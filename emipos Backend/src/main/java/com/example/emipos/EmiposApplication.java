package com.example.emipos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
public class EmiposApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmiposApplication.class, args);
	}

}
