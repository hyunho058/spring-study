package hh.core.discount;

import hh.core.annotation.MainDiscountPolicy;
import hh.core.member.Grade;
import hh.core.member.Member;
import org.springframework.stereotype.Component;

//@Component
//@MainDiscountPolicy
public class FixDiscountPolicy implements DiscountPolicy{

    private int discountFixAmount = 1000;//1000원 할인

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP){
            return discountFixAmount;
        }else {
            return 0;
        }
    }
}
