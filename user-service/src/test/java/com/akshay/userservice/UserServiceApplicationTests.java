package com.akshay.userservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.akshay.userservice.entities.Rating;
import com.akshay.userservice.service.external.services.RatingService;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired
	private RatingService ratingService;
	
//	@Test
//	void createService()
//	{
//		Rating rating = Rating.builder().rating(10).userId("").hotelId("").feedback("this is created using feign client").build();
//		Rating saveRating = ratingService.createRating(rating);
//		log.info("new rating created");
//	}

}
