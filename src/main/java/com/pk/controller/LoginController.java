package com.pk.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pk.dto.BaseDto;
import com.pk.model.NewUser;
import com.pk.model.UserLogin;
import com.pk.services.LoginServices;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping(value="api/v1/user")
public class LoginController {

	@Autowired
	LoginServices loginServices;
	
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public BaseDto login(@RequestBody NewUser exist,HttpServletRequest request){
		log.info("Login Controlller called..");
		
		return  loginServices.login(exist,request);
	}
	
	@RequestMapping(value="/signup",method=RequestMethod.POST)
	public BaseDto create(@RequestBody UserLogin userLogin){
		log.info("Login Controlller called..");
		
		return  loginServices.create(userLogin);
	}
	
	@RequestMapping(value="/logout",method = RequestMethod.GET)
	public void logout(HttpServletRequest request){
		log.info("Logout Controlller called..");
		loginServices.logout(request);
	}
	
	
}
