package com.user.service.services.Impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.user.service.entities.Hotel;
import com.user.service.entities.Rating;
import com.user.service.entities.User;
import com.user.service.exception.ResourceNotFoundException;
import com.user.service.external.service.HotelService;
import com.user.service.repository.UserRepository;
import com.user.service.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private HotelService hotelService;

	@Autowired
	private RestTemplate restTemplate;

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public User createUser(User user) {
		// generate unique userId
		String string = UUID.randomUUID().toString();
		user.setUserId(string);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAll() {

 		 List<User> findAll = userRepository.findAll();
 		 
 		 
 		 
 		 return findAll;
	}

	@Override
	public User getUserById(String userId) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with given id is not found on server !!"+userId));
		
		// fetch rating of above user from RATING SERVICE 
		//http://http://localhost:8083/ratings/users/f69c2386-c1d5-4e95-90fa-ee310656b144
		
		Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), Rating[].class);
		logger.info("{}", ratingsOfUser);
		
		List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();
		
		
		List<Rating> ratingList = ratings.stream().map(rating -> {
			
			// api call to hotel service to get the hotel
			//http://localhost:8082/hotels/c83e8357-1995-4818-b4dc-49e45752b808
			System.out.println(rating.getHotelId());
			
			//ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
			Hotel hotel = hotelService.getHotel(rating.getHotelId());
			
			//logger.info("response status code: {}", forEntity.getStatusCode());
			
			// set the hotel to rating
			rating.setHotel(hotel);
			// return the rating
			return rating;
		}).collect(Collectors.toList());
		
		user.setRating(ratingList);
		
		return user;
		
	}

	@Override
	public User updateUser(User user, String userId) {
		User save = userRepository.findById(userId).orElseThrow(
				() -> new ResourceNotFoundException("User with given id is not found on server !!" + userId));

		save.setName(user.getName());
		save.setEmail(user.getEmail());
		save.setAbout(user.getAbout());
		User savedUser = userRepository.save(user);
		return savedUser;
	}

	@Override
	public void deleteUser(String userId) {
		User user = userRepository.findById(userId).orElseThrow(
				() -> new ResourceNotFoundException("User with given id is not found on server !!" + userId));
		userRepository.delete(user);
	}

}
