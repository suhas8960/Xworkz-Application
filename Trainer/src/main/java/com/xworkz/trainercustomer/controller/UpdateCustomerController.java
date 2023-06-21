package com.xworkz.trainercustomer.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.xworkz.trainer.dto.CustomerDTO;
import com.xworkz.trainercustomer.entity.CustomerEntity;
import com.xworkz.trainercustomer.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
//@RequestMapping("update")
public class UpdateCustomerController {

	@Autowired
	private CustomerService service;

	@RequestMapping(value = "/customer/{coustomerId}", method = RequestMethod.GET)
	public String update(Model model, @PathVariable Long coustomerId) {
		log.info("Running in update get method");
		CustomerEntity entity = this.service.findById(coustomerId);
		if (entity != null) {
			model.addAttribute("dtos", entity);
			model.addAttribute("headers", "UpdateCustomer");
			return "AddCustomer";
		} else {
			model.addAttribute("msg", "Entity not found");

		}
		return "LoginSucess";
	}

	@RequestMapping(value = "/customer", method = RequestMethod.GET, params = "submit")
	public String allCustomerList(Model model) {
		List<CustomerEntity> allCustomer = service.allCustomer();
		if (allCustomer != null) {
			model.addAttribute("customer", allCustomer);
			return "Customer";
		} else {
			model.addAttribute("msg", "Customer is not added");
			return "LoginSucess";
		}

	}

	@RequestMapping(value = "/customer/byId/{customerId}", method = RequestMethod.GET)
	public RedirectView delete(@PathVariable Long customerId, Model model, HttpServletRequest req) {
		service.deleteEntity(customerId);
		model.addAttribute("customer", customerId);
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(req.getContextPath() + "/customer?submit=submit");
		return redirectView;
	}

}
