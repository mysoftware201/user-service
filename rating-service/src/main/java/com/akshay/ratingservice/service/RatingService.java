package com.akshay.ratingservice.service;

import java.util.List;

import com.akshay.ratingservice.entities.Rating;

public interface RatingService 
{
	//create
	Rating create(Rating rating);
	
	//get all ratings
	List<Rating> getAllRatings();
	
	//get rating id by userId
	List<Rating> getRatingByUserId(String userId);
	
	//get rating by hotel id
	List<Rating> getRatingByHotelId(String hotelId);
	
}
