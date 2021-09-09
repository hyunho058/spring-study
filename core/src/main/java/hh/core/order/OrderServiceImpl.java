package hh.core.order;

import hh.core.discount.DiscountPolicy;
import hh.core.discount.FixDiscountPolicy;
import hh.core.discount.RateDiscountPolicy;
import hh.core.member.Member;
import hh.core.member.MemberRepository;
import hh.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;
    //OCP 위반 사례
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도(싱글톤)
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
