package com.xworkz.trainercustomer.service;

import java.util.Collections;
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

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.xworkz.trainer.dto.CustomerDTO;
import com.xworkz.trainercustomer.entity.CustomerEntity;
import com.xworkz.trainercustomer.repository.CustomerRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

	@Value("${spring.mail.host}")
	String emailHost;
	@Value("${spring.mail.username}")
	String emailUsername;
	@Value("${spring.mail.password}")
	String emailPassword;
	@Value("${spring.mail.port}")
	String emailPort;

	@Autowired
	private CustomerRepository repository;

	private Set<ConstraintViolation<CustomerDTO>> validate(CustomerDTO dto) {
		ValidatorFactory validationFactory = Validation.buildDefaultValidatorFactory();
		Validator validator = validationFactory.getValidator();
		Set<ConstraintViolation<CustomerDTO>> vailation = validator.validate(dto);
		return vailation;
	}

	@Override
	public Set<ConstraintViolation<CustomerDTO>> saveAndValidCustomer(CustomerDTO dto) {
		Set<ConstraintViolation<CustomerDTO>> vailations = validate(dto);
		if (vailations != null && !vailations.isEmpty()) {
			log.info("vailations is dto");
			return vailations;
		} else {
			CustomerEntity entity = new CustomerEntity();
			BeanUtils.copyProperties(dto, entity);
			Boolean saveCustomer = repository.saveCustomer(entity);
			if (saveCustomer) {
				Boolean sendMail = sendMail(dto.getEmail(), entity);
				log.info("" + sendMail);
			}
			log.info("saveCustomer : " + saveCustomer);
			return Collections.emptySet();
		}

	}

	@Override
	public Long findByEmail(String email) {
		log.info("findByEmail Running in service.....");
		Long byEmail = this.repository.findByMail(email);
		return byEmail;
	}

	@Override
	public Boolean sendMail(String email, CustomerEntity entity) {
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
			message.setText("Hi" + " " + entity.getName() + " Successfully Added as a Customer" + " " + "Thank You");

			// send the message
			Transport.send(message);

			log.info("message sent successfully...");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return true;

	}

	@Override
	public List<CustomerEntity> allCustomer() {
		log.info("findByAll is running on service...");
		List<CustomerEntity> byAll = this.repository.findByAll();
		return byAll;
	}

	@Override
	public CustomerEntity findById(Integer coustomerId) {
		log.info("findById Running in service.....");
		if (coustomerId > 0) {
			CustomerEntity byId = this.repository.findById(coustomerId);
			return byId;
		} else {
			log.info("coustomerId is not found");
		}
		return null;

	}

	@Override
	public Boolean updateEntity(CustomerEntity entity) {
		log.info("updateEntity in service");
	
		if(entity !=null) {
		Boolean update = repository.update(entity);
		return update;
		}else {
			log.info("customer is not update");
		}
		return true;
	}

	@Override
	public void deleteEntity(Integer coustomerId) {
		log.info("deleteEntity in service");
		repository.deleteById(coustomerId);
	}

}
