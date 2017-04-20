package com.pk.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.pk.enumeration.LovStatus;
import com.pk.enumeration.Role;
import com.pk.exception.ErrorCode;
import com.pk.model.NewUser;
import com.pk.model.UserLogin;
import com.pk.util.ValidateUtil;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class UserLoginValidator {
	
	Calendar cal= Calendar.getInstance();
	String dateInString = new java.text.SimpleDateFormat("EEEE, dd/MM/yyyy/hh:mm:ss")
	        .format(cal.getTime());
	SimpleDateFormat formatter = new SimpleDateFormat("EEEE, dd/MM/yyyy/hh:mm:ss");
	
	public void loginValidation(NewUser exist) throws ParseException{
		Date parsedDate = formatter.parse(dateInString);
		ValidateUtil.notNull(exist.getUserName(), ErrorCode.USERNAME_NOTNULL);
		ValidateUtil.notNull(exist.getPassword(), ErrorCode.USERPASS_NOTNULL);
		
		if(exist.getUpdatedDate()== null || exist.getUpdatedDate()!= null){
			exist.setUpdatedDate(parsedDate);
		}
		
	}
	
	public void createAccountvalidation(UserLogin userLogin) throws ParseException {
		
		if(userLogin.getRole()==null && userLogin.getStatus()==null){
			userLogin.setRole(Role.USER);
			userLogin.setStatus(LovStatus.Active);
		}
		validateNewUser(userLogin);
		
	}
	
	private void validateNewUser(UserLogin userLogin) throws ParseException{
		Date parsedDate = formatter.parse(dateInString);
		
		   
		ValidateUtil.notNull(userLogin.getNewUser().getUserName(), ErrorCode.USERNAME_NOTNULL);
		ValidateUtil.notNull(userLogin.getNewUser().getPassword(), ErrorCode.USERPASS_NOTNULL);
		ValidateUtil.notNull(userLogin.getNewUser().getEmail(), ErrorCode.EMAIL_NOTNULL);
		ValidateUtil.notNull(userLogin.getNewUser().getMobile(), ErrorCode.MOBILE_NOTNULL);

		if(userLogin.getNewUser().getStatus()==null){
			userLogin.getNewUser().setStatus(LovStatus.Active);
		}
		
		if(userLogin.getNewUser().getCreatedDate()== null){
			userLogin.getNewUser().setCreatedDate(parsedDate);
		}
		
		
		
	}
	
	
	
}
