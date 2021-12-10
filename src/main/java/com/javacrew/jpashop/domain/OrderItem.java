package com.javacrew.jpashop.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name = "ORDER_ITEM_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    private int orderPrice;

    private int count;

    @Builder
    public OrderItem(Item item, Order order, int orderPrice, int count) {
        this.item = item;
        this.order = order;
        this.orderPrice = orderPrice;
        this.count = count;
    }

    public void changeOrder(Order order) {
        if (!Objects.isNull(this.order)) {
            this.order.getOrderItems().remove(this);
        }
        this.order = order;
        if (!Objects.isNull(order) && order.getOrderItems().contains(this)) {
            order.addOrderItem(this);
        }
    }
}
