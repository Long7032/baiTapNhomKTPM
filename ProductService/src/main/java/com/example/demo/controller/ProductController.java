package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

	@Autowired
	private ProductRepository productRepository;

	@GetMapping("/findProduct")
	public Optional<Product> findProduct(@RequestParam int IdProduct) {
		// TODO: process POST request
		System.out.println(productRepository.findById(IdProduct));
		;
		return productRepository.findById(IdProduct);
	}

	@PostMapping("/findAllProduct")
	public List<Product> findAllProduct() {
		// TODO: process POST request
		System.out.println(productRepository.findAll());
		;
		return productRepository.findAll();
	}

	@PostMapping("/deleteProduct")
	public String deleteProduct(@RequestParam int IdProduct) {
		// TODO: process POST request
		productRepository.deleteById(IdProduct);
		System.out.println("delete successful!!");
		return "delete product by id";
	}

	@PostMapping("/AddProduct")
	public String AddProduct(@RequestBody Product product) {
		// TODO: process POST request
		productRepository.save(product);
		System.out.println("Add successfull");
		return "Add product successful";
	}

	@PostMapping("/UpdateProduct")
	public String UpdateProduct(@RequestParam int IdProduct, @RequestBody Product product) {
		// TODO: process POST request
		productRepository.deleteById(IdProduct);
		productRepository.save(product);
		System.out.println("Update successfull");
		return "Update product successful";
	}

}
