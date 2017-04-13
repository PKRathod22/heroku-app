package com.pk.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.pk.model.Customer;


public interface CustomerRepository extends CrudRepository<Customer, Long>{
	List<Customer> findById(Long id);
}
