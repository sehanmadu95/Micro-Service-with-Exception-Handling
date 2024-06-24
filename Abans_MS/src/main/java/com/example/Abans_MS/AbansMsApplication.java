package com.example.Abans_MS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ProblemDetail;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class AbansMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AbansMsApplication.class, args);
	}




	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
