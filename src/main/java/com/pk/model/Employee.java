package com.pk.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import com.pk.enumeration.EmploymentStatus;
import com.pk.enumeration.Gender;

import lombok.Getter;
import lombok.Setter;


@Entity
@Setter
@Getter
@Table(name = "employee",uniqueConstraints = {
		@UniqueConstraint(columnNames = { "mobile" }, name = "UK_EMPLOYEE_MOBILE"),
		@UniqueConstraint(columnNames = { "employee_id" }, name = "UK_EMPLOYEE_EMPID")})
public class Employee {

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
	
	@Column(name = "employee_id",nullable=false)
	String employeeId;
	
	@Column(name = "user_name",nullable=false)
	String userName;
	
	@Column(name = "email",nullable=false)
	String email;
	
	@Column(name = "mobile",nullable=false)
	Long mobile;
	
	@Column(name = "image")
	@Lob
	byte[] image;
	
	@Column(name="gender")
	@Enumerated(EnumType.STRING)
	Gender gender;
	
	@Column(name="employment_status")
	@Enumerated(EnumType.STRING)
	EmploymentStatus status;
}
