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
public class SearchController {
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private BrandRepository brandRepository;

	@GetMapping("/search")
	public String search(@RequestParam("key") String key, Model model) {
		List<Product> products = productService.search(key);

		List<Category> categories = categoryRepository.findAll();
		List<Brand> brands = brandRepository.findAll();
		
		model.addAttribute("products", products);
		model.addAttribute("categories", categories);
		model.addAttribute("brands", brands);

		model.addAttribute("products", products);
		return "index";
	}
}
