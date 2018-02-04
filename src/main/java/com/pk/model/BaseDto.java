package com.pk.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class BaseDto implements Serializable {
	
	private static final long serialVersionUID = -2565153017469742242L;

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
