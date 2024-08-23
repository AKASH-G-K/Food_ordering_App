package com.gl.request;

import com.gl.model.Address;

import lombok.Data;

@Data
public class OrderRequest {

	private Long restaurantId;
	private Address deliveryAddress;
}
