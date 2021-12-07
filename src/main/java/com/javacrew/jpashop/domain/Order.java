package com.javacrew.jpashop.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

    private LocalDateTime localDateTime;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Builder
    public Order(Member member, LocalDateTime localDateTime, OrderStatus orderStatus) {
        this.member = member;
        this.localDateTime = localDateTime;
        this.orderStatus = orderStatus;
        orderItems = new ArrayList<>();
    }

    public void changeMember(Member member) {
        if (!Objects.isNull(this.member)) {
            this.member.getOrders().remove(this);
        }
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        this.orderItems.add(orderItem);
        orderItem.changeOrder(this);
    }
}
