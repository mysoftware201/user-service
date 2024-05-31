package com.akshay.ratingservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akshay.ratingservice.entities.Rating;
import com.akshay.ratingservice.repository.RatingRepository;

@Service
public class RatingServiceImpl implements RatingService
{
	@Autowired
	private RatingRepository ratingRepository;
	
	@Override
	public Rating create(Rating rating) 
	{
		return ratingRepository.save(rating);
	}

	@Override
	public List<Rating> getAllRatings() 
	{
		return ratingRepository.findAll();
	}

	@Override
	public List<Rating> getRatingByUserId(String userId) 
	{
		return ratingRepository.findByUserId(userId);
	}

	@Override
	public List<Rating> getRatingByHotelId(String hotelId) 
	{
		return ratingRepository.findByHotelId(hotelId);
	}

}
