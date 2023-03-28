package com.xworkz.homestay.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.xworkz.homestay.model.HomeStayEntity;

@Repository
public interface HomeStayRepository extends PagingAndSortingRepository<HomeStayEntity, Long> {

	
}
