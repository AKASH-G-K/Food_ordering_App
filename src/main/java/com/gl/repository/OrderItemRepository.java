package com.gl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
