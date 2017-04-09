package com.pk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages="com.pk.*")
public class HerokuApplication {
	public static void main(String[] args) {
		SpringApplication.run(HerokuApplication.class, args);
	}
}
