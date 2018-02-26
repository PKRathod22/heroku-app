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

@Table(name="user_master")
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
public class UserMaster implements Serializable {
	
	private static final long serialVersionUID = -6513828693331958787L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
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
    String qualification;
    String dobMonth;
    String dobYear;
    String dobDay;
    String maritalStatus;
    
    String state;
    
    String district;
    
    String city;
    
    String pincode;
    
    //Nominee Detail
    
    String nomineeName;
    String relation;
    String nomineeAge;
    
    //Bank Detail
    
    String accountNo;
    String branchName;
    String bankName;
    String ifscCode;
    String panNo;
    String aadharCard;
    String VoterId;
    String DrivingLicence;
    
    //Qualification
    String address;
    //Payment Deposit Detail
   
    String transactionMode;
    String transactionNumber;
    
    String packageName;
    String packageMrp;
    String designation;
    Double paidAmount;
    
    Date currentLoginDate;
    String status;
    Boolean paymentStatus;
    Date createdDate;
    Date modifiedDate;
    
    @Transient
    String sessionId;

    @Version
    Long version;
    
    
	
}
