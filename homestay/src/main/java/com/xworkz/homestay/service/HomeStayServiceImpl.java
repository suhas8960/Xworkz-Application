package com.xworkz.homestay.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.xworkz.homestay.model.HomeStayEntity;
import com.xworkz.homestay.repository.HomeStayRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class HomeStayServiceImpl implements HomeStayService {


	@Autowired
	private HomeStayRepository repository;

	@Override
	public HomeStayEntity validateAndSave(HomeStayEntity entity) {
		
		log.info("Running validate and save method...");
		return repository.save(entity);
	}

	@Override
	public Optional<HomeStayEntity> findByID(Long id) {
		log.info("FindNameById is Runnig.....");
		return repository.findById(id);
	}

	@Override
	public void deleteEntity(Long id) {
		log.info("Delete method Running....");
		repository.deleteById(id); ;
	}
//	@Override
//	@Transactional(rollbackOn = Exception.class)
//	public void updateEntity(Long id, String location) {
//		Optional<HomeStayEntity> entity=repository.findById(id);
//		repository.save(entity);
//		
//	}

	@Override
	public Page<HomeStayEntity> HomeStayPegination(int pageNo,int pageSize) {
		Pageable pageable=PageRequest.of(pageNo, pageSize);
		Page<HomeStayEntity> page= repository.findAll(pageable);
		
		return page;
	}

}
