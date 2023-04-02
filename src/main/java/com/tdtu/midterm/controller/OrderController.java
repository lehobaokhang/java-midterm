package com.tdtu.midterm.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tdtu.midterm.entity.OrderHistory;
import com.tdtu.midterm.entity.User;
import com.tdtu.midterm.service.OrderService;

@Controller
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/order")
	public String showOrder(HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:/login";
		}
		
		List<OrderHistory> orderHistory = orderService.getOrder(user.getId());
		System.out.println(orderHistory.get(0).getProducts().size());
		model.addAttribute("orders", orderHistory);
		
		return "order";
	}
}
