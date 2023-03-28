package com.xworkz.homestay.person.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.xworkz.homestay.dto.PersonEntity;
import com.xworkz.homestay.person.service.PersonService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class PersonController {
	@Autowired
	private PersonService service;

	public PersonController() {
		log.info("person controller Running********");
	}

	@PostMapping("/onsave")
	public PersonEntity onSave(@RequestBody PersonEntity entity) {
		log.info("onsave method is running**********");
		return service.validateAndSave(entity);
	}
//ResponseEntity
	// App to app 
	// postman--> app 	
	//RestTemplate
	//postman-->app-->app
	@GetMapping("/findBy/{pId}")
	public ResponseEntity<PersonEntity> onFind(@PathVariable Long pId) {
		log.info("findById controller method running**********");
		Optional<PersonEntity> optional = service.findByPersonId(pId);
		if (optional.isPresent()) {
			log.info("optional onfind is present*******");
			return ResponseEntity.ok().body(optional.get());
		}
		return ResponseEntity.notFound().build();

	}
	
	@DeleteMapping("/deleteById/{pId}")
	public void onDelete(@PathVariable Long pId) {
		log.info("ondelete method is running......");
		service.deleteById(pId);
	}

}
