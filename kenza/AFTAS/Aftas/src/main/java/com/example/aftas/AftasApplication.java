package com.example.aftas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.example.aftas.entity","com.example.aftas.config"})
@SpringBootApplication(exclude = {R2dbcAutoConfiguration.class})
public class AftasApplication {
	public static void main(String[] args) {
		SpringApplication.run(AftasApplication.class, args);
	}

}
