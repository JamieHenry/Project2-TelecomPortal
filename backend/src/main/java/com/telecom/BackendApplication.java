package com.telecom;

import org.jboss.logging.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Project2-TelecomPortal", version = "1.0"))
@SecurityScheme(name = "JWT Authentication", bearerFormat = "JWT", scheme = "bearer", type = SecuritySchemeType.HTTP)
public class BackendApplication implements CommandLineRunner {

	private static final Logger logger = Logger.getLogger(BackendApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("App started successfully");
	}
}
