package com.pk.dto;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.MDC;

import lombok.Getter;
import lombok.Setter;
public class BaseDto{

	@Getter @Setter String trackId;
	
	@Getter @Setter String responseCode;
	
	@Getter @Setter String responseDescription;

	@Getter @Setter Object responseObject;
	
	@Getter @Setter List<String> params = new ArrayList<String>();
	
	public BaseDto(){
		trackId = (String) MDC.get("TrackId");
	}
	
}
