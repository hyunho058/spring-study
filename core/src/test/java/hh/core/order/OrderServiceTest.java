package hh.core.order;

import hh.core.AppConfig;
import hh.core.discount.DiscountPolicy;
import hh.core.discount.FixDiscountPolicy;
import hh.core.member.Grade;
import hh.core.member.Member;
import hh.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

//    MemberService memberService = new MemberServiceImp();
//    OrderService orderService = new OrderServiceImpl();

    MemberService memberService;
    OrderService orderService;
    DiscountPolicy discountPolicy = new FixDiscountPolicy();
    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    @DisplayName("order")
    void order(){
        //give
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        //when
        memberService.join(member);
        Order order = orderService.createOrder(memberId, "itemA", 1000);
        System.out.println("order== "+order.toString());
        //then
//        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000); //FixDiscount
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(100); //RateDiscount
//        Assertions.assertThat(order.calculatePrice()).isEqualTo(0);
    }
}
