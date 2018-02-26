package com.pk.model;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pk.enumeration.TransactionType;

import lombok.Data;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
public class UserTransaction implements Serializable{

	private static final long serialVersionUID = -5042929395564162855L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	Long id;
	
	@ManyToOne
	@JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_user_transaction_userId"))
	UserMaster user;
	
	Date transactionDate;
    TransactionType transactionType;
    String transactionMode;
    String transactionNumber;
    Double cashbackAmount;
    String bankName;
    String accountNumber;
    Double amountReceived;
	String amountSign;
    Date  createdDate;
    Date modifiedDate;
    @Version
    Long version;
    
	
	
}
