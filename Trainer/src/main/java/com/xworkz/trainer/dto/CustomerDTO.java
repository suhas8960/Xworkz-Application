package com.xworkz.trainer.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class CustomerDTO {
	private Long coustomerId;
	private Integer trainerId;
	@Size(max = 40, min = 3, message = "name must not be null")
	private String name;
	private String address;
	@Size(max = 40, min = 3, message = "email must not be null")
	@Email
	private String email;
	private Long mobileNo;
	private String companyName; 
	private Integer salary;

}
