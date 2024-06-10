package com.akshay.hotelservice.service;

import java.util.List;

import com.akshay.hotelservice.entities.Hotel;

public interface HotelService 
{
	//create
	Hotel create(Hotel create);
	
	//get all
	List<Hotel> getAllUser();
	
	//get one hotel
	Hotel get(String id);
}
