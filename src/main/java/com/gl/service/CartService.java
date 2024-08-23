package com.gl.service;

import com.gl.model.Cart;
import com.gl.model.CartItem;
import com.gl.request.AddCartItemRequest;

public interface CartService {
	
public CartItem addItemToCart(AddCartItemRequest req,String jwt)throws Exception;

public CartItem updateCartItemQuantity(Long cartItemId,int quantity) throws Exception;

public Cart removeItemFromCart(Long cartItemId,String jwt)throws Exception;

public Long calculateCartTotals(Cart cart) throws Exception;

public Cart findcartById(Long id) throws Exception;

public Cart findCartByUserId(Long userId)throws Exception;

public Cart clearCart(Long userId) throws Exception;
}
