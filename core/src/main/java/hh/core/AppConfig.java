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

public class AppConfig {

    //생성자 주입
    public MemberService memberService(){
        return new MemberServiceImp(memberRepository());
    }

    private MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }

    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy(){
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
