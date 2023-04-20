package com.xworkz.stars.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StarsDTO {

	@Size(max = 45,min = 3)
	@NotBlank(message = "Pls enter the Name")
	private String name;
	@Email
	@NotBlank(message = "Pls enter the Email")
	private String email;
	@Size(max =10,min=10)
	@NotBlank(message = "Pls enter the MobileNo")
	private String mobileNo;
	@Size(max = 45,min = 3)
	@NotBlank(message = "Pls enter the Location")
	private String location;
	private Boolean statusActive=false;
}
