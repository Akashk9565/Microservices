package com.user.service.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.service.entities.User;
import com.user.service.services.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	// create
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User createUser = userService.createUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(createUser);
	}

	// get all
	@GetMapping
	public ResponseEntity<List<User>> getAll() {
		List<User> all = userService.getAll();
		return ResponseEntity.ok(all);
	}

	// get ById single user
//	@GetMapping("/{userId}")
//	public ResponseEntity<User> getById(@PathVariable String userId) {
//		logger.info("Get single user handler : UserController");
//		User userById = userService.getUserById(userId);
//		return ResponseEntity.ok(userById);
//	}
	
	
	
	// --------------------------circuiteBreaker----------------------------
	
	int retryCount = 1;
	@GetMapping("/{userId}")
	//@CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallBack")
	//@Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallBack")
	@RateLimiter(name = "userRateLimiter", fallbackMethod = "ratingHotelFallBack")
	public ResponseEntity<User> getById(@PathVariable String userId) {
		logger.info("Get single user handler : UserController");
		logger.info("Retry count {}", retryCount);
		retryCount++;
		
		User user = userService.getUserById(userId);
		return ResponseEntity.ok(user);
	}
	
	// creating fall back method circuiteBreaker
	public ResponseEntity<User> ratingHotelFallBack(String userId, Exception ex){
		//logger.info("Fallback is executed becouse service is down",ex.getMessage());
		
		User user = User.builder()
		.email("dummy@gmail.com")
		.name("dummy")
		.about("This user is created dummy becouse some service is down")
		.userId("123456")
		.build();
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	
	

	// update user
	@PutMapping("/{userId}") 
	public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable String userId) {
		User updateUser = userService.updateUser(user, userId);
		return ResponseEntity.ok(updateUser);
	}

	// delete user
	@DeleteMapping("/{userId}")
	public ResponseEntity<User> deleteUser(@PathVariable String userId) {
		userService.deleteUser(userId);
		return new ResponseEntity("User Deleted Successfully", HttpStatus.OK);
	}

}
