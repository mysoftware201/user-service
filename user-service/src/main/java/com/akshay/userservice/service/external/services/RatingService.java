package com.akshay.userservice.service.external.services;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import com.akshay.userservice.entities.Rating;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService 
{
	@GetMapping("/ratings/user/{userId}")
	public ResponseEntity<Rating> getRating(@PathVariable("userId") String userId);
	
	//post
	@PostMapping("/ratings")
	public ResponseEntity<Rating> createRating(Rating values);
	
	//put
	@PutMapping("/ratings/{ratingId}")
	public ResponseEntity<Rating> updateRating(@PathVariable("ratingId") String ratingId, Rating rating);
	
	//delete

	@DeleteMapping("/ratings/{ratingId}")
	public void deleteRating(@PathVariable("ratingId") String ratingId);
}
