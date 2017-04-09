package com.pk.controller;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Service
public class MyRestController {

	@RequestMapping(value="/hello", method=RequestMethod.GET)
	public String m1(){
		return "hello app";
	}
}
