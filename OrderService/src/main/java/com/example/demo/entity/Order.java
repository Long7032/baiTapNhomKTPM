package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "OrderProduct")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idOrder;
	private String dateTime;

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Order(int id, String dateTime) {
		super();
		this.idOrder = id;
		this.dateTime = dateTime;
	}

	public int getId() {
		return idOrder;
	}

	public void setId(int id) {
		this.idOrder = id;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	@Override
	public String toString() {
		return "Order [id=" + idOrder + ", dateTime=" + dateTime + "]";
	}

}
