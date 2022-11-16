package jpaexample.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "orders")
public class Order {

        @Id @GeneratedValue
        @Column(name = "order_id")
        private Long id;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "member_id")
        private Member member;

        @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
        private List<OrderItem> orderItems = new ArrayList<>();

        @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        @JoinColumn(name = "delivery_id")
        private Delivery delivery;

        private LocalDateTime orderDate; //주문시간

        @Enumerated(EnumType.STRING) //string으로 해야 숫자로 안받음
        private DeliveryStatus status;

        //==연관관계 메서드==//
        public void setMember(Member member) {
                this.member = member;

                member.getOrders().add(this);
        }

        public void addOrderItem(OrderItem orderItem) {
                orderItems.add(orderItem);
                orderItem.setOrder(this);
        }

        public void setDelivery(Delivery delivery) {
                this.delivery = delivery;
                delivery.setOrder(this);
        }

        //==생성 메서드==//


        //==비즈니스 로직==//
        /** 주문 취소 */


        //==조회 로직==//
        /** 전체 주문 가격 조회 */
}



//mappedBy는 양방향 연관관계에서 FK(외래키값)을 누가 관리할지에 대한 내용입니다.
//cascade는 A를 영속화 할 때 B도 함께 영속화 할지에 대한 내용입니다. 따라서 둘은 완전히 관계가 없습니다.