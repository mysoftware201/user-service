package com.akshay.userservice.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.akshay.userservice.entities.User;
import com.akshay.userservice.service.UserService;
import com.fasterxml.jackson.core.StreamReadConstraints.Builder;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController 
{
	@Autowired
	private UserService userService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	//create
	@PostMapping("/create")
	public ResponseEntity<User> createUser(@RequestBody User user)
	{
		User user1 = userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(user1);
	}
	
	
	
	int retryCount = 1;
	//single user get
	@GetMapping("/{userId}")
	//@CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
	//@Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallback")
	@RateLimiter(name = "userRateLimiter", fallbackMethod = "ratingHotelFallback")
	public ResponseEntity<User> getSingleUser(@PathVariable String userId)
	{
		log.info("Get single user handler: UserController");
		log.info("retry count {} : " + retryCount);
		retryCount++;
		User user = userService.getUser(userId);
		return ResponseEntity.ok(user);
	}
	
	
	//creating fallback method for circuit breaker
	public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex)
	{
		log.info("fallback method is executed because service is down : " + ex.getMessage());
		User user = User.builder()
			.email("dummy@gmail.com")
			.name("dummy")
			.about("this user is created dummy because some services down")
			 .userId("12345")
			 .build();
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	
	
	
	
	//all user get
	@GetMapping("/getAllUser")
	public ResponseEntity<List<User>> getAllUser()
	{
		List<User> user = userService.getAllUser();
		return ResponseEntity.ok(user);
	}
	
	
	
	
	
}
