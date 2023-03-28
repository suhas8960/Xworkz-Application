package com.xworkz.homestay.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.xworkz.homestay.model.HomeStayEntity;

@Service
public interface HomeStayService {

	HomeStayEntity validateAndSave(HomeStayEntity entity);

	Optional<HomeStayEntity> findByID(Long id);

	// void updateEntity(Long id, String location);

	void deleteEntity(Long id);

	Page<HomeStayEntity> HomeStayPegination(int pageNo, int pageSize);

}
