package com.xworkz.trainercustomer.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xworkz.trainer.dto.CustomerDTO;
import com.xworkz.trainer.dto.TrainerDTO;
import com.xworkz.trainer.entity.TrainerEntity;
import com.xworkz.trainercustomer.entity.CustomerEntity;
import com.xworkz.trainercustomer.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j

public class CustomerController {
	@Autowired
	private CustomerService service;

	@RequestMapping(value = "/addOrUpdateCustomer", method = RequestMethod.POST)
	public String addOrUpdate(CustomerDTO dto, Model model,HttpServletRequest request) {
		log.info("created onSave in CustomerController......." + dto);
		// TODO move to service
		model.addAttribute("headers", "AddCustomer");

		if (dto.getCoustomerId() == null) {
			model.addAttribute("headers", "");

//			HttpSession httpSession = request.getSession(true);
//			TrainerDTO trainerDTO = (TrainerDTO) httpSession.getAttribute("dto");
//			dto.setTrainerId(trainerDTO.getId());
			Set<ConstraintViolation<CustomerDTO>> validateAndSave = service.saveAndValidCustomer(dto);
			log.info("validateAndSave " + validateAndSave);
			if (validateAndSave.isEmpty()) {
				model.addAttribute("CustomerDTO", dto);
				model.addAttribute("msgs", "Customer Details successfull Added");
				return "AddCustomer";
			}
		} else {
			model.addAttribute("headers", "UpdateCustomer");
			Boolean updateEntity = service.updateEntity(dto);
			log.info("updateEntity in controller : " + updateEntity);
			model.addAttribute("msgs", "Customer details update");
			return "AddCustomer";
		}

		return "LoginSucess";

	}


}
