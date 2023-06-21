package com.xworkz.trainer.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xworkz.trainer.service.TrainerService;
import com.xworkz.trainercustomer.entity.CustomerEntity;
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

	@GetMapping(value = "/onEmails/{emails}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String onEmail(@PathVariable String email) {
		Long byEmail = service.findByEmail(email);
		if (byEmail == 0) {
			return "";
		} else {
			return "email is exists";
		}

	}

	@GetMapping(value = "/mail/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String onEmails(@PathVariable String email) {
		Long byEmail = customerService.findByEmail(email);
		if (byEmail == 0) {
			return "";
		} else {
			return "email is exists";
		}

	}

	@GetMapping(value = "/search/{letters}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<String>> search(@PathVariable("letters") String letters, HttpServletResponse response)
			throws IOException {
		List<CustomerEntity> allCustomer = customerService.searchByName(letters);
		System.out.println("allCustomer : " + allCustomer);
		ResponseEntity<List<String>> bodys = ResponseEntity.ok()
				.body(allCustomer.stream().map(e -> e.getName()).collect(Collectors.toList()));
		System.out.println("bodys : " + bodys);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		return bodys;

	}

}
