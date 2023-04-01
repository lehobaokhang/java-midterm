package com.tdtu.midterm.service;

import java.util.List;

import com.tdtu.midterm.entity.Product;

public interface ProductService {
	public List<Product> getAll();
	
	public Product getProductById(int id);
	
	public List<Product> search(String key);
	
	public List<Product> filter(String category, String brand, int bottom, int top);
}
