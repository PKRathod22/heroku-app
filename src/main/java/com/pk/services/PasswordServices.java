package com.pk.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pk.dto.BaseDto;
import com.pk.model.Password;
import com.pk.repository.PasswordRepository;
import com.pk.util.AppUtil;

@Service
public class PasswordServices {
	@Autowired
	PasswordRepository passwordRepository;
	
	@Autowired
	AppUtil appUtil;
	
	public BaseDto getAll(){
		BaseDto dto = new BaseDto();
		
try{
	List<Password> pl = (List<Password>) passwordRepository.findAll();
	dto.setResponseObject(pl);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return appUtil.setDesc(dto);
	}
	
	public BaseDto passGet(Password pass){
		BaseDto dto = new BaseDto();
		try{
			pass = passwordRepository.save(pass);
			dto.setResponseObject(pass);
		}catch(Exception e){
			e.printStackTrace();
		}
		return appUtil.setDesc(dto);
	}
	
}
