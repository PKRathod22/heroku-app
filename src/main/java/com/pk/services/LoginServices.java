package com.pk.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pk.dto.BaseDto;
import com.pk.exception.ErrorCode;
import com.pk.exception.RestException;
import com.pk.model.NewUser;
import com.pk.model.UserLogin;
import com.pk.repository.UserLoginRepository;
import com.pk.util.AppUtil;
import com.pk.validator.UserLoginValidator;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class LoginServices {
	
	@Autowired
	AppUtil appUtil;
	
	@Autowired
	UserLoginRepository loginRepository;
	
	@Autowired
	UserLoginValidator userLoginValidator;
	
	
	
	public BaseDto login(NewUser exist,HttpServletRequest request){
		BaseDto dto = new BaseDto();
		try{
			NewUser userExist = loginRepository.findByMobileAndPassword(exist.getMobile(),exist.getPassword());
			userLoginValidator.loginValidation(userExist);	
			log.info("user LoggedIn Successfully");
			dto.setResponseCode(ErrorCode.SUCCESS);
			dto.setResponseObject(userExist);			
			
		}catch (RestException exception) {
			log.error("Exception Occured with error code ", exception);
			dto.setResponseCode(exception.getMessage());
		} catch (Exception e) {
			log.error("Exception ", e);
			dto.setResponseCode(ErrorCode.UNAUTHORIZED_USER);
		}

		return appUtil.setDesc(dto);
	}
	
	
	
	public BaseDto create(UserLogin userLogin){

		BaseDto dto = new BaseDto();
		try{
			userLoginValidator.createAccountvalidation(userLogin);
			UserLogin saved =loginRepository.save(userLogin);
			dto.setResponseCode(ErrorCode.SUCCESS);
			dto.setResponseObject(saved);			
			if(saved.getNewUser().getId()==null){
				throw new RestException(ErrorCode.SIGNUP_FAILED);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return appUtil.setDesc(dto);
	}
	
	public BaseDto getAll(){
		BaseDto dto = new BaseDto();
		
   try{
	List<UserLogin> pl =   loginRepository.findAll();
	dto.setResponseObject(pl);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return appUtil.setDesc(dto);
	}
	
	
	public void logout(HttpServletRequest request) {

		log.info("Logout Method is Invoked");

		HttpSession session = request.getSession(false);

		if (session != null) {

			session.setAttribute("LOGOUTSTATUS", "USER_LOGOUT");

			session.invalidate();

		} else {

			log.info("Session is already invalidated");
		}
	}
}
