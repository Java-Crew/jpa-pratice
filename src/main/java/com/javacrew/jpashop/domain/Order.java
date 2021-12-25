package com.javacrew.jpashop.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(
    name = "ORDERS",
    uniqueConstraints =
    @UniqueConstraint(
        columnNames = {"DELIVERY_ID"}
    )
)
public class Order extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "DELIVERY_ID")
    private Delivery delivery;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Builder
    public Order(Member member, Delivery delivery, OrderStatus orderStatus) {
        this.member = member;
        this.delivery = delivery;
        this.orderStatus = orderStatus;
        orderItems = new ArrayList<>();
    }

    public void changeMember(Member member) {
        if (Objects.nonNull(this.member)) {
            this.member.getOrders().remove(this);
        }
        this.member = member;
        if (Objects.nonNull(member) && !member.getOrders().contains(this)) {
            member.addOrder(this);
        }
    }

    public void addOrderItem(OrderItem orderItem) {
        this.orderItems.add(orderItem);
        if (orderItem.getOrder() != this) {
            orderItem.changeOrder(this);
        }
    }

    public void changeDelivery(Delivery delivery) {
        if (Objects.nonNull(this.delivery)) {
            this.delivery.changeOrder(null);
        }
        this.delivery = delivery;
        if (Objects.nonNull(this.delivery) && this.delivery.getOrder() != this) {
            this.delivery.changeOrder(this);
        }
    }
}
