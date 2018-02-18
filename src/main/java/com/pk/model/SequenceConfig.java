package com.pk.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table
@Entity
@Data
public class SequenceConfig implements Serializable {
	
	private static final long serialVersionUID = 145277345670055400L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long id;
	
	@Enumerated(EnumType.STRING)
	SequenceName sequenceName;
	
	String description;
    Long currentValue;
	
    String prefix;
    String suffix;
}
