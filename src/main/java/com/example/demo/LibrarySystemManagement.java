package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class LibrarySystemManagement {

	public static void main(String[] args) {
		SpringApplication.run(LibrarySystemManagement.class, args);
	}

}
