package com.pk.model;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pk.enumeration.TransactionType;

import lombok.Data;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
@Table(name="user_transaction")
public class UserTransaction implements Serializable{

	private static final long serialVersionUID = -5042929395564162855L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,generator="autogen")
	@TableGenerator(name = "autogen", initialValue = 0, allocationSize = 1)
	@Column(name = "id", updatable = false, nullable = false)
	Long id;
	
	@NotNull
	@Column(name="distributer_id")
    String distributerId;
	
	@NotNull
	@Column(name="transaction_date")
	Date transactionDate;
	
	@NotNull
	@Column(name="transaction_type")
    TransactionType transactionType;
	
	@Column(name="transaction_mode")
    String transactionMode;
	
	@Column(name="transaction_number")
    String transactionNumber;
	
	@Column(name="cashback_amount")
    Double cashbackAmount;
	
	@Column(name="bank_name")
    String bankName;
	
	@Column(name="account_number")
    String accountNumber;
    
	@NotNull
	@Column(name="amount_received")
    Double amountReceived;
    
	@Column(name="amount_sign")
	String amountSign;
    
	@Column(name="created_date")
    Date  createdDate;
    
	@Column(name="modified_date")
    Date modifiedDate;
    
	@Version
    Long version;
    
	
	
}
