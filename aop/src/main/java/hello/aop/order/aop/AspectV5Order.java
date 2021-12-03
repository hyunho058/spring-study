package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

@Slf4j
//@Aspect
//@Order(1) //`@Order(Number)` 어노테이션으로 Aspect실행 순서는 정할수 있지만 하나의 Aspect안에 어드바이저 들에 순서는 바꿀수 없다. => 순서를 정하려면 아래 코드처럼 클래스 단위로 적용 해야한다.
public class AspectV5Order {

    @Aspect
    @Order(2)
    public static class LogAspect {
        @Around("hello.aop.order.aop.Pointcuts.allOrder()")
        public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[log] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }
    }

    @Aspect
    @Order(1)
    public static class TxAspect{
        //hello.aop.order 패키지와 하위 패키지 이면서 클래스 이름 패턴이 *Service
        @Around("hello.aop.order.aop.Pointcuts.orderAndService()")   //포인트컷 조합 = `&&`, `||`, `!` 3가지 조합이 가능
        public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
            try {
                log.info("[Start Transaction] {}", joinPoint.getSignature());
                Object result = joinPoint.proceed();
                log.info("[Commit Transaction] {}", joinPoint.getSignature());
                return result;
            } catch (Exception e) {
                log.info("[Rollback Transaction] {}", joinPoint.getSignature());
                throw e;
            } finally {
                log.info("[Release Resource] {}", joinPoint.getSignature());
            }
        }
    }
}
