package com.gl.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.model.Address;
import com.gl.model.Cart;
import com.gl.model.CartItem;
import com.gl.model.Order;
import com.gl.model.OrderItem;
import com.gl.model.Restaurant;
import com.gl.model.User;
import com.gl.repository.AddressRepository;
import com.gl.repository.OrderItemRepository;
import com.gl.repository.OrderRepository;
import com.gl.repository.UserRepository;
import com.gl.request.OrderRequest;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Order createOrder(OrderRequest order, User user) throws Exception  {
		// TODO Auto-generated method stub
		Address shippingAddress = order.getDeliveryAddress();
		Address savedAddress=addressRepository.save(shippingAddress);
		if(!user.getAddresses().contains(savedAddress)) {
			user.getAddresses().add(savedAddress);
			userRepository.save(user);
		}
		Restaurant restaurant  = restaurantService.findRestaurantById(order.getRestaurantId());
		
		Order createdOrder = new Order();
		createdOrder.setCustomer(user);
		createdOrder.setCreatedAt(new Date());
		createdOrder.setDeliveryAddress(savedAddress);
		createdOrder.setRestaurant(restaurant);
		createdOrder.setOrderStatus("PENDING");
		
		Cart cart = cartService.findCartByUserId(user.getId());
		
		List<OrderItem> orderItems = new ArrayList<>();
		for(CartItem cartItem:cart.getItem()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setFood(cartItem.getFood());
			orderItem.setIgredients(cartItem.getIngredients());
			orderItem.setQuantity(cartItem.getQuantity());
			orderItem.setTotalPrice(cartItem.getTotalPrice());
			
			OrderItem savedOrderItem = orderItemRepository.save(orderItem);
			orderItems.add(savedOrderItem);
		}
		Long totalPrice=cartService.calculateCartTotals(cart);
		
		createdOrder.setItems(orderItems);
		createdOrder.setTotalPrice(totalPrice);
		
		Order savedOrder = orderRepository.save(createdOrder);
         restaurant.getOrders().add(savedOrder);
		return createdOrder;
	}

	@Override
	public Order updateOrder(Long orderId, String orderStatus) throws Exception {
		// TODO Auto-generated method stub
		Order order = findOrderById(orderId);
		if(orderStatus.equals("OUT_FOR_DELIVERY")
				|| orderStatus.equals("DELIVERED")
				|| orderStatus.equals("COMPLETED") 
				|| orderStatus.equals("PENDING")) {
			order.setOrderStatus(orderStatus);
			return orderRepository.save(order);
		}
		throw new Exception("Please select the valid order status");
	}

	@Override
	public void cancelOrder(Long orderId) throws Exception {
		// TODO Auto-generated method stub
		Order order=findOrderById(orderId);
		orderRepository.deleteById(orderId);

	}

	@Override
	public List<Order> getUsersOrder(Long userId) throws Exception {
		// TODO Auto-generated method stub
		return orderRepository.findByCustomerId(userId);
	}

	@Override
	public List<Order> getRestaurantsOrder(Long restaurantId, String orderStatus) throws Exception {
		// TODO Auto-generated method stub
	   List<Order> orders = orderRepository.findByRestaurantId(restaurantId);
	   
	   if(orderStatus!=null) {
		   orders = orders.stream().filter(order ->
		                      order.getOrderStatus().equals(orderStatus))
				                .collect(Collectors.toList());
	   }
	   return orders;
	}

	@Override
	public Order findOrderById(Long orderId) throws Exception {
		// TODO Auto-generated method stub
		Optional<Order> optionalOrder = orderRepository.findById(orderId);
		if(optionalOrder.isEmpty()) {
			throw new Exception("Order not found" + orderId);
		}
		return optionalOrder.get();
	}

}
