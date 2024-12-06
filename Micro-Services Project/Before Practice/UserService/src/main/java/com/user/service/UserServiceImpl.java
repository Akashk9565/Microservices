package com.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.user.entities.User;


@Service
public class UserServiceImpl implements UserService{

	List<User> list = List.of(
			new User(1001L, "Akash Kumar", "798646498"),
			new User(1002L, "Animesh Banik", "952568494"),
			new User(1003L, "Ravina Banik", "798646498"),
			new User(1004L, "Ratan Banik", "7976448"),
			new User(1005L, "Mahesh Banik", "973648461")
			);
	
	
	
	@Override
	public User getUser(Long id) { 
		return list.stream().filter(user->user.getUserId().equals(id)).findAny().orElse(null);
	}

}