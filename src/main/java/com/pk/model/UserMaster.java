package com.pk.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.ToString;

@Entity
@Table
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@ToString()
@Data
public class UserMaster implements Serializable {
	
	private static final long serialVersionUID = -6513828693331958787L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	Long id;
	
	@Column(name="name")
	String name;
	
	@Column(name="password")
	String password;
	
	
	
}
