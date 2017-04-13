package com.pk.repository;

import org.springframework.data.repository.CrudRepository;

import com.pk.model.Password;

public interface PasswordRepository extends CrudRepository<Password, Long> {

}
