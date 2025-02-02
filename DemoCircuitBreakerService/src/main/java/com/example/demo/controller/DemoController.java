package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/d1")
public class DemoController {

	@Autowired
	private CircuitBreakerFactory circuitBreakerFactory;
//	@Autowired

	@Autowired
	private RestTemplate restTemplate = new RestTemplate();

	@GetMapping("/getDemoCallService")
	public String getMethodName() {
		CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");
		return circuitBreaker.run(() -> {
			// Gọi dịch vụ khác ở đây
			restTemplate.getForObject("http://localhost:5819/api/v1/getProduct", String.class);
			return "Kết quả từ dịch vụ khác";
		}, throwable -> {
			// Xử lý khi dịch vụ gặp lỗi
			return "Lỗi khi gọi dịch vụ khác";
		});
	}

	private int counter = 0;

	@GetMapping("/retryexample")
	@Retryable(maxAttempts = 3, backoff = @Backoff(delay = 1000)) // Thử lại tối đa 3 lần
	public String retryExample() {
		// Gọi một API hoặc thực hiện một tác vụ có thể gặp lỗi tạm thời
		// Ví dụ: Kết nối đến một dịch vụ ngoại vi
		// Nếu gặp lỗi, Spring Retry sẽ thử lại phương thức này

		// Giả sử sau 3 lần thử lại, phương thức vẫn không thành công
		// Thì Spring Retry sẽ ném ra ngoại lệ (ví dụ: MaxAttemptsExceededException)
		counter++;
		System.out.println("Thử lại lần thứ " + counter);
		restTemplate.getForObject("http://localhost:5819/api/v1/getProduct", String.class);
		return "Thành công!";
	}

	@Recover
	public ResponseEntity<String> fallBack(Exception e) {
		counter = 0;
		return new ResponseEntity<String>("Service is down", org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
