package com.tdtu.midterm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tdtu.midterm.entity.OrderHistory;

public interface CheckoutRepository extends JpaRepository<OrderHistory, Integer>{

}
