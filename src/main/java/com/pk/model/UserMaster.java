package com.pk.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserMaster implements Serializable {
	
	private static final long serialVersionUID = -6513828693331958787L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long id;
	@Column
	String name;
	@Column
	String password;
	
	
	
}
