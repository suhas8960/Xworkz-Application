package com.xworkz.butterfly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xworkz.butterfly.model.LifeInsurenceEntity;
import com.xworkz.butterfly.service.LifeInsurenceService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("confirm")
@Slf4j
public class ConfirmPasswordController {

	@Autowired
	private LifeInsurenceService service;

	public ConfirmPasswordController() {
		log.info("ConfirmPasswordController Running.....");
	}
	
	@PostMapping
	public String confirmPassword(String password, String email, LifeInsurenceEntity entity, Model model) {
		return updatePassword(password, email, entity, model);
	}

	@GetMapping
	public String updatePassword(String password, String email, LifeInsurenceEntity entity, Model model) {
		log.info("updatePassword in cntroller");

		Boolean passwordByEmail = service.resetPasswordByEmail(password, email, entity);
		if (passwordByEmail) {
			model.addAttribute("msg", "Password is updated..");
			return "Signin";
		} else {
			model.addAttribute("msg", "Password is not updated..");
			return "forgetPassword";
		}
	}

}
