package com.gl.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gl.dto.RestaurantDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String fullName;
	
	private String email;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)//if we give (write only )this will allow us to write but not showing the password to the frontend,
	private String password;
	
	private USER_ROLE role = USER_ROLE.ROLE_CUSTOMER;
	
	@JsonIgnore//to ignore this order while fetching the user
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")//we are telling that only customer not for seller
	private List<Order> orders= new ArrayList<>();
	
	@ElementCollection
	private List<RestaurantDto> favorites = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Address> addresses = new ArrayList<>();

}
