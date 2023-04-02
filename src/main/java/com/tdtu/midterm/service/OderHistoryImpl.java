package com.tdtu.midterm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdtu.midterm.entity.OrderHistory;
import com.tdtu.midterm.repository.CheckoutRepository;

@Service
public class OderHistoryImpl implements CheckoutService {
	@Autowired
	private CheckoutRepository checkoutRepository;
	
	@Override
	public void checkout(OrderHistory o) {
		checkoutRepository.save(o);
	}

}
