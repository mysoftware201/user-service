package com.akshay.userservice.serviceimpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.akshay.userservice.entities.Hotel;
import com.akshay.userservice.entities.Rating;
import com.akshay.userservice.entities.User;
import com.akshay.userservice.exceptions.ResourceNotFoundException;
import com.akshay.userservice.repository.UserRepository;
import com.akshay.userservice.service.UserService;
import com.akshay.userservice.service.external.services.HotelService;
import com.akshay.userservice.service.external.services.RatingService;

import jakarta.ws.rs.core.Cookie;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;

@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HotelService hotelService;
	
	@Autowired
	private RatingService ratingService;

	private Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	public User saveUser(User user) 
	{
		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() 
	{
		return userRepository.findAll();
	}

	@Override
	public User getUser(String userId)
	{
		 User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id not found on server : " + userId));
		 Rating[] ratingsofUser =  restTemplate.getForObject("http://RATING-SERVICE/ratings/user/"+user.getUserId(), Rating[].class);
	//	 Rating[] ratingofUser = ratingService.getRating(userId);
		 log.info("{details}", ratingsofUser);
		 System.out.println("rating details" + ratingsofUser.toString());
		 List<Rating> ratings = Arrays.stream(ratingsofUser).toList();
		 List<Rating> ratingList = ratings.stream().map(rating ->
		 {
			  Hotel hotel = hotelService.getHotel(rating.getHotelId());
			  log.info("{} Response status code : " + hotel);
			  rating.setHotel(hotel);
			  return rating;
		  }).collect(Collectors.toList());
		  
		  user.setRatings(ratingList);
		  return user;
	}
}