package com.xworkz.butterfly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xworkz.butterfly.model.LifeInsurenceEntity;
import com.xworkz.butterfly.service.LifeInsurenceService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("singUp")
@Slf4j
public class LifeInsurenceController {
	
	@Autowired
	private LifeInsurenceService service;

	@PostMapping
	public String onSave(LifeInsurenceEntity entity, Model model) {
		log.info("created onSave in LifeInsurenceController.......");
		if (entity != null) {
			LifeInsurenceEntity findByEmail = service.findByEmail(entity.getEmail());
			if (findByEmail != null) {
				model.addAttribute("msg", "Registration fail due to Email is alredy exist");

			}else {
				Boolean validateAndSave = service.validateAndSave(entity);
				log.info("validateAndSave "+validateAndSave);
				if(validateAndSave) {
					model.addAttribute("LifeInsurenceEntity", entity);
					model.addAttribute("msgs", "Registration is successfull");
					return "Signin";
				}else {
					model.addAttribute("msg", "Registration is Failed");
				}
			}

			
		}
		return "SignUp";
	}
}
