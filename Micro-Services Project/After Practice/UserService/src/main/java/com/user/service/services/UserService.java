package com.user.service.services;

import java.util.List;

import com.user.service.entities.User;

public interface UserService {

	// create
	User createUser(User user);

	// get all
	List<User> getAll();

	// get single
	User getUserById(String userId);

	// update user

	User updateUser(User user, String userId);

	// delete user

	void deleteUser(String userId);

}
