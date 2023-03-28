package com.xworkz.butterfly.repository;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;

import com.xworkz.butterfly.model.LifeInsurenceEntity;

public interface LifeInsurenceDAO {

	Boolean save(LifeInsurenceEntity entity);
	
	LifeInsurenceEntity findById(Integer id);

	List<LifeInsurenceEntity> findByName(String name);

	LifeInsurenceEntity findByEmail(String email);
	
	LifeInsurenceEntity findByPasswordAndEmail(String email ,String password);

	List<LifeInsurenceEntity> findByAll();
	
	Boolean updateStatusByEmail(String status,String email);
	
	Boolean updateCountByEmail(Integer count,String email);

	Boolean updateOtpByEmail(Integer otp,String email);
	
	Boolean resetPasswordByEmail(String password,String email);
	
	Boolean updatePasswordByEmail(String password,String email);
	
	Boolean updateDetails(LifeInsurenceEntity entity);
}
