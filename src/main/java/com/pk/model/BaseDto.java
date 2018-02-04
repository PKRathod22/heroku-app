package com.pk.model;

import java.util.List;

import lombok.Data;

@Data
public class BaseDto{
	
	int statusCode = 2000;

	String message;

	String errorDescription;

	int totalRecords = 0;
	
	Object responseContent;

	List<?> responseContents;
	
	public BaseDto(String string, String error) {
		this.message = string;
		this.errorDescription = error;
	}

	public BaseDto() {
	}
	
}
