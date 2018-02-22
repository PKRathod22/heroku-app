package com.pk.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.Data;

@Table
@Entity
@Data
public class UserMessage implements Serializable {
	
	private static final long serialVersionUID = 6302697582463487420L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long id;
	
	String name;
	String message;
    String email;
    String phoneNumber;
    Date createdDate;
    
    @Version
    Long version;
}
