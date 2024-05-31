package com.akshay.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akshay.userservice.entities.User;

public interface UserRepository extends JpaRepository<User, String>
{
	
}
