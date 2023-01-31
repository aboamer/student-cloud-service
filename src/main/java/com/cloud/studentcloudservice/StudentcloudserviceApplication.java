package com.cloud.studentcloudservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableFeignClients
public class StudentcloudserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentcloudserviceApplication.class, args);
	}

	@Bean
	public WebClient webClient () {
		WebClient webClient =WebClient.builder().build();

		return webClient;
	}
}
