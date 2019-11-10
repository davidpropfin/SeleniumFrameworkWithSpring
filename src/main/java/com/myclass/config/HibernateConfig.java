package com.myclass.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@Configuration
public class HibernateConfig {
@Bean
public DataSource  dataSource() {
	DriverManagerDataSource source =new DriverManagerDataSource();
	source.setDriverClassName("com.mysql.cj.jdbc.Driver");
	source.setUrl("jdbc::mysql://localhost:3306/elearning");
	source.setUsername("root");
	source.setUsername("12345678");
	return source;
}
@Bean 
public LocalSessionFactoryBean sessionFactory() {
	LocalSessionFactoryBean bean =new LocalSessionFactoryBean();
	bean.setDataSource(dataSource());
	bean.setPackagesToScan("com.myclass.entity");
	
	Properties properties=new Properties();
	properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
	properties.put("hibernate.show_sql",true);
	properties.put("hibernate.format_sql",true);
	return bean;
}
}
