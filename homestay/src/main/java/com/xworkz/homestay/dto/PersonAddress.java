package com.xworkz.homestay.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "PersonAddress")
@Table(name = "personsAddress")
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
public class PersonAddress {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "aId_pk")
	private Long aId;
	@Size(min = 3, max = 45)
	private String street;
	@Size(min = 3, max = 45)
	private String city;
	private Long pinCode;
	@OneToOne
	@JsonIgnore
	@JoinColumn(name = "pId")
	private PersonEntity personEntity;

	public PersonAddress() {
	System.out.println("personAddress entity calling......");
	}

	public PersonAddress(Long aId, @Size(min = 3, max = 45) String street, @Size(min = 3, max = 45) String city,
			Long pinCode, PersonEntity personEntity) {
		super();
		this.aId = aId;
		this.street = street;
		this.city = city;
		this.pinCode = pinCode;
		this.personEntity = personEntity;
	}

	public Long getaId() {
		return aId;
	}

	public void setaId(Long aId) {
		this.aId = aId;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Long getPinCode() {
		return pinCode;
	}

	public void setPinCode(Long pinCode) {
		this.pinCode = pinCode;
	}

	public PersonEntity getPersonEntity() {
		return personEntity;
	}

	public void setPersonEntity(PersonEntity personEntity) {
		this.personEntity = personEntity;
	}

	

}
