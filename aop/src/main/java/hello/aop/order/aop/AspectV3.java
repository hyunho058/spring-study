package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class AspectV3 {

    //hello.app.order 패키지와 하위 패키지 적용
    @Pointcut("execution(* hello.aop.order..*(..))")
    private void allOrder() {
    }   //pointcut signature

    //클래스 이름 패턴이 *Service인것
    @Pointcut("execution(* *..*Service.*(..))")
    private void allService() {
    }

    @Around("allOrder()")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[log] {}", joinPoint.getSignature());
        return joinPoint.proceed();
    }

    //hello.aop.order 패키지와 하위 패키지 이면서 클래스 이름 패턴이 *Service
    @Around("allOrder() && allService()")   //포인트컷 조합 = `&&`, `||`, `!` 3가지 조합이 가능
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
