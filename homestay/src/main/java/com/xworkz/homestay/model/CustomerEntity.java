package com.xworkz.homestay.model;

import java.time.LocalDateTime;

import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
public class CustomerEntity {

	private String createdBy = "suhas";
	private LocalDateTime createdDate = LocalDateTime.now();
	private String updatedBy = "suhas";
	private LocalDateTime updatedDate = LocalDateTime.now();

}
