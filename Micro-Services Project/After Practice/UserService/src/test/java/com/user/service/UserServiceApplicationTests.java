package com.user.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.user.service.entities.Rating;
import com.user.service.external.service.RatingService;

@SpringBootTest
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}

//	@Autowired
//	private RatingService ratingService;
//
//
//	@Test
//	void craeteRating() {
//		Rating rating = Rating.builder().rating(10).userId("").hotelId("").feedback("This us created using feign lient").build();
//			 ResponseEntity<Rating> createRating = ratingService.createRating(rating);
//				System.out.println("new rating created");
//	}
	
}
