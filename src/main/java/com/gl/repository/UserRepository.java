package com.gl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findByEmail(String fullName);
	
}
