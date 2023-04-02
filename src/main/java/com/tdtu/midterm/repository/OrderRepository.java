package com.tdtu.midterm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tdtu.midterm.entity.OrderHistory;

public interface OrderRepository extends JpaRepository<OrderHistory, Integer> {
	@Query("SELECT o FROM OrderHistory o WHERE o.user = :userId")
	List<OrderHistory> getOrderById(@Param("userId") String userId);
}
