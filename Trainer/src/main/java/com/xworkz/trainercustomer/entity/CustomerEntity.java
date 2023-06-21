package com.xworkz.trainercustomer.entity;

import java.io.Serializable;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "CustomerEntity")
@Setter
@Getter
@ToString(callSuper = true)
//@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer")
@NamedQueries({
		@NamedQuery(name = "findBy", query = "select count(*)from CustomerEntity customer where customer.email=:mail"),
		@NamedQuery(name = "findByAlls", query = "select customer from CustomerEntity customer"),
		@NamedQuery(name = "findById", query = "select customer from CustomerEntity customer where customer.coustomerId=:ids") })
public class CustomerEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long coustomerId;
	private Integer trainerId;
	private String name;
	private String address;
	private String email;
	private Long mobileNo;
	private String companyName;
	private Integer salary;

	public CustomerEntity(Integer trainerId, String name, String address, String email, Long mobileNo,
			String companyName, Integer salary) {
		super();
		this.trainerId = trainerId;
		this.name = name;
		this.address = address;
		this.email = email;
		this.mobileNo = mobileNo;
		this.companyName = companyName;
		this.salary = salary;
	}

}
