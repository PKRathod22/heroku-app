package com.pk.services;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.pk.exception.ErrorCode;
import com.pk.exception.RestException;
import com.pk.model.BaseDto;
import com.pk.model.UserMaster;
import com.pk.repositoy.UserRepository;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class UserServices {

	
	@Autowired
	UserRepository userRepository;
	
	@Value("${session.timeout}")
	int sessionTimeout;
	
	public BaseDto login(UserMaster user,HttpServletRequest request){
		BaseDto baseDto = new BaseDto();
		try {
			if(user!=null && user.getDistributerId()!=null && user.getPassword() !=null){
			    UserMaster existingUser = userRepository.findByUserAndPassword(user.getDistributerId().trim(),user.getPassword().trim());
			    if(existingUser == null && existingUser.getDistributerId()==null){
			    	log.info("not valid user");
			    	throw new RestException(ErrorCode.FAILED);
			    }
			    existingUser.setCurrentLoginDate(new Date());
			    userRepository.save(existingUser);
			    HttpSession session = request.getSession(true);
				session.setMaxInactiveInterval(sessionTimeout);

			    existingUser.setSessionId(session.getId());
				
			    baseDto.setResponseContent(existingUser);
			    baseDto.setErrorDescription(ErrorCode.SUCCESS);
			}
		} catch (Exception e) {
			 baseDto.setErrorDescription(ErrorCode.FAILED);
           log.error("error found while login",e);
		}
		return baseDto;
	}
	
	
	public BaseDto getById(String distributerId){
		BaseDto baseDto = new BaseDto();
		log.info("get By id start Distributer  ID : "+distributerId);
		try {
		    UserMaster userMaster = userRepository.findByDistributerId(distributerId.trim());
		    baseDto.setResponseContent(userMaster);
		    baseDto.setErrorDescription(ErrorCode.SUCCESS);
			log.info("user master getbyid successfully");
		} catch (Exception e) {
			baseDto.setErrorDescription(ErrorCode.FAILED);
			log.error("found exception in user master getby id",e);
		}
		return baseDto;
	}
	
	public BaseDto registor(UserMaster user){
		BaseDto baseDto = new BaseDto();
		log.info("register start");
		try {
			user.setCreatedDate(new Date());
			userRepository.save(user);
			baseDto.setErrorDescription(ErrorCode.SUCCESS);
			log.info("register successfully");
		} catch (Exception e) {
			baseDto.setErrorDescription(ErrorCode.FAILED);
			log.error("found exception in register",e);
		}
		return baseDto;
	}
	
	public BaseDto updateUser(UserMaster user){
		BaseDto baseDto = new BaseDto();
		try {
			log.info("User object : "+user);
			user.setModifiedDate(new Date());
			UserMaster updateUser=	userRepository.save(user);
			baseDto.setErrorDescription(ErrorCode.SUCCESS);
			baseDto.setResponseContent(updateUser);
			log.info("user updated successfully of Distribute Id : "+updateUser.getDistributerId());
		} catch (Exception e) {
			baseDto.setErrorDescription(ErrorCode.FAILED);
			log.error("found exception in update user details",e);
		}
		return baseDto;
	}
	public BaseDto getAll(){
		BaseDto baseDto = new BaseDto();
		try{
		 List<UserMaster> userList = userRepository.findAll();
		 log.info("user master list "+userList);
		 baseDto.setResponseContents(userList);
		 baseDto.setTotalRecords(userList.size());
		}catch(Exception e){
			log.error("found eror in user : [",e);
			baseDto.setErrorDescription("failed");
		}
		return baseDto;
		
	}

	
	public void logout(HttpServletRequest request){

		log.debug("Logout Method is Invoked");

		HttpSession session = request.getSession(false);

		if (session != null) {

			session.setAttribute("LOGOUTSTATUS", "USER_LOGOUT");

			session.invalidate();

		} else {

			log.debug("Session is already invalidated");
		}
	}
	
}
