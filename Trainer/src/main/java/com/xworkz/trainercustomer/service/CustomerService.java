package com.xworkz.trainercustomer.service;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import com.xworkz.trainer.dto.CustomerDTO;
import com.xworkz.trainercustomer.entity.CustomerEntity;

public interface CustomerService {

	Set<ConstraintViolation<CustomerDTO>> saveAndValidCustomer(CustomerDTO dto);

	Long findByEmail(String email);

	Boolean sendMail(String email, CustomerEntity entity);

	List<CustomerEntity> allCustomer();

	CustomerEntity findById(Integer coustomerId);

	Boolean updateEntity(CustomerEntity entity);

	void deleteEntity(Integer coustomerId);

}
