package com.pk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pk.model.BaseDto;
import com.pk.model.UserTransaction;
import com.pk.services.TransactionService;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequestMapping("user/transaction")
public class TransactionController {

	@Autowired
	TransactionService transactionService;
	
    @RequestMapping(value="/get/id/{distributerId}",method=RequestMethod.GET)
	public BaseDto getById(@PathVariable("distributerId") String distributerId) {
		return transactionService.getById(distributerId);
	}
	
    @RequestMapping(value="/getall",method=RequestMethod.GET)
	public BaseDto getAll() {
		return transactionService.getAll();
	}
	
    @RequestMapping(value="/send/money",method=RequestMethod.POST)
	public BaseDto sendMoneyToUser(@RequestBody UserTransaction userTransaction) {
		return transactionService.sendMoneyToUser(userTransaction);
	}
	
	
	
}
