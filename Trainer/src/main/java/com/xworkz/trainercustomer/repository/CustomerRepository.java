package com.xworkz.trainercustomer.repository;

import java.util.List;
import java.util.Optional;

import com.xworkz.trainer.entity.TrainerEntity;
import com.xworkz.trainercustomer.entity.CustomerEntity;

public interface CustomerRepository {
	
	Boolean saveCustomer(CustomerEntity entity);
	
	Long findByMail(String email);
	
	List<CustomerEntity> findByAll();

	Boolean update(CustomerEntity entity);
	
	void deleteById(Integer coustomerId);
	
	CustomerEntity findById(Integer customerId);
}
