package com.example.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.entity.Customer;

public interface CustomerDAO extends CrudRepository<Customer, Long>{
			
	//public Customer findById(Long id);
	public Iterable<Customer> findByOrderByWeightAsc();
	public Iterable<Customer> findByWeightGreaterThan(Integer weight);
	
	//in @Query, the entity name (not table name) is case sensitive
	@Query("select c from Customer c where c.weight > 40")
	public Iterable<Customer> findOverWeight();
	
	

}



