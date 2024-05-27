package com.example.demo;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class ControlerDemo {

	@GetMapping("/")
	public String getMethodName() {
		return "Hello World";
	}
	
}
