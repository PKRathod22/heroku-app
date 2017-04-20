package com.pk.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.pk.enumeration.LovStatus;
import com.pk.enumeration.Role;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "password")
@Setter
@Getter
public class Password implements Serializable {
	
	private static final long serialVersionUID = -3009157732242241606L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;

	@Column(name = "password",nullable=false)
	String password;
	
	@Column(name = "role_name",nullable=false)
	String roleName;

	@Column(name="role")
	@Enumerated(EnumType.STRING)
	Role role;
	
	
	@Column(name="status")
	@Enumerated(EnumType.STRING)
	LovStatus status;
	
	
	
}
