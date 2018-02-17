package com.pk.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pk.model.BaseDto;
import com.pk.model.UserMaster;
import com.pk.services.UserServices;

import lombok.extern.log4j.Log4j;

@RestController
@Log4j
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserServices userService;

	@PostMapping("/register/create")
	public ResponseEntity<BaseDto> register(@RequestBody UserMaster user) {
		BaseDto baseDto = new BaseDto();
		baseDto = userService.registor(user);
		return new ResponseEntity<BaseDto>(baseDto, HttpStatus.OK);
	}

	@GetMapping("/get/id/{distributerId}")
	public ResponseEntity<BaseDto> getById(@PathVariable("distributerId") String distributerId) {
		BaseDto baseDto = new BaseDto();
		baseDto = userService.getById(distributerId);
		return new ResponseEntity<BaseDto>(baseDto, HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<BaseDto> login(@RequestBody UserMaster userMaster, HttpServletRequest request) {
		BaseDto baseDto = new BaseDto();
		baseDto = userService.login(userMaster, request);
		return new ResponseEntity<BaseDto>(baseDto, HttpStatus.OK);
	}

	@GetMapping("/logout")
	public void logout(HttpServletRequest request) {
		log.info("Logout method is called.");
		userService.logout(request);
	}

	@PutMapping("/update")
	public ResponseEntity<BaseDto> updateUser(@RequestBody UserMaster user) {
		BaseDto baseDto = new BaseDto();
		baseDto = userService.updateUser(user);
		return new ResponseEntity<BaseDto>(baseDto, HttpStatus.OK);
	}

}
