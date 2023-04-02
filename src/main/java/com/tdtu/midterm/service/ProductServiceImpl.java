package com.tdtu.midterm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdtu.midterm.entity.Product;
import com.tdtu.midterm.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public List<Product> getAll() {
		return productRepository.findAll();
	}

	@Override
	public Product getProductById(int id) {
		return productRepository.getById(id);
	}

	@Override
	public List<Product> search(String key) {
		return productRepository.findByNameContainingIgnoreCase(key);
	}

	@Override
	public List<Product> filter(String category, String brand, int bottom, int top) {
		return productRepository.filter(category, brand, bottom, top);
	}

	@Override
	public void addProduct(Product p) {
		productRepository.save(p);
	}

	@Override
	public void removeProduct(int p) {
		productRepository.deleteById(p);
	}

	@Override
	public void updateProduct(Product p) {
		productRepository.save(p);	
	}
}
