package com.xworkz.trainer.entity;

import java.time.LocalDateTime;

import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class TrainerDetails{
	
	private String createdBy="Suhas";
	private String updatedBy="Abhi";
	private LocalDateTime createdDate=LocalDateTime.now();
	private LocalDateTime updatedDate=LocalDateTime.now();
	
//	public TrainerDetails(String createdBy, String updatedBy, LocalDateTime createdDate, LocalDateTime updatedDate) {
//		super();
//		this.createdBy = createdBy;
//		this.updatedBy = updatedBy;
//		this.createdDate = createdDate;
//		this.updatedDate = updatedDate;
//	}
	
	

}
