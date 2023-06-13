package com.xworkz.trainer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.xworkz.trainer.service.TrainerService;
import com.xworkz.trainercustomer.service.CustomerService;

@RestController
@EnableWebMvc
@RequestMapping("/")
public class TrainerAjaxController {

	@Autowired(required = true)
	@Qualifier("trainerServiceImpl")
	private TrainerService service;
	
	@Autowired(required = true)
	@Qualifier("customerServiceImpl")
	private CustomerService customerService;
	
	@GetMapping(value = "/onEmails/{emails}",produces = MediaType.APPLICATION_JSON_VALUE)
	public String onEmail(@PathVariable String email) {
		Long byEmail = service.findByEmail(email);
		if(byEmail==0) {
			return "";
		}else {
		return "email is exists"; 
		}
		
	
	}
	
	@GetMapping(value = "/onEmail/{email}",produces = MediaType.APPLICATION_JSON_VALUE)
	public String onEmails(@PathVariable String email) {
		Long byEmail = customerService.findByEmail(email);
		if(byEmail==0) {
			return "";
		}else {
		return "email is exists"; 
		}
		
	
	}
	
	
}
