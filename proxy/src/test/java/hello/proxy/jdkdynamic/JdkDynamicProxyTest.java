package hello.proxy.jdkdynamic;

import hello.proxy.jdkdynamic.code.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

@Slf4j
public class JdkDynamicProxyTest {
    @Test
    void dynamicA() {
        AInterface target = new AImpl();
        TimeInvocationHandler handler = new TimeInvocationHandler(target);

        //proxy 생성 기술 - Proxy.newProxyInstance(어디에 생성될지, 들어가는 인터페이스(어떤 인터페이스를 기반으로 프록시를 만들지), 프록시가 사용할 로직 호출)
        AInterface proxy = (AInterface) Proxy.newProxyInstance(
                AInterface.class.getClassLoader(),
                new Class[]{AInterface.class},
                handler
        );

        proxy.call();
        log.info("targetClass={}",target.getClass());
        log.info("proxyClass={}",proxy.getClass());
    }

    @Test
    void dynamicB() {
        BInterface target = new BImpl();
        TimeInvocationHandler handler = new TimeInvocationHandler(target);

        //proxy 생성 기술 - Proxy.newProxyInstance(어디에 생성될지, 들어가는 인터페이스(어떤 인터페이스를 기반으로 프록시를 만들지), 프록시가 사용할 로직 호출)
        BInterface proxy = (BInterface) Proxy.newProxyInstance(
                BInterface.class.getClassLoader(),
                new Class[]{BInterface.class},
                handler
        );

        proxy.call();
        log.info("targetClass={}",target.getClass());
        log.info("proxyClass={}",proxy.getClass());
    }
}
