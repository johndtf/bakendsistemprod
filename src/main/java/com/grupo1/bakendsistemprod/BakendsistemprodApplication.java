package com.grupo1.bakendsistemprod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.grupo1.bakendsistemprod.entities")
@EnableJpaRepositories(basePackages = "com.grupo1.bakendsistemprod.repositories") 
public class BakendsistemprodApplication {

	public static void main(String[] args) {
		SpringApplication.run(BakendsistemprodApplication.class, args);
	}

}
