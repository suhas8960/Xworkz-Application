package com.xworkz.homestay.person.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xworkz.homestay.dao.PersonRepositry;
import com.xworkz.homestay.dto.PersonEntity;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PersonServiceImpl implements PersonService {
	@Autowired
	private PersonRepositry repositry;

	@Override
	public PersonEntity validateAndSave(PersonEntity entity) {
		log.info("validate and save method Running*************");
		//entity.getAddress().setPersonEntity(entity);
		return repositry.save(entity);
	}

	@Override
	public Optional<PersonEntity> findByPersonId(Long pId) {
		log.info("findby method is running*****************");
		return repositry.findById(pId);
	}

	@Override
	public void deleteById(Long pId) {
		log.info("deletById method is running.....");
		repositry.deleteById(pId);
		
	}

}
