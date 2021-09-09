package hh.core;

import hh.core.discount.DiscountPolicy;
import hh.core.discount.RateDiscountPolicy;
import hh.core.member.MemberRepository;
import hh.core.member.MemberService;
import hh.core.member.MemberServiceImpl;
import hh.core.member.MemoryMemberRepository;
import hh.core.order.OrderService;
import hh.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    //생성자 주입
    @Bean
    public MemberService memberService() {
        System.out.println("Call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("Call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("Call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
