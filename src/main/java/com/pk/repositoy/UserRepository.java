package com.pk.repositoy;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pk.model.UserMaster;

public interface UserRepository extends JpaRepository<UserMaster, Long> {

}
