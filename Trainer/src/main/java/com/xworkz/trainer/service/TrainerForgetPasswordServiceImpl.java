package com.xworkz.trainer.service;

import java.time.LocalTime;
import java.util.Base64;
import java.util.Optional;
import java.util.Properties;
import java.util.Base64.Encoder;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.xworkz.trainer.dto.TrainerDTO;
import com.xworkz.trainer.entity.TrainerEntity;
import com.xworkz.trainer.repository.TrainerRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TrainerForgetPasswordServiceImpl implements TrainerService {

	@Value("${spring.mail.host}")
	String emailHost;
	@Value("${spring.mail.username}")
	String emailUsername;
	@Value("${spring.mail.password}")
	String emailPassword;
	@Value("${spring.mail.port}")
	String emailPort;

	@Autowired
	private TrainerRepository repository;

	@Override
	public Boolean updateOtpByEmail(Integer otp, String email, TrainerEntity entity) {
		log.info("updateOtpByEmail in service.....");

		Optional<TrainerEntity> byEmail = this.repository.findByEmails(email);
		if (byEmail.isPresent()) {
			TrainerEntity entity2 = byEmail.get();
			log.info("email is found ");
			Integer generation = this.otpGeneration();
			entity2.setOtp(generation);
			Boolean otpByEmail = this.repository.updateOtpAndOtpTimeByEmail(generation, LocalTime.now(), email);
			if (otpByEmail) {
				Boolean sendOtpMail = this.sendOtpMail(email, generation, entity2);
				if (sendOtpMail) {
					log.info("otp send...");
				} else {
					log.info("otp is not send..");
				}

			} else {
				log.info("email is invalid");
			}
		} else {
			log.info("email is not match...");
			return false;
		}

		return true;
	}

	@Override
	public Integer otpGeneration() {
		int randomPin = (int) (Math.random() * 9000) + 1000;
		Integer otp = Integer.valueOf(randomPin);
		return otp;
	}

	@Override
	public Boolean sendOtpMail(String email, Integer otp, TrainerEntity entity) {

		Properties props = new Properties();
		props.put("mail.smtp.host", emailHost);
		props.put("mail.smtp.port", emailPort);
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "true");
		props.put("mail.transport.protocol", "smtp");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(emailUsername, emailPassword);
			}
		});

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(emailUsername));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
			message.setSubject("Otp Confirmation  Mail");
			message.setText("Hi" + " " + entity.getName() + " " + entity.getLastName() + " Send otp successfully " + " "
					+ "your otp " + entity.getOtp() + " " + "Thank You");

			// send the message
			Transport.send(message);

			log.info("otp sent successfully...");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public TrainerEntity findByEmails(String email) {
		Optional<TrainerEntity> findByEmails = repository.findByEmails(email);
		TrainerEntity entity = null;
		if (findByEmails.isPresent()) {
			entity = findByEmails.get();
		}
		return entity;
	}

	@Override
	public Boolean updatePassword(String password, String email, Integer otp) {
		Optional<TrainerEntity> findByEmails = repository.findByEmails(email);
		if (findByEmails.isPresent()) {
			TrainerEntity entity = findByEmails.get();
			log.info("entity : " + entity);
			log.info("update count and status");
			entity.setCount(0);
			entity.setStatus("unBlock");
			repository.update(entity);
			String encryptPassword = encryptPassword(password);
			if (entity.getOtp().equals(otp)) {
				Boolean updatePasswordByEmail = repository.updatePasswordAndOtpTimeByEmail(encryptPassword,
						LocalTime.now().plusSeconds(180), email);
				log.info(encryptPassword);
				if (updatePasswordByEmail) {
					log.info("updatepassword successfully");
				} else {
					log.info("password not update");
				}
			} else {
				log.info("otp not match");
			}
		}
		return true;

	}

	@Override
	public String encryptPassword(String password) {
		Encoder encoder = Base64.getEncoder();
		String passwords = password;
		String encodeToString = encoder.encodeToString(passwords.getBytes());
		log.info("EncryptPassword " + encodeToString);
		return encodeToString;
	}
}
