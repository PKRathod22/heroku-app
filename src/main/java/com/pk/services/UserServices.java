package com.pk.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pk.exception.ErrorCode;
import com.pk.model.BaseDto;
import com.pk.model.UserMaster;
import com.pk.repositoy.UserRepository;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class UserServices {

	
	@Autowired
	UserRepository userRepository;
	
	public BaseDto registor(UserMaster user){
		BaseDto baseDto = new BaseDto();
		log.info("register start");
		try {
			userRepository.save(user);
			baseDto.setErrorDescription(ErrorCode.SUCCESS);
			log.info("register successfully");
		} catch (Exception e) {
			baseDto.setErrorDescription(e.getMessage());
			log.error("found exception in register",e);
		}
		return baseDto;
	}
	public BaseDto updateUser(UserMaster user){
		BaseDto baseDto = new BaseDto();
		userRepository.save(user);
		return baseDto;
	}
	public BaseDto getAll(){
		BaseDto baseDto = new BaseDto();
		try{
		 List<UserMaster> userList = userRepository.findAll();
		 log.info("user master list "+userList);
		 baseDto.setResponseContents(userList);
		 baseDto.setTotalRecords(userList.size());
		}catch(Exception e){
			log.error("found eror in user : [",e);
			baseDto.setErrorDescription("failed");
		}
		return baseDto;
		
	}
	
}
