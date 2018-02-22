package com.pk.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pk.exception.ErrorCode;
import com.pk.model.BaseDto;
import com.pk.model.UserMessage;
import com.pk.repositoy.UserMessageRepository;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class MessageServices {
	
	@Autowired
	UserMessageRepository userMessageRepository;
	
	public BaseDto sendEnquiry(UserMessage message){
		BaseDto baseDto = new BaseDto();
		try {
			log.info("Message obj :::::"+message);
			userMessageRepository.save(message);
			baseDto.setErrorDescription(ErrorCode.SUCCESS);
			log.info("Message send successfully :::::"+message);

		} catch (Exception e) {
			baseDto.setErrorDescription(ErrorCode.FAILED);
			log.info("found exception in sending enwuiry");
		}
				
		return baseDto;
	}
}
