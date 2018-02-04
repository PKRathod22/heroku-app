
package com.pk.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pk.model.BaseDto;
import com.pk.services.UserServices;


@RestController
@RequestMapping("/user")
public class MyRestController {

	@Autowired
	UserServices userService;

    @GetMapping("/get")
	public ResponseEntity<BaseDto> getAll(){
		BaseDto baseDto = new BaseDto();
		baseDto = userService.getAll();
		return new ResponseEntity<BaseDto>(baseDto, HttpStatus.OK);
	}

	
	
	
}
