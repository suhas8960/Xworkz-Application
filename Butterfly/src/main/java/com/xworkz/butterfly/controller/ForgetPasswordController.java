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
@RequestMapping("forget")
@Slf4j
public class ForgetPasswordController {
	@Autowired
	private LifeInsurenceService service;

	public ForgetPasswordController() {
		log.info("ForgetPasswordController Running....");
	}

	@PostMapping
	public String password(Integer otp, String email, LifeInsurenceEntity entity, Model model) {
		return forgotPassword(otp, email, entity, model);
	}

	@GetMapping
	public String forgotPassword(Integer otp, String email, LifeInsurenceEntity entity, Model model) {

		Boolean otpByEmail = service.updateOtpByEmail(otp, email, entity);

		if (otpByEmail) {
			model.addAttribute("msg", "OTP sent to your mail pls check it,,,");
			return "confirmPassword";
		} else {
			model.addAttribute("msg", "worng email pls check it..");
			return "forgetPassword";
		}

	}

}
