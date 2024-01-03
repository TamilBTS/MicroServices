package com.spring.accounts;

import com.spring.accounts.dto.AccountsContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableConfigurationProperties(value = AccountsContactInfoDto.class)
@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(info = @Info(
		title = "Accounts Microservice REST API Documentation",
		version = "v1",
		description = "Accounts Micro Services REST API Endpoints documentation",
		contact = @Contact(
				name = "Tamilarasi",
				email = "tamilarasi.srinivasan@outlook.com",
				url = "https://www.linkedin.com/in/tamilarasi-srinivasan-537589232/"),
		license = @License(
				name = "Apache 2.0",
				url = "http://www.testlicense.com")),
		externalDocs = @ExternalDocumentation(
				description = "Account Service REST API Documentation",
		url = "http://localhost:8080/swagger-ui/index.html#/")
		)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
