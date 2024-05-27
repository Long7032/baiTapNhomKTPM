package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v2")

public class UserController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RedisTemplate template;

	@GetMapping("/getListUser")
	public String ListUser() {
		System.out.println(userRepository.findAll());
//		System.out.println("Value of key loda: " + template.opsForValue().get("1"));
		
		return "get list users";
	}

	@RequestMapping("/findUser")
	public Optional<User> findUser(@RequestParam int id) {
		// TODO: process POST request
		System.out.println(userRepository.findById(id));
		;
		return userRepository.findById(id);
	}

	@RequestMapping("/findAllUser")
	public List<User> findAllUser() {
		// TODO: process POST request
		System.out.println(userRepository.findAll());
		;
		return userRepository.findAll();
	}

	@RequestMapping("/deleteUser")
	public String deleteUser(@RequestParam int IdUser) {
		// TODO: process POST request
		userRepository.deleteById(IdUser);
		System.out.println("delete successful!!");
		return "delete user by id";
	}

	@RequestMapping("/AddUser")
	public String AddUser(@RequestBody User user) {
		// TODO: process POST request
		userRepository.save(user);
		System.out.println("Add successfull");
		return "Add user successful";
	}

}
