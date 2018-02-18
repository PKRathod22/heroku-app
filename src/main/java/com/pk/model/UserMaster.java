package com.pk.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.ToString;

@Table(name="user_master")
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@ToString()
@Data
public class UserMaster implements Serializable {
	
	private static final long serialVersionUID = -6513828693331958787L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long id;
	
	//Personal Detail
	@NotNull
	String userName;
	
	String password;
	String confirmPassword;
	String photo;
    String email;
    String gender;
    @NotNull
    String mobileNumber;
    String optionalMobileNumber;
    @Enumerated(EnumType.STRING)
    UserType userType=UserType.customer;
    
    @Column(name="distributer_id", unique=true)
    String distributerId;
  //this is distributerId who referred 
    String sponsorID;
    String mySponserName;
    
    String legPosition;
    
    //Personal Detail
    String fathersName;
    
    String dobMonth;
    String dobYear;
    String dobDay;
    
    String state;
    
    String district;
    
    String city;
    
    String pincode;
    
    //Nominee Detail
    
    String nomineeName;
    String relation;
    
    //Bank Detail
    
    String accountNo;
    String bankName;
    String ifscCode;
    String panNo;
    String aadharCard;
    String VoterId;
    String DrivingLicence;
    
    //Qualification
    String address;
    //Payment Deposit Detail
    String paymentType;
    String paytmNumber;
    
    String purchaseDay;
    String purchaseMonth;
    String purchaseYear;
    String packageName;
    String designation;
    
    Double earnAmount;
    
    Date currentLoginDate;
    String status;
    Date createdDate;
    Date modifiedDate;
    
    @Transient
    String sessionId;

    @Version
    Long version;
    
    
	
}
