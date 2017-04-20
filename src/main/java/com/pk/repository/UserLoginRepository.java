package com.pk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pk.model.NewUser;
import com.pk.model.UserLogin;

public interface UserLoginRepository extends JpaRepository<UserLogin, Long> {

	@Query("SELECT u from NewUser u  WHERE mobile=?1 AND password=?2")
	NewUser findByMobileAndPassword(Long mobile,String password);
	
	/*UserLogin findById(Long id);*/

}


