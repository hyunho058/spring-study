package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING) //ORDINAL - 숫자 순번으로 저장됨( ex) READY, COMP 사이에 추가 되게되면 COMP가 기존 2에서 3으로 밀려 문제 발생) - STRING를 사용해야함
    private DeliveryStatus status; //ENUM [READY(준비), COMP(배송)]
}
