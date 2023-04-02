package com.tdtu.midterm.controller;

import java.nio.file.Path;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
		model.addAttribute("action", "add");
		
		return "add";
	}
	
	@PostMapping("/manage/add")
	public String addProductHandle(@ModelAttribute("product") Product product, @RequestParam("image_product") MultipartFile image_product) {
		String filePath = "/uploads/";
		if (!image_product.isEmpty()) {	
			try {
				String fileName = image_product.getOriginalFilename();
				image_product.transferTo(Path.of("src/main/resources/static" + filePath + fileName));
				product.setImage(filePath + fileName);
				
				productService.addProduct(product);
			} catch (Exception e) {				
			}
		}		
		return "redirect:/manage";
	}
	
	@GetMapping("/manage/edit/{id}")
	public String editProduct(@PathVariable int id, HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		if(user == null || user.getRole() != 0) {
			return "redirect:/";
		}
		
		Product product = productService.getProductById(id);
		List<Category> categories = categoryRepository.findAll();
		List<Brand> brands = brandRepository.findAll();		
		
		model.addAttribute("brands", brands);
		model.addAttribute("categories", categories);
		model.addAttribute("product", product);
		model.addAttribute("action", "edit");
				
		return "edit";
	}
	
	@PostMapping("/manage/edit")
	public String editProductHandle(@ModelAttribute("product") Product product, @RequestParam("image_product") MultipartFile image_product) {
		String[] imageTemp = product.getImage().split("/");
		if (!image_product.isEmpty()) {	
			try {
				image_product.transferTo(Path.of("src/main/resources/static/uploads/" +  imageTemp[imageTemp.length - 1]));
				
			} catch (Exception e) {				
			}
		}
		
		product.setImage("/uploads/" + imageTemp[imageTemp.length - 1]);
		productService.updateProduct(product);
		
		return "redirect:/manage";
	}
	
	@PostMapping("/manage/remove")
	@ResponseBody
	public boolean removeProduct(@RequestParam("product") int product) {
		productService.removeProduct(product);		
		return true;
	}
}
