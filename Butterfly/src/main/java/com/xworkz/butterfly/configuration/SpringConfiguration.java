package com.xworkz.butterfly.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.server.WebHandler;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import lombok.extern.slf4j.Slf4j;

@Configuration
@ComponentScan("com.xworkz.butterfly")
@Slf4j
public class SpringConfiguration {

	public SpringConfiguration() {
		log.info("created ......" + this.getClass().getSimpleName());
	}

	@Bean
	public ViewResolver viewResolver() {
		log.info("Running viewResolver...");
		return new InternalResourceViewResolver("/", ".jsp");
	}

	@Bean
	public MultipartResolver multipartResolver() {
		log.info("Running multipartResolver.... ");
		return new StandardServletMultipartResolver();
	}
	
	
	

}
