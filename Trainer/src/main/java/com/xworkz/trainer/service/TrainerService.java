package com.xworkz.trainer.service;

import java.util.Set;

import javax.validation.ConstraintViolation;

import com.xworkz.trainer.dto.TrainerDTO;
import com.xworkz.trainer.entity.TrainerEntity;

public interface TrainerService {

	default Set<ConstraintViolation<TrainerDTO>> validateAndSave(TrainerDTO dto) {
		return null;
	}

	default Long findByEmail(String email) {
		return null;
	}

	default Boolean sendMail(String email, TrainerEntity entity, String password) {
		return null;
	}

	default Integer otpGeneration() {
		return null;
	}

	default Boolean updateOtpByEmail(Integer otp, String email, TrainerEntity entity) {
		return null;
	}

	default Boolean sendOtpMail(String email, Integer otp, TrainerEntity entity) {
		return null;
	}

	default String encryptPassword(String password) {
		return null;
	}

	default String decryptedPassword(String encryptPassword) {
		return null;
	}

	default TrainerEntity userSignIn(String email, String password) {
		return null;
	}

	default Boolean sendAccountBlockMail(String email, TrainerEntity entity) {
		return null;
	}

	default Boolean updatePassword(String password, String email, Integer otp) {
		return null;

	}

	default TrainerDTO updateProfilePic(String email, String name, String lastName, String imagePath) {
		return null;
	}

	default TrainerEntity findByEmails(String email) {
		return null;
	}
}
