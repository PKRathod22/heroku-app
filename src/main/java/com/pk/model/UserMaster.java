package com.pk.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class UserMaster {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long id;
	@Column
	String name;
	@Column
	String password;
	
	
	
}
