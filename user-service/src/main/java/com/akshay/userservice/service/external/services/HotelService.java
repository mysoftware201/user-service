package com.akshay.userservice.service.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.akshay.userservice.entities.Hotel;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelService 
{
	@GetMapping("/hotel/{hotelId}")
	public Hotel getHotel(@PathVariable("hotelId") String hotelId);

}
