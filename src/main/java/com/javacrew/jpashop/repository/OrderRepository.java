package com.javacrew.jpashop.repository;

import com.javacrew.jpashop.domain.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
