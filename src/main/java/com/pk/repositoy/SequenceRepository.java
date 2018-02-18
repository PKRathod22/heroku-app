package com.pk.repositoy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pk.model.SequenceConfig;

public interface SequenceRepository extends JpaRepository<SequenceConfig, Long> {

	@Query(nativeQuery=true,value="select * from sequence_config where sequence_name=?1")
	SequenceConfig findBySequenceName(String sequenceName);
	
	
	
}
