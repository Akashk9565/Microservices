package com.hotel.service;

import java.util.List;

import com.hotel.entities.Hotel;

public interface HotelService {
	
	// create
	
	Hotel craeteHotel(Hotel hotel);
	
	// get all
	List<Hotel> getAll();
	
	// get ById
	Hotel getById(String id);
	
	

}
