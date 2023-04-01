package com.tdtu.midterm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "image")
	private String image;
	
	@OneToOne
    @JoinColumn(name = "category")
	private Category category;
	
	@OneToOne
    @JoinColumn(name = "brand")
	private Brand brand;
	
	@Column(name = "color")
	private String color;
	
	@Column(name = "detail")
	private String detail;
	
	@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "price")
	private int price;
	
	public Product() {
		
	}

//	public Product(String name, String image, int category, int brand, String color, String detail, int quantity,
//			int price) {
//		super();
//		this.name = name;
//		this.image = image;
//		this.category = category;
//		this.brand = brand;
//		this.color = color;
//		this.detail = detail;
//		this.quantity = quantity;
//		this.price = price;
//	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", image=" + image + ", category=" + category + ", brand="
				+ brand + ", color=" + color + ", detail=" + detail + ", quantity=" + quantity + ", price=" + price
				+ "]";
	}
}
