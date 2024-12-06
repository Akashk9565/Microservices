package com.contact.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.contact.entity.Contact;

@Service
public class ContactServiceImpl implements ContactService {

	List<Contact> list = List.of(
				new Contact(1L, "akash@gmail.com", "Akash", 1001L),
				new Contact(2L, "animesh@gmail.com", "Animesh", 1001L), 
				new Contact(3L, "ani@gmail.com", "Ani", 1001L), 
				new Contact(4L, "Ravina@gmail.com", "Ravina", 1002L), 
				new Contact(5L, "ruby@gmail.com", "ruby", 1002L), 
				new Contact(6L, "beby@gmail.com", "beby", 1002L), 
				new Contact(7L, "ratan@gmail.com", "Ratan", 1003L), 
				new Contact(8L, "Ratanbanik@gmail.com", "Ratan Banik", 1003L), 
				new Contact(9L, "Mahesh@gmail.com", "Mahesh", 1004L)
			);
	
	@Override
	public List<Contact> getContactOfUser(Long userId) { 
		return list.stream().filter(contact->contact.getUserId().equals(userId)).collect(Collectors.toList());
	}

}