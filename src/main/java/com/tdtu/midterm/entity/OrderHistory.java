package com.tdtu.midterm.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "order_history")
public class OrderHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "user")
	private String user;
	
	@Column(name = "created_at")
	private Timestamp created_at;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "orderId")
	private List<OrderProduct> products;

	public OrderHistory(String user, Timestamp created_at) {
		super();
		this.user = user;
		this.created_at = created_at;
	}

	public OrderHistory() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public List<OrderProduct> getProducts() {
		return products;
	}

	public void setProducts(List<OrderProduct> products) {
		this.products = products;
	}
	
	public void addProduct(OrderProduct p) {
		if (products == null) {
			products = new ArrayList<>();
		}
		products.add(p);
	}
}
