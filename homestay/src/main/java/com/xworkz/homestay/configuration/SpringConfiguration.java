package com.xworkz.homestay.configuration;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class SpringConfiguration {

	public SpringConfiguration() {
		log.info("Spring configuration Running.....");
	}

	@Bean
	public DataSource dataSource() {
		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setMaximumPoolSize(100);
		dataSource.setDataSourceClassName("com.mysql.cj.jdbc.MysqlDataSource");
		dataSource.addDataSourceProperty("url", "jdbc:mysql://localhost:3306/xworkz");
		dataSource.addDataSourceProperty("user", "root");
		dataSource.addDataSourceProperty("password", "Suhas@1996");
		dataSource.addDataSourceProperty("cachePrepStmts", true);
		dataSource.addDataSourceProperty("prepStmtCacheSize", 250);
		dataSource.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
		dataSource.addDataSourceProperty("useServerPrepStmts", true);
		return dataSource;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan("com.xworkz");
		factory.setDataSource(dataSource());
		// factory.setJpaProperties(jpaProperties());

		return factory;
	}

}
