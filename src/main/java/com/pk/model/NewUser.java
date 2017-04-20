package com.pk.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import com.pk.enumeration.Gender;
import com.pk.enumeration.LovStatus;

import lombok.Getter;
import lombok.Setter;


@Entity
@Setter
@Getter
@Table(name = "new_user",uniqueConstraints = {
		@UniqueConstraint(columnNames = { "email" }, name = "UK_NEWUSER_EMAIL"),
		@UniqueConstraint(columnNames = { "mobile" }, name = "UK_NEWUSER_MOBILE") })
public class NewUser {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,generator="autogen")
	@TableGenerator(name = "autogen", initialValue = 0, allocationSize = 1)
	Long id;
	
	@Version
	@Column(name = "version_lock")
	long versionLock;
	
	@Column(name="created_date")
	Date createdDate;
	
	@Column(name="updated_date")
	Date updatedDate;
	
	@Column(name = "password",nullable=false)
	String password;
	
	@Column(name = "user_name",nullable=false)
	String userName;
	
	@Column(name = "email",nullable=false)
	String email;
	
	@Column(name = "mobile",nullable=false)
	Long mobile;
	
	@Column(name="gender")
	@Enumerated(EnumType.STRING)
	Gender gender;
	
	@Column(name="user_status")
	@Enumerated(EnumType.STRING)
	LovStatus status;
}
