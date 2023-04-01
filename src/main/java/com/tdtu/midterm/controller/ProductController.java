package com.tdtu.midterm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tdtu.midterm.entity.Brand;
import com.tdtu.midterm.entity.Category;
import com.tdtu.midterm.entity.Product;
import com.tdtu.midterm.repository.BrandRepository;
import com.tdtu.midterm.repository.CategoryRepository;
import com.tdtu.midterm.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private BrandRepository brandRepository;
	
	@GetMapping("/product/{id}")
	public String getProductById(@PathVariable int id, Model model) {
		Product product = productService.getProductById(id);
						
		String[] productDetails = product.getDetail().split(" \\|\\| ");
		
		List<Category> categories = categoryRepository.findAll();
		List<Brand> brands = brandRepository.findAll();
		
		
		model.addAttribute("categories", categories);
		model.addAttribute("brands", brands);
		model.addAttribute("details", productDetails);
		model.addAttribute("product", product);
		
		return "product-details";
	}
}
