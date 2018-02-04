package com.pk.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
public class BaseDto{
	
	@Getter @Setter int totalRecords =  0;
	
	@Getter @Setter String responseCode;
	
	@Getter @Setter String responseDescription;

	@Getter @Setter Object responseObject;
	
	@Getter @Setter List<?> responseObjects = new ArrayList<>();
	
	public BaseDto(){

	}
	
}
