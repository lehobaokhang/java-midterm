package com.tdtu.midterm.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tdtu.midterm.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer>{
	@Transactional
	@Modifying
	@Query("DELETE FROM Cart c WHERE c.product.id = :productId AND c.user.id = :userId")
	void removeCart(@Param("productId") int productId, @Param("userId") String userId);

}
