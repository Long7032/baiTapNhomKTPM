package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.OrderDetail;
import com.example.demo.repository.OrderDetailRepository;

@RestController
@RequestMapping("/api/v5")

public class OrderDetailController {

	@Autowired
	private OrderDetailRepository orderDetailRepository;

	@PostMapping("/findOrderDetail")
	public Optional<OrderDetail> findOrderDetail(@RequestParam int IdOrderDetail) {
		// TODO: process POST request
		System.out.println(orderDetailRepository.findById(IdOrderDetail));
		;
		return orderDetailRepository.findById(IdOrderDetail);
	}

	@PostMapping("/findAllOrderDetail")
	public List<OrderDetail> findAllOrderDetail() {
		// TODO: process POST request
		System.out.println(orderDetailRepository.findAll());
		;
		return orderDetailRepository.findAll();
	}

	@RequestMapping("/deleteOrderDetail")
	public String deleteOrderDetail(@RequestParam int IdOrderDetail) {
		// TODO: process POST request
		orderDetailRepository.deleteById(IdOrderDetail);
		System.out.println("delete successful!!");
		return "delete OrderDetail by id";
	}

	@RequestMapping("/AddOrderdetail")
	public String AddUser(@RequestBody OrderDetail orderDetail) {
		// TODO: process POST request
		orderDetailRepository.save(orderDetail);
		System.out.println("Add successfull");
		return "Add user successful";
	}

}
