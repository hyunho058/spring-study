package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Slf4j
@Aspect
public class AspectV6Advice {
    //hello.aop.order 패키지와 하위 패키지 이면서 클래스 이름 패턴이 *Service
    @Around("hello.aop.order.aop.Pointcuts.orderAndService()")   //포인트컷 조합 = `&&`, `||`, `!` 3가지 조합이 가능
    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            //@Before
            log.info("[Start Transaction] {}", joinPoint.getSignature());
            Object result = joinPoint.proceed();
            //@AfterReturning
            log.info("[Commit Transaction] {}", joinPoint.getSignature());
            return result;
        } catch (Exception e) {
            //@AfterThrowing
            log.info("[Rollback Transaction] {}", joinPoint.getSignature());
            throw e;
        } finally {
            //@After
            log.info("[Release Resource] {}", joinPoint.getSignature());
        }
    }

    @Before("hello.aop.order.aop.Pointcuts.orderAndService()")
    public void doBefore(JoinPoint joinPoint){
        log.info("[Before] {}", joinPoint.getSignature());
    }

    @AfterReturning(value = "hello.aop.order.aop.Pointcuts.orderAndService()", returning = "result")    //`returning = "리턴되는 값 이름"`
    public void doAfterReturning(JoinPoint joinPoint, Object result){
        log.info("[AfterReturning] {} return = {}", joinPoint.getSignature(), result);
    }

    @AfterThrowing(value = "hello.aop.order.aop.Pointcuts.orderAndService()", throwing = "ex")
    public void doAfterThrowing(JoinPoint joinPoint, Exception ex){
        log.info("[AfterThrowing] {}, exception message = {}", joinPoint.getSignature(), ex);
    }

    @After("hello.aop.order.aop.Pointcuts.orderAndService()")
    public void doAfter(JoinPoint joinPoint){
        log.info("[After] {}", joinPoint.getSignature());
    }
}
