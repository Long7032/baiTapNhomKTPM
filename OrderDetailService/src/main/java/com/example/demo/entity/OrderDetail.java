package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_details")
public class OrderDetail {

	private int idOrderProduct;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idOrderDetail;
	@Column(name = "id_product")
	private int idProduct;
	private int quantity;
	private double price;

	public OrderDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderDetail(int idOrderProduct, int idOrderDetail, int idProduct, int quantity, double price) {
		super();
		this.idOrderProduct = idOrderProduct;
		this.idOrderDetail = idOrderDetail;
		this.idProduct = idProduct;
		this.quantity = quantity;
		this.price = price;
	}

	public int getIdOrder() {
		return idOrderProduct;
	}

	public void setIdOrder(int idOrderProduct) {
		this.idOrderProduct = idOrderProduct;
	}

	public int getIdOrderDetail() {
		return idOrderDetail;
	}

	public void setIdOrderDetail(int idOrderDetail) {
		this.idOrderDetail = idOrderDetail;
	}

	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "OrderDetail [idOrder=" + idOrderProduct + ", idOrderDetail=" + idOrderDetail + ", idProduct="
				+ idProduct + ", quantity=" + quantity + ", price=" + price + "]";
	}

}
