package com.xworkz.butterfly.controller;

import java.util.List;

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
@RequestMapping("singin")
@Slf4j
public class StatusController {

	@Autowired
	private LifeInsurenceService service;

	@PostMapping
	public String registration(String email, String password, Model model) {

		return onFindByEmailAndPassword(email, password, model);
	}

	@GetMapping
	public String onFindByEmailAndPassword(String email, String password, Model model) {
		log.info("onFindByEmailAndPassword is Running in contoller....");
		LifeInsurenceEntity byEmail = service.findByEmail(email);
		if (byEmail !=null) {
			if (byEmail.getCount() ==4) {

				model.addAttribute("message", "check the password..");
			} else {
				if (byEmail.getStatus().equals("Blocked")) {
					model.addAttribute("message", "your account has be blocked");
					model.addAttribute("LifeInsurenceEntity", byEmail);
					return "forgetPassword";
				} else {
					model.addAttribute("msg", "Welcome to page");
					model.addAttribute("LifeInsurenceEntity", byEmail);
					return "Signin";
				}

			}
		}
		
		return "Signin";
	}
}
