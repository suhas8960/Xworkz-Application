package com.xworkz.homestay.person.service;

import java.util.Optional;

import com.xworkz.homestay.dto.PersonEntity;

public interface PersonService {

	PersonEntity validateAndSave(PersonEntity entity);
	
	Optional<PersonEntity> findByPersonId(Long pId);
	
	void deleteById(Long pId);
}
