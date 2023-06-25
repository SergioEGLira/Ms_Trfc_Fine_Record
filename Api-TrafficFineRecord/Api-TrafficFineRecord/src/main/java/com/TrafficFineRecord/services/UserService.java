package com.TrafficFineRecord.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TrafficFineRecord.entities.UserPartialReplica;
import com.TrafficFineRecord.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public UserPartialReplica save(UserPartialReplica user) {
		return userRepository.save(user);
	}

}
