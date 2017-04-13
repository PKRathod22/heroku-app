package com.pk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pk.repository.CustomerRepository;



@RestController
public class MyRestController {

	@Autowired
	CustomerRepository repository;
	
	@RequestMapping(value="/check", method=RequestMethod.GET)
	public String m1(){
		return "Working well";
	}

	
	
	
}
