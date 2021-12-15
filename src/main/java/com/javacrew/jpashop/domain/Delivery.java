package com.javacrew.jpashop.domain;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Delivery extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    private String city;

    private String street;

    private String zipcode;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliverystatus;

    @Builder
    public Delivery(Order order, String city, String street, String zipcode, DeliveryStatus deliverystatus) {
        this.order = order;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
        this.deliverystatus = deliverystatus;
    }

    public void changeOrder(Order order) {
        if (Objects.nonNull(this.order)) {
            this.order.changeDelivery(null);
        }
        this.order = order;
        if (Objects.nonNull(this.order) && this.order.getDelivery() != this) {
            order.changeDelivery(this);
        }
    }
}
