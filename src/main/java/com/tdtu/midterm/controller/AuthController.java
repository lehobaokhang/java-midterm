package com.tdtu.midterm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.tdtu.midterm.entity.User;
import com.tdtu.midterm.service.UserService;

@Controller
public class AuthController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/login")
	public String showLoginForm(Model model, HttpSession session) {
		if (session.getAttribute("user") != null) {
			return "redirect:/";
		}
		User user = new User();
		model.addAttribute("user", user);
		return "login";
	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute("user") User user, Model model, HttpSession session) {
		User userInfo = userService.findUserByUsername(user.getUsername());
		
		if (userInfo == null) {
			model.addAttribute("message", "This username does not match with any account");
			return "login";
		} else if (!userInfo.getPassword().equals(user.getPassword())) {
			model.addAttribute("message", "Incorrect password");
			return "login";
		}
		
		session.setAttribute("user", userInfo);
//		session.setAttribute("userId", userInfo.getId());
//		session.setAttribute("name", userInfo.getName());
		return "redirect:/";
	}
	
	@PostMapping("/register")
	public String register(@ModelAttribute("user") User user) {
		userService.register(user);
		return "redirect:/login";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
	    session.invalidate();
	    return "redirect:/login";
	}
}
