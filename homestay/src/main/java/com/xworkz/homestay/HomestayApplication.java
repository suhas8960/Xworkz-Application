package com.xworkz.homestay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;

import lombok.extern.slf4j.Slf4j;

//@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
@SpringBootApplication
@Slf4j
@EnableCaching
public class HomestayApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomestayApplication.class, args);
		log.info("Running Spring Boot Application");
	}

}
