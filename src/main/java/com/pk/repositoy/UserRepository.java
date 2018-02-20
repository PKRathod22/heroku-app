package com.pk.repositoy;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pk.model.UserMaster;

public interface UserRepository extends JpaRepository<UserMaster, Long> {

	@Query(nativeQuery=true,value="select * from user_master where distributer_id=?1 and password=?2")
	UserMaster findByUserAndPassword(String distributerId,String password);
	
	
	@Query(nativeQuery=true,value="select * from user_master where distributer_id=?1")
	UserMaster findByDistributerId(String distributerId);
	
	@Query(nativeQuery=true,value="select * from user_master order by id desc")
	List<UserMaster> getAllById();
	
}
