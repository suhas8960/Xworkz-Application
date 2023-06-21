package com.xworkz.trainer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xworkz.trainer.entity.TrainerEntity;
import com.xworkz.trainer.service.TrainerService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/")
@Slf4j
public class ForgetPasswordController {
	@Autowired(required = true)
	@Qualifier("trainerForgetPasswordServiceImpl")
	private TrainerService trainerForgetPasswordServiceImpl;

	@GetMapping("forget")//Page NAme
	public String forgotPassword(Integer otp, String email, TrainerEntity entity, Model model) {

		Boolean otpByEmail = trainerForgetPasswordServiceImpl.updateOtpByEmail(otp, email, entity);

		if (otpByEmail) {
			model.addAttribute("msg", "OTP sent to your mail login within 3min");
			return "resetPassword";
		} else {
			model.addAttribute("msg", "worng email pls check it..");
			return "forgetPassword";
		}

	}

	@PostMapping("reset")
	public String resetPassword(String password, String email, Integer otp ,Model model) {
		log.info("resetpassword running in service....");
		Boolean updatePassword = trainerForgetPasswordServiceImpl.updatePassword(password, email,otp);
			if (updatePassword) {
				model.addAttribute("msg", "password updated successfully");
				return "Signin";
		} else {
			model.addAttribute("msg", "otp not match");

		}
		return "resetPassword";

	}

}
