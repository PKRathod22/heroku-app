package com.pk.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pk.enumeration.LovStatus;
import com.pk.enumeration.Role;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "user_login")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class UserLogin implements Serializable {
	
	private static final long serialVersionUID = -3009157732242241606L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,generator="autogen")
	@TableGenerator(name = "autogen", initialValue = 0, allocationSize = 1)
	Long id;
	
	@Version
	@Column(name = "version_lock")
	long versionLock;
	
	@Column(name="role")
	@Enumerated(EnumType.STRING)
	Role role;
	
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="new_user_id",foreignKey=@ForeignKey(name="FK_USER_NEWUSERID"))
	NewUser newUser;
	
	@Column(name="status")
	@Enumerated(EnumType.STRING)
	LovStatus status;
	
	
	
}
