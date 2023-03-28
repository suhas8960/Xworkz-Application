package com.xworkz.homestay.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "persons")
//@Setter
//@Getter
//@AllArgsConstructor
//@NoArgsConstructor
public class PersonEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pId_pk")
	private Long pId;
	@Size(min = 4, max = 45)
	private String personName;
	@Min(value = 18)
	private Long age;
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "personEntity")
	//@PrimaryKeyJoinColumn
	private PersonAddress address;
	
	public PersonEntity() {
		System.out.println("personentity is calling...");
	}
	
	
	public Long getpId() {
		return pId;
	}
	public void setpId(Long pId) {
		this.pId = pId;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public Long getAge() {
		return age;
	}
	public void setAge(Long age) {
		this.age = age;
	}
	public PersonAddress getAddress() {
		return address;
	}
	public void setAddress(PersonAddress address) {
		this.address = address;
	}
	public PersonEntity(Long pId, @Size(min = 4, max = 45) String personName, @Min(18) Long age,
			PersonAddress address) {
		super();
		this.pId = pId;
		this.personName = personName;
		this.age = age;
		this.address = address;
	}
}
