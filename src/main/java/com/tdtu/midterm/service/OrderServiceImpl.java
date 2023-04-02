package com.tdtu.midterm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdtu.midterm.entity.OrderHistory;
import com.tdtu.midterm.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public List<OrderHistory> getOrder(String user) {
		return orderRepository.getOrderById(user);
	}

}
