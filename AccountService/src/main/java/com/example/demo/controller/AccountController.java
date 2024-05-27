package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.entity.Account;
import com.example.demo.repository.AccountRepository;
import com.example.demo.util.JWTUtil;


@RequestMapping("/api/v3")
@RestController
public class AccountController {
	private int counter = 0;
	@Autowired
	private JWTUtil jwtUtil;
	@Autowired
	private RedisTemplate template;
	@Autowired
	private AccountRepository accountRepository;
	private String token;
	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping("/AddAccount")
	@Retryable(maxAttempts = 3, backoff = @Backoff(delay = 1000))
	public String AddAccount(@RequestBody Account account) {
		// TODO: process POST request
		restTemplate.getForObject("http://localhost:5821/api/v2/findUser?id=" + account.getIdUser(), String.class);
		System.out.println(counter);
//		accountRepository.save(account);
		System.out.println("Add successfull");
		counter++;
		return "Add Account successful";
	}
	
	@GetMapping("/addAccount")
	@Retryable(maxAttempts = 3, backoff = @Backoff(delay = 1000)) // Thử lại tối đa 3 lần
	public String retryExample(@RequestBody Account account) {
		counter++;
		System.out.println("Thử lại lần thứ " + counter);
		String rs = restTemplate.getForObject("http://localhost:5821/api/v2/findUser?id=" + account.getIdUser(), String.class);
		System.out.println("Rs: " + rs);
		if(rs.isBlank()) {
			accountRepository.save(account);
			System.out.println("Add successfull");
		}
		return "Thành công!";
	}

	@PostMapping("/login")
	@Retryable(maxAttempts = 3, backoff = @Backoff(delay = 1000))
	public Optional<Account> findOrder(@RequestParam int IdAccount) {
		// TODO: process POST request
		System.out.println(accountRepository.findById(IdAccount));
		token = jwtUtil.generateToken(String.valueOf(IdAccount));
		System.out.println("Token" + token);
		HashOperations hashOperations = template.opsForHash();
		hashOperations.put("token", String.valueOf(IdAccount), token);

		return accountRepository.findById(IdAccount);
	}
	
	

	@PostMapping("/findAllAccount")
	public List<Account> findAllAccount() {
		// TODO: process POST request
		System.out.println(accountRepository.findAll());
		;
		return accountRepository.findAll();
	}

}
