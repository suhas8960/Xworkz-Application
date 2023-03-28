package com.xworkz.butterfly.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "lifeinsurence")
@NamedQueries({ @NamedQuery(name = "findById", query = "select life from LifeInsurenceEntity life where life.id=:id"),
		@NamedQuery(name = "findByName", query = "select life from LifeInsurenceEntity life where life.name=:name"),
		@NamedQuery(name = "findByEmail", query = "select life from LifeInsurenceEntity life where life.email=:email"),
		@NamedQuery(name = "findByAll", query = "select life from LifeInsurenceEntity life"),
		@NamedQuery(name = "findByPasswordAndEmail", query = "select life from LifeInsurenceEntity life where life.email=:ema and life.password=:pass"),
		@NamedQuery(name = "updateStatusByEmail", query = "update LifeInsurenceEntity life set life.status=:status where life.email=:email"),
		@NamedQuery(name = "updateCountByEmail", query = "update LifeInsurenceEntity life set life.count=:count where life.email=:emai"),
		@NamedQuery(name = "updateOtpByEmail", query = "update LifeInsurenceEntity life set life.otp=:otp where life.email=:emails"),
		@NamedQuery(name = "resetPasswordByEmail", query = "update LifeInsurenceEntity life set life.password=:passw where life.email=:emil"),
		@NamedQuery(name = "updatePasswordByEmail", query = "update LifeInsurenceEntity life set life.password=:passs where life.email=:emmai")
		})
public class LifeInsurenceEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotNull(message = "name must not be null")
	@Length(max = 40)
	@Length(min = 3)
	private String name;
	@NotNull(message = "lastname must not be null")
	@Length(max = 40)
	@Length(min = 1)
	private String lastName;
	@NotNull(message = "email not be null")
	@Length(max = 40)
	@Length(min = 3)
	@Email
	private String email;
	@NotNull(message = "mobileno not be null")
	private Long mobileNo;
	@NotNull(message = "password not be null")
	@NotEmpty
	@Size( min = 8)
	private String password;
	
	private String status;
	
	private Integer count;
	
	private Integer otp;
	
	private String file;

	
}
