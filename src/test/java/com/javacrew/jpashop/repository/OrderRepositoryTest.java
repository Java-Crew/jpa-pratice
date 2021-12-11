package com.javacrew.jpashop.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.javacrew.jpashop.domain.Delivery;
import com.javacrew.jpashop.domain.DeliveryStatus;
import com.javacrew.jpashop.domain.Member;
import com.javacrew.jpashop.domain.Order;
import com.javacrew.jpashop.domain.OrderStatus;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@DisplayName("OrderRepository 테스트")
class OrderRepositoryTest {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    @DisplayName("Order 객체를 저장한다.")
    void save() {
        Delivery delivery = deliveryRepository.save(Delivery.builder()
            .city("서울")
            .deliverystatus(DeliveryStatus.READY)
            .street("송파구")
            .build());

        Order expected = orderRepository.save(Order.builder()
            .orderStatus(OrderStatus.ORDER)
            .localDateTime(LocalDateTime.now())
            .delivery(delivery)
            .build());
        expected.changeDelivery(delivery);

        Order actual = orderRepository.findById(expected.getId()).get();

        assertAll(
            () -> assertThat(expected).isSameAs(actual),
            () -> assertThat(expected.getDelivery()).isNotNull(),
            () -> assertThat(expected.getDelivery()).isSameAs(actual.getDelivery())
        );
    }
}