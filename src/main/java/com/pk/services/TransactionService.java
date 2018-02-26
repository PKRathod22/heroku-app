package com.pk.services;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pk.enumeration.TransactionType;
import com.pk.exception.ErrorCode;
import com.pk.model.BaseDto;
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
	
	public BaseDto getById(Long userId){
		log.info("get by id transaction details of ID ::::"+userId);
		BaseDto baseDto  = new BaseDto();
		try {
			List<UserTransaction> userTransactionList = userTransactionRepository.findByUserId(userId);
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
		if(userTransaction!=null && userTransaction.getUser()!=null && userTransaction.getUser().getId()!=null){
			userTransaction.setUser(userTransaction.getUser());
			userTransaction.setTransactionDate(new Date());
			if(userTransaction.getTransactionType()==TransactionType.Credited){
				userTransaction.setAmountSign("+");	
			}else if(userTransaction.getTransactionType()==TransactionType.Debited){
				userTransaction.setAmountSign("-");	
			}
			if(userTransaction.getTransactionMode().equalsIgnoreCase("NetBanking")){
				if(userTransaction.getUser().getBankName()!=null){
					userTransaction.setBankName(userTransaction.getUser().getBankName());
				}if(userTransaction.getUser().getAccountNo()!=null){
					userTransaction.setAccountNumber(userTransaction.getUser().getAccountNo());
				}
			}
			
			UserTransaction savedTransaction = userTransactionRepository.save(userTransaction);
			log.info("Send Money Transaction succesfull.."+savedTransaction);
		}else{
			log.info("Send Money Transaction failed ..");
		}
	}
}
