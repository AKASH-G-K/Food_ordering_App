package com.gl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

	public Cart findByCustomerId(Long userId);
}
