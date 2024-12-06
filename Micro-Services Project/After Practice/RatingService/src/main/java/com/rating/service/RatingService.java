package com.rating.service;

import java.util.List;

import com.rating.entities.Rating;

public interface RatingService {
	
	// create
	Rating craete(Rating rating);
	
	// get all rating
	List<Rating> getRating();
	
	// get all ById user
	List<Rating> getRatingByUserId(String rating);
	
	// get all by hotel
	List<Rating> getRatingByHotelId(String hotelId);
}
