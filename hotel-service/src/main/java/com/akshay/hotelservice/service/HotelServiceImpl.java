package com.akshay.hotelservice.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akshay.hotelservice.entities.Hotel;
import com.akshay.hotelservice.exceptions.ResourceNotFoundException;
import com.akshay.hotelservice.repository.HotelRepository;

@Service
public class HotelServiceImpl implements HotelService
{
	@Autowired
	private HotelRepository hotelRepository;

	@Override
	public Hotel create(Hotel create) 
	{
		String id = UUID.randomUUID().toString();
		create.setId(id);
		return hotelRepository.save(create);
	}

	@Override
	public List<Hotel> getAllUser() 
	{
		return hotelRepository.findAll();
	}

	@Override
	public Hotel get(String id) 
	{
		return hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(""));
	}

}
