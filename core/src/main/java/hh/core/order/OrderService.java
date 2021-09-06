package hh.core.order;

import hh.core.member.Member;

public interface OrderService {

    Order createOrder(Long memberId, String itemName, int itemPrice);

}
