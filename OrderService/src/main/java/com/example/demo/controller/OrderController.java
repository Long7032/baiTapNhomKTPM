package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Order;
import com.example.demo.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/v4")
public class OrderController {

	@Autowired
	private OrderRepository orderRepository;

	@PostMapping("/findOrder")
	public Optional<Order> findOrder(@RequestParam int IdOrder) {
		// TODO: process POST request
		System.out.println(orderRepository.findById(IdOrder));
		;
		return orderRepository.findById(IdOrder);
	}

	@PostMapping("/findAllOrder")
	public List<Order> findAllOrder() {
		// TODO: process POST request
		System.out.println(orderRepository.findAll());
		;
		return orderRepository.findAll();
	}

	@RequestMapping("/deleteOrder")
	public String deleteOrder(@RequestParam int IdOrder) {
		// TODO: process POST request
		orderRepository.deleteById(IdOrder);
		System.out.println("delete successful!!");
		return "delete Order by id";
	}

	@RequestMapping("/AddOrder")
	public String AddOrder(@RequestBody Order order) {
		// TODO: process POST request
		orderRepository.save(order);
		System.out.println("Add successfull");
		return "Add order successful";
	}

}
