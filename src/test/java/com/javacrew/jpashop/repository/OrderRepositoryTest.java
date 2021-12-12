package com.javacrew.jpashop.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.javacrew.jpashop.domain.Member;
import com.javacrew.jpashop.domain.Order;
import com.javacrew.jpashop.domain.OrderStatus;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class OrderRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    OrderRepository orderRepository;

    @Test
    void 회원_주문_연관관계_편의_메소드_테스트() {
        Member member = memberRepository.save(Member.builder()
            .name("상우")
            .build());

        Order order = orderRepository.save(Order.builder()
            .orderDateTime(LocalDateTime.now())
            .status(OrderStatus.ORDER)
            .build());

        order.updateMember(member);

        assertAll(
            () -> assertThat(order.getMember()).isEqualTo(member),
            () -> assertThat(member.getOrders()).contains(order)
        );
    }
}
