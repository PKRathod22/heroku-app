package com.pk.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pk.model.BaseDto;
import com.pk.model.UserMaster;
import com.pk.repositoy.UserRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class UserServices {

	
	@Autowired
	UserRepository userRepository;
	
	
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
