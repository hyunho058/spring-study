package hh.core.order;

import hh.core.discount.DiscountPolicy;
import hh.core.discount.FixDiscountPolicy;
import hh.core.member.Grade;
import hh.core.member.Member;
import hh.core.member.MemberService;
import hh.core.member.MemberServiceImp;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {
    MemberService memberService = new MemberServiceImp();
    OrderService orderService = new OrderServiceImpl();
    DiscountPolicy discountPolicy = new FixDiscountPolicy();
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
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
        Assertions.assertThat(order.calculatePrice()).isEqualTo(0);
    }
}
