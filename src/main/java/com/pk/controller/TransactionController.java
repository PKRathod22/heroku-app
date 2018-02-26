package com.pk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	@GetMapping("/get/id/{id}")
	public BaseDto getById(@PathVariable("id") Long id) {
		return transactionService.getById(id);
	}
	
	@GetMapping("/getall")
	public BaseDto getAll() {
		return transactionService.getAll();
	}
	
	@PostMapping("/send/money")
	public BaseDto sendMoneyToUser(@RequestBody UserTransaction userTransaction) {
		return transactionService.sendMoneyToUser(userTransaction);
	}
	
	
	
}
