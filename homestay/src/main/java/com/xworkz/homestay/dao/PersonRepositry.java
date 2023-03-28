package com.xworkz.homestay.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.xworkz.homestay.dto.PersonEntity;

@Repository
public interface PersonRepositry extends PagingAndSortingRepository<PersonEntity, Long>{

}
