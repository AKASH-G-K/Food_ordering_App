package com.gl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}
