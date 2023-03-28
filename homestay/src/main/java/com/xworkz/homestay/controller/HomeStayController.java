package com.xworkz.homestay.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xworkz.homestay.model.HomeStayEntity;
import com.xworkz.homestay.service.HomeStayService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class HomeStayController {

	@Autowired
	private HomeStayService service;

	public HomeStayController() {
		log.info("Homestay" + this.getClass().getName());
	
	}

	@PostMapping("/save")
	public HomeStayEntity saveOn(@RequestBody HomeStayEntity entity) {
		log.info("Running saveOn.....");
		log.info("entity" + entity);
		return service.validateAndSave(entity);

	}

	@GetMapping("/findById/{id}")
	@Cacheable(key = "#id" ,cacheNames = "HomeStayEntity")
	public Optional<HomeStayEntity> findOn(@PathVariable Long id) {
		log.info("Running findOn.....");
		System.out.println("find id "+id);
		return service.findByID(id);

	}
//	@PutMapping("/updateLocation/{id}")
//	public void putLocation(@PathVariable Long id,@RequestBody String location) {
//		LOG.info("put Method is Running....");
//		 service.updateEntity(id, location);
//	}

	@DeleteMapping("/deleteEntity/{id}")
	@CacheEvict(key = "#id", cacheNames = "HomeStayEntity")
	public void deleteOn(@PathVariable Long id) {
		log.info("Delete Method Running on controller.....");
		service.deleteEntity(id);
	}

	@GetMapping("/HomeStayPegination")
	public Page<HomeStayEntity> getHomeStayEntity(@RequestParam int pageNo, @RequestParam int pageSize) {
		log.info("homestaypegination is running....");
		Page<HomeStayEntity> pegination = service.HomeStayPegination(pageNo, pageSize);
		return pegination;

	}

}
