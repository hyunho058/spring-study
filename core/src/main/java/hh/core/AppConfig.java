package hh.core;

import hh.core.discount.DiscountPolicy;
import hh.core.discount.FixDiscountPolicy;
import hh.core.discount.RateDiscountPolicy;
import hh.core.member.MemberRepository;
import hh.core.member.MemberService;
import hh.core.member.MemberServiceImp;
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
        return new MemberServiceImp(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
