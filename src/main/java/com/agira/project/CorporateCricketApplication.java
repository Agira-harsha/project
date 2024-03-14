package com.agira.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
   public class CorporateCricketApplication {

	public static void main(String[] args) {
		SpringApplication.run(CorporateCricketApplication.class, args);
		System.out.println("Database Connected successfully " +
				"\nHappy cricket :)");
	}

}
