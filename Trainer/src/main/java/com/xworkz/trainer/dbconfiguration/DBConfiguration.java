package com.xworkz.trainer.dbconfiguration;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import lombok.extern.slf4j.Slf4j;

@Configuration
@PropertySource("classpath:application.properties")
@Slf4j
public class DBConfiguration {

	@Value("${spring.datasource.url}")
	private String dbUrl;

	@Value("${spring.datasource.username}")
	private String dbUsername;

	@Value("${spring.datasource.password}")
	private String dbPassword;

	@Value("${spring.datasource.driver-class-name}")
	private String dbDriverClass;

	public DBConfiguration() {

	}

	@Bean
	public LocalContainerEntityManagerFactoryBean factoryBean(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
		bean.setPackagesToScan("com.xworkz.");
		bean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		bean.setDataSource(dataSource);

		Properties map = new Properties();
		map.put("hibernate.show_sql", true);
		map.put("hibernate.formate_sql", true);
		bean.setJpaProperties(map);
		return bean;

	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource source = new DriverManagerDataSource();
		source.setDriverClassName(dbDriverClass);
		source.setUrl(dbUrl);
		source.setUsername(dbUsername);
		source.setPassword(dbPassword);

		return source;
	}
	@Bean
	public MultipartResolver multipartResolver() {
		log.info("Running in multipartResolver");
		return new StandardServletMultipartResolver();
	}

}
