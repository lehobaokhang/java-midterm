package com.tdtu.midterm.service;

import java.util.List;

import com.tdtu.midterm.entity.OrderHistory;

public interface OrderService {
	public List<OrderHistory> getOrder(String user);
}
