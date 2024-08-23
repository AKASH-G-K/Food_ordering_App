package com.gl.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.dto.RestaurantDto;
import com.gl.model.Address;
import com.gl.model.Restaurant;
import com.gl.model.User;
import com.gl.repository.AddressRepository;
import com.gl.repository.RestaurantRepository;
import com.gl.repository.UserRepository;
import com.gl.request.CreateRestaurantRequest;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	private  RestaurantRepository restaurantRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private UserRepository userRepository;
	@Override
	public Restaurant createRestaurant(CreateRestaurantRequest req, User user) {
		// TODO Auto-generated method stub
		Address address =addressRepository.save(req.getAddress());
		Restaurant restaurant = new Restaurant();
		restaurant.setAddress(address);
		restaurant.setContactInformation(req.getContactInformation());
		restaurant.setCuisineType(req.getCuisineType());
		restaurant.setDescription(req.getDescription());
		restaurant.setName(req.getName());
		restaurant.setImages(req.getImages());
		restaurant.setOpeningHours(req.getOpeningHours());
		restaurant.setRegistrationDate(LocalDateTime.now());
		restaurant.setOwner(user);
		return restaurantRepository.save(restaurant);
	}

	@Override
	public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRestaurant) throws Exception {
		// TODO Auto-generated method stub
		Restaurant  restaurant = findRestaurantById(restaurantId);
		if(restaurant.getCuisineType()!=null) {
			restaurant.setCuisineType(updatedRestaurant.getCuisineType());
		}
		if(restaurant.getDescription()!=null) {
			restaurant.setDescription(restaurant.getDescription());
		}
		if(restaurant.getName()!=null) {
			restaurant.setName(updatedRestaurant.getName());
		}
		return restaurantRepository.save(restaurant);
	}

	@Override
	public void deleteRestaurant(Long restaurantId) throws Exception {
		// TODO Auto-generated method stub
		Restaurant restaurant = findRestaurantById(restaurantId);
		
			restaurantRepository.delete(restaurant);
		
		
	}

	@Override
	public List<Restaurant> getAllRestaurants() {
		// TODO Auto-generated method stub
		
		return restaurantRepository.findAll();
	}

	@Override
	public List<Restaurant> searchRestaurant(String keyword) {
		// TODO Auto-generated method stub
		return restaurantRepository.findBySearchquery(keyword);
	}

	@Override
	public Restaurant findRestaurantById(Long id) throws Exception {
		// TODO Auto-generated method stub
		Optional<Restaurant> opt = restaurantRepository.findById(id);
		
		if(opt.isEmpty()) {
		   throw new Exception("Restaurant not found with the id " + id);	
		}
		return opt.get();
	}

	@Override
	public Restaurant getRestaurantByUserId(Long userId) throws Exception {
		// TODO Auto-generated method stub
		Restaurant restaurant = restaurantRepository.findByOwnerId(userId);
		if(restaurant ==null) {
			throw new Exception("Restaurant not found with owner id"+userId);
		}
		return restaurant;
	}

	@Override
	public RestaurantDto addToFavourites(Long restaurantId, User user) throws Exception {
		// TODO Auto-generated method stub
		Restaurant restaurant = findRestaurantById(restaurantId);
		
		RestaurantDto dto = new RestaurantDto();
		dto.setDescription(restaurant.getDescription());
		dto.setImages(restaurant.getImages());
		dto.setTitle(restaurant.getName());
		dto.setId(restaurantId);
//		if(user.getFavorites().contains(dto)) {
//			user.getFavorites().remove(dto);
//		}else {
//			user.getFavorites().add(dto);
//		}
		Boolean isfavorited = false;
		List<RestaurantDto> favorites = user.getFavorites();
		for(RestaurantDto favorite:favorites) {
			if(favorite.getId().equals(restaurantId)) {
				isfavorited=true;
				break;
			}
		}
		if(isfavorited) {
			favorites.removeIf(favorite -> favorite.getId().equals(restaurantId));
		}else {
			favorites.add(dto);
		}
		userRepository.save(user);
		return dto;
	}

	@Override
	public Restaurant updateRestaurantStatus(Long id) throws Exception {
		// TODO Auto-generated method stub
		Restaurant restaurant = findRestaurantById(id);
		
		restaurant.setOpen(!restaurant.isOpen());
		return restaurantRepository.save(restaurant);
	}

}
