package com.pk.util;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.pk.enumeration.LovStatus;
import com.pk.exception.RestException;

import lombok.extern.log4j.Log4j2;

/**
 * The Class Validate.
 */

@Log4j2
public class ValidateUtil {
	
	public static String regularExp = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
	

	public static void notNull(Object object, String errorCode) throws RestException {
		if (object == null) {
			throw new RestException(errorCode);
		}
	}

	public static void notEquals(Object src, Object target, String errorCode) throws RestException {
		if (!src.equals(target)) {
			throw new RestException(errorCode);
		}
	}



	public static <E> E checkPattern(E object, String regExp, String errorCode) throws RestException {
		if (object == null) {
			return null;
		}
		
		if(regExp==null){
			return null;
		}
		
		Pattern pattern = Pattern.compile(regExp);
		Matcher matcher = pattern.matcher(object.toString());
		boolean matchFound = matcher.matches();
		if (!matchFound) {
			RestException ex = new RestException(errorCode.toString());
			throw ex;
		}
		return object;
	}
	
	public static void notEmpty(@SuppressWarnings("rawtypes") Collection list, String errorCode) throws RestException {
		if (list == null || list.size() == 0) {
			throw new RestException(errorCode);
		}
	}

	public static void notNullOrEmpty(String object, String errorCode) throws RestException {
		if (object == null || object.trim().length() == 0) {
			throw new RestException(errorCode);
		}
	}


	public static void validateLength(Object object, int min, int max, String errorCode) throws RestException {

		if(object != null) {
			if (object.toString().length() > max) {
				throw new RestException(errorCode);
			}

			if (object.toString().length() < min) {
				throw new RestException(errorCode);
			}
		}
	}
	
	public static void maxAndmin(double d, double e, String errorCode) throws RestException {

		if(d>e){
			
			throw new RestException(errorCode);
			
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	

	public static void validateEnum(Class<?> enumType, Object object, String errorCode) throws RestException {
		boolean flag = false;

		if (object != null) {
			for (Object obj : enumType.getEnumConstants()) {
				if (obj.equals(object)) {
					flag = true;
					break;
				}
			}
		}
		if (flag == false) {
			throw new RestException(errorCode);
		}
	}

	public static void checkDate(Date input, String errorCode) throws RestException {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		if (!sdf.format(date).equals(sdf.format(input))) {
			throw new RestException(errorCode);
		}

	}

	public static void dateNotNull(Date date, String errorCode) throws RestException {
		if (date == null) {
			throw new RestException(errorCode);
		}
	}

	public static void dateEmpty(Date date, String errorCode) throws RestException {
		if (date.equals("")) {
			throw new RestException(errorCode);
		}
	}

	public static void checkDecimalLength(Double value, String errorCode) {
		String text = Double.toString(Math.abs(value));
		int integerPlaces = text.indexOf('.');
		int decimalPlaces = text.length() - integerPlaces - 1;
		if (integerPlaces > 5 || decimalPlaces > 6) {
			throw new RestException(errorCode);
		}
	}

	public static void nonZero(Double value, String errorCode) throws RestException {
		if (value == 0) {
			throw new RestException(errorCode);
		}
	}
	public static void nonZero(Long value, String errorCode) throws RestException {
		if (value == 0) {
			throw new RestException(errorCode);
		}
	}
	public static void nonZero(int value, String errorCode) throws RestException {
		if (value == 0) {
			throw new RestException(errorCode);
		}
	}

	public static void isValidEmail(String enteredEmail, String errorCode) throws RestException {
		log.info("enteredEmail:" + enteredEmail);
		String EMAIL_REGIX = "^[\\\\w!#$%&’*+/=?`{|}~^-]+(?:\\\\.[\\\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,6}$";
		Pattern pattern = Pattern.compile(EMAIL_REGIX);
		Matcher matcher = pattern.matcher(enteredEmail);
		if (!enteredEmail.isEmpty() && enteredEmail != null && matcher.matches()) {
			throw new RestException(errorCode);
		}
	}

	
	

	
	
	
	public static void isStatusBlocked(LovStatus status, String errorCode) {
		if(status.equals(LovStatus.Block)) {
			log.info("Status is Blocked. Throwing error code : " + errorCode);
			throw new RestException(errorCode);
		}
	}
	
	public static void isStatusHidden(LovStatus status, String errorCode) {
		if(status.equals(LovStatus.Hide)) {
			log.info("Status is Hidden. Throwing error code : " + errorCode);
			throw new RestException(errorCode);
		}
	}
	
	public static void belowRange(double value, double minValue, String errorCode) {
		if(value < minValue) {
			throw new RestException(errorCode);
		}
	}
	
	public static void aboveRange(double value, double maxValue, String errorCode) {
		if(value > maxValue) {
			throw new RestException(errorCode);
		}
	}
	
	public static void decimalLength(double object,int min, int max, String errorCode) {
		
if(object>max){
	
	throw new RestException(errorCode);
}
if(object<min){
	
	throw new RestException(errorCode);
}
		
}
	

	public static void isPortInCountry(String countryCode, String isCountry, String errorCode) {

		if(!countryCode.equals(isCountry)){
			throw new RestException(errorCode);
		}
		
	}
	
	



}
