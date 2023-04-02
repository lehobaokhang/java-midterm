package com.tdtu.midterm.controller;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.tdtu.midterm.entity.Cart;
import com.tdtu.midterm.entity.OrderHistory;
import com.tdtu.midterm.entity.OrderProduct;
import com.tdtu.midterm.entity.User;
import com.tdtu.midterm.service.CartService;
import com.tdtu.midterm.service.CheckoutService;

@Controller
public class CheckoutController {
	@Autowired
	private CartService cartService;
	
	@Autowired
	private CheckoutService checkoutService;
	
	@PostMapping("/checkout")
	public String checkout(HttpSession session) {
		User user = (User) session.getAttribute("user");
		
		List<Cart> carts = cartService.getCarts(user.getId());
		
		OrderHistory orderHistory = new OrderHistory();
		orderHistory.setUser(user.getId());
		Instant created_at = Instant.now();
		orderHistory.setCreated_at(Timestamp.from(created_at));
		
		for (Cart cart : carts) {
			orderHistory.addProduct(new OrderProduct(cart.getProduct()));
			cartService.removeCart(cart.getUser().getId(), cart.getProduct().getId());
		}
		
		checkoutService.checkout(orderHistory);
		
		return "redirect:/";
	}
}
