package com.tdtu.midterm.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.tdtu.midterm.entity.Brand;
import com.tdtu.midterm.entity.Category;
import com.tdtu.midterm.entity.Product;
import com.tdtu.midterm.entity.User;
import com.tdtu.midterm.repository.BrandRepository;
import com.tdtu.midterm.repository.CategoryRepository;
import com.tdtu.midterm.service.ProductService;

@Controller
public class ManageController {
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private BrandRepository brandRepository;
	
	@GetMapping("/manage")
	public String showManage(HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		if(user == null || user.getRole() != 0) {
			return "redirect:/";
		}
		
		List<Product> products = productService.getAll();
		
		model.addAttribute("products", products);
		
		return "manage";
	}
	
	@GetMapping("/manage/add")
	public String addProduct(HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		if(user == null || user.getRole() != 0) {
			return "redirect:/";
		}
		
		Product product = new Product();
		List<Category> categories = categoryRepository.findAll();
		List<Brand> brands = brandRepository.findAll();		
		
		model.addAttribute("product", product);
		model.addAttribute("brands", brands);
		model.addAttribute("categories", categories);
		
		return "add";
	}
	
	@PostMapping("/manage/add")
	public String addProductHandle(@ModelAttribute("product") Product product, @RequestParam("image_product") MultipartFile image_product) {
		if (image_product == null) {
			System.out.println("1");
		} else System.out.println('2');
		return "redirect:/manage";
	}
}
