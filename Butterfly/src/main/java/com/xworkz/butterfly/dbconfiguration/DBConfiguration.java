package com.xworkz.butterfly.dbconfiguration;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
public class DBConfiguration {

	public DBConfiguration() {
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean factoryBean(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
		bean.setPackagesToScan("com.xworkz.butterfly");
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
		source.setDriverClassName("com.mysql.jdbc.Driver");
		source.setUrl("jdbc:mysql://localhost:3306/xworkz");
		source.setUsername("root");
		source.setPassword("Suhas@1996");

		return source;
	}

}
