package com.xworkz.butterfly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xworkz.butterfly.service.LifeInsurenceService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("reset")
@Slf4j
public class UpdatePasswordController {

	@Autowired
	private LifeInsurenceService service;

	public UpdatePasswordController() {
		log.info("UpdatePasswordController Running");
	}


	@PostMapping
	public String resetPass(String password, String email, Model model) {
		log.info("resetpass is running.....");

		Boolean passwordByEmail = service.updatePasswordByEmail(password, email);
		if (passwordByEmail) {
			model.addAttribute("msg", "password is update");
			return "Signin";
		} else {
			model.addAttribute("msg", "password is not update pls check..");
			return "resetPassword";
		}
	}

}
