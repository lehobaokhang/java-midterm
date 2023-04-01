package com.tdtu.midterm.service;

import java.util.List;

import com.tdtu.midterm.entity.Cart;
import com.tdtu.midterm.entity.User;

public interface CartService {
	public void addCart(Cart cart);
	
	public List<Cart> getCarts();
	
	public void removeCart(String user, int product);
}
