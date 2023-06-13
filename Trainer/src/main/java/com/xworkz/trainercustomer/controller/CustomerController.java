package com.xworkz.trainercustomer.controller;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xworkz.trainer.dto.CustomerDTO;
import com.xworkz.trainercustomer.entity.CustomerEntity;
import com.xworkz.trainercustomer.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j

public class CustomerController {
	@Autowired
	private CustomerService service;
	
	@RequestMapping(value = "/addCustmoe",method = RequestMethod.POST)
	public String onSave(CustomerDTO dto, Model model) {
		log.info("created onSave in CustomerController......."+dto);
		Set<ConstraintViolation<CustomerDTO>> validateAndSave = service.saveAndValidCustomer(dto);
		log.info("validateAndSave " + validateAndSave);
		if (validateAndSave.isEmpty()) {
			model.addAttribute("CustomerDTO", dto);
			model.addAttribute("msgs",
					"Customer Details successfull Added");
			return "Customer";
		} else {
			model.addAttribute("msg", "Customer Details is Failed");
		}

		return "LoginSucess";

	}
	

	
	
	
	

}
