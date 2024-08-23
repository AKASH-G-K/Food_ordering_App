package com.gl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
