package com.xworkz.trainer.configuration;

import java.io.File;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.xworkz.trainer.dbconfiguration.DBConfiguration;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class SpringMVCInitializer extends AbstractAnnotationConfigDispatcherServletInitializer
		implements WebMvcConfigurer {
	private String[] getServletMappping = { "/" };
	private Class[] getSrervletConfigClasses = { SpringConfiguration.class, DBConfiguration.class };

	public SpringMVCInitializer() {
		log.info("Created SpringMVCInitializer");
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		log.info("created getRootConfigClasses....");
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		log.info("created getServletConfigClasses....");
		return getSrervletConfigClasses;
	}

	@Override
	protected String[] getServletMappings() {
		log.info("created getServletMappings...");
		return getServletMappping;
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	protected void customizeRegistration(Dynamic registration) {
		String tempDir = "D:\\temp";
		int maxUploadSizeInMb = 50 * 1024 * 1024;
		File uploadDirectory = new File(tempDir);
		MultipartConfigElement multipartConfigElement = new MultipartConfigElement(uploadDirectory.getAbsolutePath(),
				maxUploadSizeInMb, maxUploadSizeInMb * 2, maxUploadSizeInMb / 2);
		registration.setMultipartConfig(multipartConfigElement);
	}

}
