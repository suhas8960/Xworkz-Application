package com.xworkz.butterfly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationStartupAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xworkz.butterfly.model.LifeInsurenceEntity;
import com.xworkz.butterfly.service.LifeInsurenceService;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("update")
@Slf4j
public class EditRegistrationController {
	@Autowired
	private LifeInsurenceService service;

	public EditRegistrationController() {
		log.info("EditRegistrationController Running..");
	}
	
	@GetMapping
	public String update(Model model, @RequestParam Integer id) {
		log.info("Running in update get method");
		LifeInsurenceEntity lifeInsurenceEntity = this.service.findById(id);
		if(lifeInsurenceEntity !=null) {
		model.addAttribute("dtos", lifeInsurenceEntity);
		
		return "editRegistrationPage";
		}else {
			model.addAttribute("msg", "Entity not found");
			
		}
		return "index";
	}

	@PostMapping
	public String onUpdate(LifeInsurenceEntity entity, Model model) {
		log.info("onUpdate controller rurnning...");
		Boolean updateDetails = this.service.validateUpdateDetails(entity);
		if(updateDetails !=null) {
			model.addAttribute("msg","update successfully");
			
			return "Signin";
		}else {
			model.addAttribute("emsg", "update unsuccessfull");
		return	"editRegistrationPage";
		}
	}

}
