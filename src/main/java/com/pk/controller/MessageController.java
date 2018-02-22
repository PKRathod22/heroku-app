
package com.pk.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pk.model.BaseDto;
import com.pk.model.UserMessage;
import com.pk.services.MessageServices;


@RestController
@RequestMapping("/user/message")
public class MessageController {

	@Autowired
	MessageServices messageServices; 

    @PostMapping("/sendenquiry")
	public BaseDto sendEnquiry(@RequestBody UserMessage message){
		return messageServices.sendEnquiry(message);
	}

	
	
	
}
