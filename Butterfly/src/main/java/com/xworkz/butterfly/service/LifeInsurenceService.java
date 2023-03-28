package com.xworkz.butterfly.service;

import java.util.List;

import com.xworkz.butterfly.model.LifeInsurenceEntity;

public interface LifeInsurenceService {

	Boolean validateAndSave(LifeInsurenceEntity entity);

	LifeInsurenceEntity findById(Integer id);

	List<LifeInsurenceEntity> findByName(String name);

	LifeInsurenceEntity findByEmail(String email);

	List<LifeInsurenceEntity> findByAll();

	LifeInsurenceEntity validateAndfindByPasswordAndEmail(String email, String password);

	Boolean sendMail(String email, LifeInsurenceEntity entity, String password);

	Boolean updateStatusByEmail(String status, String email);

	Boolean updateCountByEmail(Integer count, String email);

	Boolean updateOtpByEmail(Integer otp, String email, LifeInsurenceEntity entity);

	Boolean updatePasswordByEmail(String password, String email);

	Boolean resetPasswordByEmail(String password, String email, LifeInsurenceEntity entity);

	Integer otpGeneration();

	String encryptPassword(String password);

	String decryptedPassword(String encryptPassword);

	Boolean sendOtpMail(String email, Integer otp, LifeInsurenceEntity entity);

	Boolean validateUpdateDetails(LifeInsurenceEntity entity);

}
