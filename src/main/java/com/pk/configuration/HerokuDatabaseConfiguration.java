package com.pk.configuration;

import java.beans.PropertyVetoException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import lombok.extern.log4j.Log4j2;

@Configuration
@Log4j2
public class HerokuDatabaseConfiguration {

	@Value("${spring.profiles.active}")
	String activeProfile;

	@Bean
	SessionRoutingDataSource dataSource() throws PropertyVetoException {
		
		String fileName = "db-config-" + activeProfile + ".xml";
		
		log.info("Loading Database Configuration from : " + fileName + " ....");
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(fileName);

		return context.getBean("dataSource", SessionRoutingDataSource.class);
	}

}