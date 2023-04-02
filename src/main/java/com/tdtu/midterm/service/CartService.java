package com.tdtu.midterm.service;

import java.util.List;

import com.tdtu.midterm.entity.Cart;

public interface CartService {
	public void addCart(Cart cart);
	
	public List<Cart> getCarts(String user);
	
	public void removeCart(String user, int product);
	
	public int getTotal(String user);
}
