package com.xworkz.trainer.repository;

import java.time.LocalTime;
import java.util.Optional;

import com.xworkz.trainer.entity.TrainerEntity;

public interface TrainerRepository {

	boolean save(TrainerEntity entity);

	Long findByEmail(String email);

	Boolean updateOtpAndOtpTimeByEmail(Integer otp, LocalTime otpTime,String email);

	Optional<TrainerEntity> findByEmails(String email);

	Boolean updateCountByEmail(Integer count, String email);

	Boolean updateStatusByEmail(String status, String email);

	Boolean updatePasswordAndOtpTimeByEmail(String password,LocalTime otpTime, String email);
	
	Boolean update(TrainerEntity entity);
}
