package com.akshay.hotelservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akshay.hotelservice.entities.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, String>
{

}
