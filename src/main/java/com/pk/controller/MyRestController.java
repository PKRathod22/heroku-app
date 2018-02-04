
package com.pk.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MyRestController {

	
	@RequestMapping(value="/check", method=RequestMethod.GET)
	public String m1(){
		return "Working well";
	}

	
	
	
}
