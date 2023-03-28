package com.xworkz.butterfly.service;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xworkz.butterfly.model.LifeInsurenceEntity;
import com.xworkz.butterfly.repository.LifeInsurenceDAO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LifeInsurenceServiceImpl implements LifeInsurenceService {

	@Autowired
	private LifeInsurenceDAO dao;

	@Override
	public Boolean validateAndSave(LifeInsurenceEntity entity) {
		ValidatorFactory buValidatorFactory = Validation.buildDefaultValidatorFactory();
		Validator validator = buValidatorFactory.getValidator();
		Set<ConstraintViolation<LifeInsurenceEntity>> violations = validator.validate(entity);
		if (violations.size() > 0) {
			violations.forEach(p -> log.info(p.getMessage()));
			log.info("data is invalid");
		} else {
			entity.setCount(0);
			entity.setStatus("unBlock");
			String passwordGen = encryptPassword(entity.getPassword());
			entity.setPassword(passwordGen);
			boolean save = this.dao.save(entity);
			if (save) {
				
				log.info("data is save :" + save);
				boolean sendMail = this.sendMail(entity.getEmail(), entity, entity.getPassword());
				System.out.println(sendMail);
				return sendMail;
			}
		}
		return true;
	}

	@Override
	public LifeInsurenceEntity findById(Integer id) {
		log.info("findById Running in service.....");
		if (id > 0) {
			LifeInsurenceEntity byId = this.dao.findById(id);
			return byId;
		} else {
			log.info("id is not found");
		}
		return null;

	}

	@Override
	public List<LifeInsurenceEntity> findByName(String name) {
		log.info("findByName Running in service.....");
		List<LifeInsurenceEntity> byName = this.dao.findByName(name);
		if (byName != null) {
			log.info("name is found");
			return byName;
		} else {
			log.info("name is not found ");
		}
		return null;

	}

	@Override
	public LifeInsurenceEntity findByEmail(String email) {
		log.info("findByEmail Running in service.....");
		LifeInsurenceEntity byEmail = this.dao.findByEmail(email);
		if (byEmail != null) {
			log.info("email is found");
			return byEmail;
		} else {
			log.info("email is not found");

		}
		return byEmail;
	}

	@Override
	public List<LifeInsurenceEntity> findByAll() {
		log.info("findByAll is running on service...");
		List<LifeInsurenceEntity> byAll = this.dao.findByAll();
		return byAll;
	}

	@Override
	public Boolean sendMail(String email, LifeInsurenceEntity entity, String password) {

		String host = "smtp.office365.com";
		final String user = "suhasnbanakar99@outlook.com";
		final String password1 = "suhas143";
		String to = email;

		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "true");
		props.put("mail.transport.protocol", "smtp");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password1);
			}
		});

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("Registration Confirmation  Mail");
			message.setText("Hi" + " " + entity.getName() + " " + entity.getLastName() + " Registration is complite "
					+ " " + "your password " + entity.getPassword() + " " + "Thank You");

			// send the message
			Transport.send(message);

			log.info("message sent successfully...");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public Boolean sendOtpMail(String email, Integer otp, LifeInsurenceEntity entity) {

		String host = "smtp.office365.com";
		final String user = "suhasnbanakar99@outlook.com";
		final String password1 = "suhas143";
		String to = email;

		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "true");
		props.put("mail.transport.protocol", "smtp");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password1);
			}
		});

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
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
	public LifeInsurenceEntity validateAndfindByPasswordAndEmail(String email, String password) {
		log.info("validateAndfindByPasswordAndEmail Running in service...");
		LifeInsurenceEntity findByPasswordAndEmail = this.dao.findByEmail(email);
		log.info("findByPasswordAndEmail "+findByPasswordAndEmail);
		if (findByPasswordAndEmail != null) {
			
			String password2 = findByPasswordAndEmail.getPassword();
			String decryptedPassword1 = decryptedPassword(password2);
			

			if (decryptedPassword1.equals(password)) {
				return findByPasswordAndEmail;
			} else {
				if (findByPasswordAndEmail.getCount() <= 3) {
					log.info("update count .........");
					this.dao.updateCountByEmail(findByPasswordAndEmail.getCount() + 1, email);

				} else if (findByPasswordAndEmail.getCount() >= 3) {
					log.info("account block .........");
					this.dao.updateStatusByEmail("Blocked", email);
				}
			}

		}
		return findByPasswordAndEmail;
	}

	@Override
	public Boolean updateStatusByEmail(String status, String email) {
		log.info("Running updateStatusByEmail service..");
		return dao.updateStatusByEmail(status, email);
	}

	@Override
	public Boolean updateCountByEmail(Integer count, String email) {
		log.info("Running updateCountByEmail service..");
		return dao.updateCountByEmail(count, email);
	}

	@Override
	public Boolean updateOtpByEmail(Integer otp, String email, LifeInsurenceEntity entity) {
		log.info("updateOtpByEmail in service.....");

		LifeInsurenceEntity byEmail = this.dao.findByEmail(email);
		if (byEmail != null) {
			log.info("email is found ");
			Integer generation = this.otpGeneration();
			byEmail.setOtp(generation);
			Boolean otpByEmail = this.dao.updateOtpByEmail(generation, email);
			if (otpByEmail) {
				Boolean sendOtpMail = this.sendOtpMail(email, generation, byEmail);
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
	public Boolean updatePasswordByEmail(String password, String email) {
		log.info("updatePasswordByEmail service....");
		LifeInsurenceEntity byEmail = this.dao.findByEmail(email);
		if (byEmail != null) {
			Boolean passwordByEmail = this.dao.updatePasswordByEmail(password, email);
			if (passwordByEmail) {
				log.info("password is update");
				return true;
			} else {
				log.info("password is not update");
				return false;
			}
		} else {
			log.info("email not found....pls check email");
		}

		return true;
	}

	@Override
	public Boolean resetPasswordByEmail(String password, String email, LifeInsurenceEntity entity) {
		log.info("resetPasswordByEmail in service .....");
		LifeInsurenceEntity findByEmail = this.dao.findByEmail(email);
		if (findByEmail != null) {
			Integer otp = findByEmail.getOtp();

			if (otp.equals(entity.getOtp())) {
				entity.setStatus("unBlock");
				Boolean passwordByEmail = dao.resetPasswordByEmail(password, email);
				if (passwordByEmail) {
					log.info("Reset password");
				} else {
					log.info("ot is not match");
				}

			}
		} else {
			log.info("email is not match");
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
	public String encryptPassword(String password) {
		Encoder encoder = Base64.getEncoder();
		String passwords = password;
		String encodeToString = encoder.encodeToString(passwords.getBytes());
		log.info("EncryptPassword " + encodeToString);
		return encodeToString;

	}

	@Override
	public String decryptedPassword(String encryptPassword) {
		Decoder decoder = Base64.getDecoder();
		byte[] decode = decoder.decode(encryptPassword);
		String decrypt = new String(decode);
		log.info("DecryptedPassword" + decrypt);
		return decrypt;
	}

	@Override
	public Boolean validateUpdateDetails(LifeInsurenceEntity entity) {
		Boolean updateDetails = this.dao.updateDetails(entity);
		return updateDetails;
	}

}
