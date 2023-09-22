package com.storecode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.storecode.models")
public class ProyectoFinalAppsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoFinalAppsApplication.class, args);
	}

}
