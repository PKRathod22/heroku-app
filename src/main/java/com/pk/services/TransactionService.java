package com.pk.services;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pk.enumeration.TransactionType;
import com.pk.exception.ErrorCode;
import com.pk.model.BaseDto;
import com.pk.model.UserMaster;
import com.pk.model.UserTransaction;
import com.pk.repositoy.UserRepository;
import com.pk.repositoy.UserTransactionRepository;

import lombok.extern.log4j.Log4j2;


@Service
@Log4j2
public class TransactionService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserTransactionRepository userTransactionRepository;
	
	public BaseDto getAll(){
		log.info("getall transaction details of ID ::::");
		BaseDto baseDto  = new BaseDto();
		try {
			List<UserTransaction> userTransactionList = userTransactionRepository.findAll();
			baseDto.setErrorDescription(ErrorCode.SUCCESS);
			baseDto.setResponseContents(userTransactionList);
		} catch (Exception e) {
			baseDto.setErrorDescription(ErrorCode.FAILED);
			log.error("found exception while getll  of transaction list:::",e);
		}
		
		return baseDto;
	}
	
	public BaseDto getById(String distributerId){
		log.info("get by id transaction details of distributerId ::::"+distributerId);
		BaseDto baseDto  = new BaseDto();
		try {
			List<UserTransaction> userTransactionList = userTransactionRepository.findByUserId(distributerId);
			baseDto.setErrorDescription(ErrorCode.SUCCESS);
			baseDto.setResponseContents(userTransactionList);
		} catch (Exception e) {
			baseDto.setErrorDescription(ErrorCode.FAILED);
			log.error("found exception while getbyid  of transaction:::",e);
		}
		
		return baseDto;
	}
	
	
	public BaseDto sendMoneyToUser(UserTransaction userTransaction){
		log.info("Send Money Transaction start ..");
		BaseDto baseDto  = new BaseDto();
		try {
			secureTransaction(userTransaction);
			baseDto.setErrorDescription(ErrorCode.SUCCESS);
		} catch (Exception e) {
			baseDto.setErrorDescription(ErrorCode.FAILED);
			log.error("found exception while sending money to user :::",e);
		}
		
		return baseDto;
	
	}
	
	
	@Transactional
	public void secureTransaction(UserTransaction userTransaction){
		if(userTransaction!=null && userTransaction.getDistributerId()!=null ){
			userTransaction.setDistributerId(userTransaction.getDistributerId());
			userTransaction.setTransactionDate(new Date());
			if(userTransaction.getTransactionType()==TransactionType.Credited){
				userTransaction.setAmountSign("+");	
			}else if(userTransaction.getTransactionType()==TransactionType.Debited){
				userTransaction.setAmountSign("-");	
			}
			if(userTransaction.getTransactionMode().equalsIgnoreCase("NetBanking")){
				UserMaster userMaster = userRepository.findByDistributerId(userTransaction.getDistributerId());
				userTransaction.setBankName(userMaster.getBankName());
				userTransaction.setAccountNumber(userMaster.getAccountNo());
			}
			
			UserTransaction savedTransaction = userTransactionRepository.save(userTransaction);
			log.info("Send Money Transaction succesfull.."+savedTransaction);
		}else{
			log.info("Send Money Transaction failed ..");
		}
	}
}
