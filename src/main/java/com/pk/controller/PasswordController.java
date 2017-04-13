package com.pk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pk.dto.BaseDto;
import com.pk.model.Password;
import com.pk.services.PasswordServices;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequestMapping(value="/api/v1")
public class PasswordController {

	@Autowired
	PasswordServices passwordServices;
	
	@RequestMapping(value="/getall", method=RequestMethod.GET)
	public BaseDto getAll(){
		log.info("password getall controller called..");
		return passwordServices.getAll();
	}
	
	@RequestMapping(value="/pass", method=RequestMethod.POST)
	public BaseDto passwordProtect(Password pass){
		log.info("password save method called");
		return passwordServices.passGet(pass);
	}
	
	
	
}
