package com.xworkz.stars.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StarsDTO {

	private String name;
	private String email;
	private Long mobileNo;
	private String location;
}
