package com.tdtu.midterm.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tdtu.midterm.entity.Cart;
import com.tdtu.midterm.entity.Product;
import com.tdtu.midterm.entity.User;
import com.tdtu.midterm.service.CartService;
import com.tdtu.midterm.service.ProductService;

@Controller
public class CartController {
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CartService cartService;
	
	@GetMapping("/cart")
	public String showCart(HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:/login";
		}
		
		List<Cart> carts = cartService.getCarts();
		model.addAttribute("carts", carts);
		
		return "cart";
	}
	
	@GetMapping("/cart/remove/{id}")
	public String removeCart(@PathVariable int id, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:/login";
		}
		cartService.removeCart(user.getId(), id);
		return "redirect:/cart";
	}
	
	@PostMapping("/cart")
	@ResponseBody
	public boolean addCart(@RequestParam("product") int productId, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return false;
		}
		
		Product product = productService.getProductById(productId);
		Cart cart = new Cart(user, product);
		
		cartService.addCart(cart);
		
		return true;
	}
}
