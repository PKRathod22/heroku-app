package com.pk.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pk.model.BaseDto;
import com.pk.model.UserMaster;
import com.pk.repositoy.UserRepository;

@Service
public class UserServices {

	
	@Autowired
	UserRepository userRepository;
	
	
	public BaseDto getAll(){
		BaseDto baseDto = new BaseDto();
		 List<UserMaster> user = userRepository.findAll();
		 baseDto.setResponseObjects(user);
		return baseDto;
		
	}
	
}
