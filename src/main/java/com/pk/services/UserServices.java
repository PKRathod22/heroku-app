package com.pk.services;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.pk.exception.ErrorCode;
import com.pk.exception.RestException;
import com.pk.model.BaseDto;
import com.pk.model.SequenceConfig;
import com.pk.model.SequenceName;
import com.pk.model.UserMaster;
import com.pk.repositoy.SequenceRepository;
import com.pk.repositoy.UserRepository;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class UserServices {

	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	SequenceRepository sequenceRepository;
	
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
	
	public void generateDistributerId(UserMaster user) {
		if (user.getUserName() != null) {
			String fullName = user.getUserName();
			String firstTwo = fullName.substring(0, 2);
			SequenceConfig sequenceConfig = sequenceRepository.findBySequenceName(SequenceName.GEN_DISTRIBUTED_ID.toString());
			if (sequenceConfig == null) {
				log.error("sequence is null");
				throw new RestException(ErrorCode.FAILED);
			}
			sequenceConfig.setCurrentValue(sequenceConfig.getCurrentValue() + 1);
			sequenceRepository.save(sequenceConfig);
			String generateddistributeId = String.format("%s%04d", firstTwo.toUpperCase().trim(),
					sequenceConfig.getCurrentValue());
			log.info(" Distributed ID Generated Code ::  " + generateddistributeId);
			user.setDistributerId(generateddistributeId);
		}

	}
	
	public void validateNewJoinee(UserMaster user) {
		if (user.getSponsorID() == null) {
			user.setDesignation("NEW JOINEE");
			user.setStatus("BLOCKED");
		} else if (user.getDistributerId() != null && user.getSponsorID() != null)
			user.setDesignation("REFERAL JOINEE");
		if (user.getPaymentStatus() !=null && user.getPaymentStatus() == true)
			user.setStatus("ACTIVE");
		else
			user.setStatus("INPROGRESS");
		user.setCreatedDate(new Date());
	}

	public BaseDto registor(UserMaster user){
		BaseDto baseDto = new BaseDto();
		log.info("register start");
		try {
			validateNewJoinee(user);
			generateDistributerId(user);
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
			if(user.getId()!=null && user.getDistributerId() !=null){
				UserMaster exsistUser = userRepository.findByDistributerId(user.getDistributerId());
				if(exsistUser.getVersion()!=null)
				user.setVersion(exsistUser.getVersion());
			}
			UserMaster updateUser=	userRepository.save(user);
			baseDto.setErrorDescription(ErrorCode.SUCCESS);
			baseDto.setResponseContent(updateUser);
			log.info("user updated successfully of Distribute Id : "+updateUser.getDistributerId());
		} catch (ObjectOptimisticLockingFailureException e) {
			log.error("Exception in  -> saveOrUpdate method ", e);
			baseDto.setErrorDescription(ErrorCode.CANNOT_UPDATE_LOCKED_RECORD);
		}catch (Exception e) {
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
