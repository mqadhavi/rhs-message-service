package com.kalangga.rhsservice;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RhsMessageServiceApplication {

	@PostConstruct
	void started() {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+07:00"));
	}
	
	public static void main(String[] args) {
		SpringApplication.run(RhsMessageServiceApplication.class, args);
	}

}
