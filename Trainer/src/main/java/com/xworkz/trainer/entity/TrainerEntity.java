package com.xworkz.trainer.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString(callSuper = true)
//@AllArgsConstructor
@NoArgsConstructor
@Table(name = "trainer")
@NamedQueries({
		@NamedQuery(name = "findByEmail", query = "select count(*)from TrainerEntity train where train.email=:emai"),
		@NamedQuery(name = "updateOtpAndOtpTimeByEmail", query = "update TrainerEntity train set train.otp=:otp,train.otpTime=:otpTime where train.email=:emails"),
		@NamedQuery(name = "findByEmails", query = "select train from TrainerEntity train where train.email=:gmails"),
		@NamedQuery(name = "updateCountByEmail", query = "update TrainerEntity train set train.count=:count where train.email=:gmail"),
		@NamedQuery(name = "updateStatusByEmail", query = "update TrainerEntity train set train.status=:status where train.email=:mail"),
		@NamedQuery(name = "updatePasswordByEmail", query = "update TrainerEntity train set train.password=:passs,train.otpTime=:otpT where train.email=:emmai")})
public class TrainerEntity extends TrainerDetails implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	private String lastName;

	private String email;

	private String password;

	private Integer otp;
	
	private Integer count;
	
	private String status;
	
	private LocalTime otpTime;
	
	private String imgPath;
	
	private String loginTime;
	
	public TrainerEntity(String createdBy, String updatedBy, LocalDateTime createdDate, LocalDateTime updatedDate,
			String name, String lastName, String email, String password, Integer otp, Integer count,LocalTime otpTime) {
		super(createdBy, updatedBy, createdDate, updatedDate);
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.otp = otp;
		this.count = count;
		this.otpTime=otpTime;
		

	}

}
