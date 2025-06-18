package com.safecube.safecube_backend;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@Slf4j
@SpringBootApplication
public class SafeCubeBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SafeCubeBackendApplication.class, args);
		log.info("Application started at: {}}", LocalDateTime.now());
	}

}
