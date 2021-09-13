package hh.core.autowired;

import hh.core.AutoAppConfig;
import hh.core.discount.DiscountPolicy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

public class AllBeanTest {

    @Test
    @DisplayName("findAllBean")
    void findAllBean(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);
    }

     static class DiscountService{
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        @Autowired
         public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
             this.policyMap = policyMap;
             this.policies = policies;
             System.out.println("policyMap = " + policyMap);
             System.out.println("policies = " + policies);
         }
     }
}
