package com.xworkz.butterfly.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xworkz.butterfly.model.LifeInsurenceEntity;
import com.xworkz.butterfly.service.LifeInsurenceService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/")
@Slf4j
public class SearchController {
	@Autowired
	private LifeInsurenceService service;

	@GetMapping("serach")
	@Cacheable(key = "#lifeInsurenceId",value = "lifeInsurence")
	public String searchById(@RequestParam int id, Model model) {
		log.info("searchById isrunning=========");
		LifeInsurenceEntity byId = service.findById(id);
		if (byId != null) {
			List<LifeInsurenceEntity> entities=new ArrayList<>();
			entities.add(byId);
			model.addAttribute("entity", entities);
		
		} else {
			model.addAttribute("error", "data not found...");
			return "index";
		}
		return "findById";
	}

	@GetMapping("serachName")
	public String searchByName(@RequestParam String name, Model model) {
		log.info("serachname is running in controller...");
		List<LifeInsurenceEntity> byName = service.findByName(name);
		if (byName != null) {
			model.addAttribute("serName", byName);

		} else {
			model.addAttribute("errors", "name not found...");
			return "index";
		}
		return "findByName";
	}

	@GetMapping("serachemail")
	public String searchByEmail(@RequestParam String email, Model model) {
		log.info("searchByEmail is running in controller...");
		LifeInsurenceEntity byEmail = service.findByEmail(email);
		if (byEmail != null) {
			System.out.println("email not null...running ");
//			List<LifeInsurenceEntity> entities=new ArrayList<>();
//			entities.add(byEmail);
			model.addAttribute("byEmail", byEmail);
			System.out.println("byEmail :"+byEmail);
			return "findByEmail";
//			return "editRegistrationPage";
		} else {
			model.addAttribute("msg", "email is not fond....");
			return "index";
		}
		
	}

	@GetMapping("serachAll")
	public String searchByAll(Model model) {
		log.info("serachByAll running in controller...");
		List<LifeInsurenceEntity> byAll = service.findByAll();
		if (byAll != null) {
			model.addAttribute("serachAll", byAll);

		} else {
			model.addAttribute("msg", "data not found");
			return "index";
		}

		return "findAll";
	}
}