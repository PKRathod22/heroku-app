package com.pk.repositoy;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pk.model.UserTransaction;

public interface UserTransactionRepository extends JpaRepository<UserTransaction, Long> {

	@Query(nativeQuery=true,value="select * from user_transaction where distributer_id=?1")
	List<UserTransaction> findByUserId(String distributerId);
	
}
