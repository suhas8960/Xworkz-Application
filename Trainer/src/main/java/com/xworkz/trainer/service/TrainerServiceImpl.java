package com.xworkz.trainer.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.Collections;
import java.util.Optional;
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

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.xworkz.trainer.dto.TrainerDTO;
import com.xworkz.trainer.entity.TrainerEntity;
import com.xworkz.trainer.repository.TrainerRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TrainerServiceImpl implements TrainerService {
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

	private Set<ConstraintViolation<TrainerDTO>> validate(TrainerDTO dto) {
		ValidatorFactory validationFactory = Validation.buildDefaultValidatorFactory();
		Validator validator = validationFactory.getValidator();
		Set<ConstraintViolation<TrainerDTO>> vailation = validator.validate(dto);
		return vailation;
	}

	@Override
	public Set<ConstraintViolation<TrainerDTO>> validateAndSave(TrainerDTO dto) {
		Set<ConstraintViolation<TrainerDTO>> vailations = validate(dto);
		if (vailations != null && !vailations.isEmpty()) {
			log.info("vailations is dto");
			return vailations;
		} else {
			log.info("constraintViolations does not exist,data is good");
			TrainerEntity entity = new TrainerEntity();
			BeanUtils.copyProperties(dto, entity);
			String encryptPassword = encryptPassword(entity.getPassword());
			entity.setCount(0);
			entity.setOtp(0);
			entity.setPassword(encryptPassword);
			boolean saved = this.repository.save(entity);
			if (saved) {
				Boolean sendMail = sendMail(dto.getEmail(), entity, decryptedPassword(encryptPassword));
				log.info("" + sendMail);
			}
			log.info("" + saved);
			log.info("" + entity);
			return Collections.emptySet();
		}
	}

	@Override
	public Long findByEmail(String email) {
		log.info("findByEmail Running in service.....");
		Long byEmail = this.repository.findByEmail(email);
		return byEmail;
	}

	@Override
	public Boolean sendMail(String email, TrainerEntity entity, String password) {
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
			message.setSubject("Registration successfully completed..");
			message.setText("Hi" + " " + entity.getName() + " " + entity.getLastName() + " Registration is complite "
					+ " " + "your password " + password + " " + "Thank You");

			// send the message
			Transport.send(message);

			log.info("message sent successfully...");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return true;

	}

	@Override
	public Boolean sendAccountBlockMail(String email, TrainerEntity entity) {
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
			message.setSubject("Registration successfully completed..");
			message.setText("Hi" + " " + entity.getName() + " " + entity.getLastName()
					+ " Your account is block plz check the password " + " " + "Thank You");

			// send the message
			Transport.send(message);

			log.info("message sent successfully...");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return true;

	}

	@Override
	public TrainerEntity userSignIn(String email, String password) {
		 String pattern = "dd-MM-yyyy HH:mm";
		log.info("email :" + email);
		log.info("password :" + password);
		Optional<TrainerEntity> findByEmails = repository.findByEmails(email);
		TrainerDTO dto = new TrainerDTO();
		if (findByEmails.isPresent()) {
			TrainerEntity entity = findByEmails.get();
			log.info("" + entity);
			String decryptedPassword = decryptedPassword(entity.getPassword());
			BeanUtils.copyProperties(findByEmails, dto);
			log.info("entity email : " + entity.getEmail());
			log.info("entity paswword : " + entity.getPassword());
			if (entity.getCount() >= 3) {
				log.info("Your account is block");
				repository.updateStatusByEmail("Blocked", email);
				sendAccountBlockMail(email, entity);
				return entity;
			}
			log.info("decryptedPassword : " + decryptedPassword);
			if (entity.getEmail().equals(email) && decryptedPassword.equals(password)) {
				log.info("Running Email matching and password matching");
				LocalDateTime curentDateAndTime = LocalDateTime.now();
				entity.setLoginTime(curentDateAndTime.format(DateTimeFormatter.ofPattern(pattern)));
				repository.update(entity);
				return entity;
			} else {
				repository.updateCountByEmail(entity.getCount() + 1, email);
				log.info("count of login");
				return entity;
			}
		}
		return null;

	}

	@Override
	public TrainerDTO updateProfilePic(String email,String name,String lastName, String imagePath) {
		Optional<TrainerEntity> findByEmails = repository.findByEmails(email);
		log.info("email : "+email);
		if (findByEmails.isPresent()) {
			TrainerEntity entity = findByEmails.get();
			entity.setImgPath(imagePath);
			entity.setName(name);
			entity.setLastName(lastName);
			Boolean update = repository.update(entity);
			log.info("image update :" + entity);
			log.info("image update :" + update);
		}
			return TrainerService.super.updateProfilePic(email,name,lastName,imagePath);
		
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
}
