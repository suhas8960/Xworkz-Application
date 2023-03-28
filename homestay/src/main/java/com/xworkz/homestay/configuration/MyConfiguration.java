package com.xworkz.homestay.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.xworkz.homestay.configuration")
public class MyConfiguration {

	@Bean
	public String getName() {
		System.out.println("registering name");
		return "Abhi";
	}

	

}
