package com.akshay.userservice.service;

import java.util.List;

import com.akshay.userservice.entities.User;

public interface UserService 
{
	User saveUser(User user);
	
	List<User> getAllUser();
	
	User getUser(String userId);
}
