package com.xworkz.homestay.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "homestay")
@AllArgsConstructor
@NoArgsConstructor

public class HomeStayEntity extends CustomerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty(value = "id")
	private Long id;
	@JsonProperty(value = "name")
	@NotBlank(message = "Name is mandatory")
	@Size(max = 45, min = 3)
	private String name;
	@JsonProperty(value = "location")
	@NotBlank(message = "Location is mandatory")
	@Size(max = 45, min = 3)
	private String location;
	@JsonProperty(value = "mobileNo")
	private Long mobileNo;

}
