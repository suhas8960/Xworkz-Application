package com.xworkz.trainer.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
public class TrainerDTO {

	private Integer id;
	@Size(max = 40, min = 3, message = "name must not be null")
	private String name;
	private String lastName;
	@Size(max = 40, min = 3, message = "email must not be null")
	@Email
	private String email;
	@Size(max = 8, min = 8)
	private String password;
	@Size(max = 8, min = 8)
	private String confirmPassword;
	private Integer otp;
	private Integer count;
	private String imgPath;
}
