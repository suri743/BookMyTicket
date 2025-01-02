package com.dev.moviebookingsystem.bmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.dev.moviebookingsystem")
public class MbsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MbsApplication.class, args);
	}
}
