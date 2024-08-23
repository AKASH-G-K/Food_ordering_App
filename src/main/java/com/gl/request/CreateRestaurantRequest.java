package com.gl.request;

import java.util.List;

import com.gl.model.Address;
import com.gl.model.ContactInformation;

import lombok.Data;

@Data
public class CreateRestaurantRequest {
 private Long id;
 private String name;
 private String description;
 private Address address;
 private ContactInformation contactInformation;
 private String openingHours;
 private List<String> images;
 private String cuisineType;
 
}
