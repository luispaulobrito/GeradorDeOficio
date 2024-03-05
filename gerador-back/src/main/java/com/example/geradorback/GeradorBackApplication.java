package com.example.geradorback;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Gerador Back API", version = "1.0", description = "API desenvolvida para o projeto GeradorBack"))
public class GeradorBackApplication {
	public static void main(String[] args) {
		SpringApplication.run(GeradorBackApplication.class, args);
	}

}
