package com.tdtu.midterm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tdtu.midterm.entity.Brand;
import com.tdtu.midterm.entity.Category;
import com.tdtu.midterm.entity.Product;
import com.tdtu.midterm.repository.BrandRepository;
import com.tdtu.midterm.repository.CategoryRepository;
import com.tdtu.midterm.service.ProductService;

@Controller
public class FilterController {
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private BrandRepository brandRepository;
	
	@GetMapping("/filter")
	public String filter(@RequestParam(value = "category", required = false) String category,
			@RequestParam(value = "brand", required = false) String brand,
			@RequestParam(value = "price", required = false) Integer price,
			Model model) {
		
		if (category == null) {
			category = "";
		}
		
		if (brand == null) {
			brand = "";
		}
		int top = 0, bottom = 0;
		if (price == null) {
			top = 100000000;
			bottom = 0;
		}
		else if (price == 1) {
			bottom = 0;
			top = 10000000;
		} else if (price == 2) {
			bottom = 10000001;
			top = 20000000;
		} else if (price == 3) {
			bottom = 20000001;
			top = 30000000;
		} else {
			bottom = 30000001;
			top = 100000000;
		}
		
		List<Product> products = productService.filter(category, brand, bottom, top);
		
		List<Category> categories = categoryRepository.findAll();
		List<Brand> brands = brandRepository.findAll();
		
		model.addAttribute("products", products);
		model.addAttribute("categories", categories);
		model.addAttribute("brands", brands);
		model.addAttribute("categorySelected", Integer.parseInt(category == "" ? "0" : category));
		model.addAttribute("brandSelected", Integer.parseInt(brand == "" ? "0" : brand));
		model.addAttribute("priceSelected", price == null ? 0 : price);
		
		return "index";
	}
}
