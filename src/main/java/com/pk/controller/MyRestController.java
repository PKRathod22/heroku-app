
package com.pk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pk.model.BaseDto;
import com.pk.services.UserServices;


@RestController
public class MyRestController {

	@Autowired
	UserServices userService;
	
	@RequestMapping(value="/getall", method=RequestMethod.GET)
	public BaseDto getall(){
		return userService.getAll();
	}

	
	
	
}
