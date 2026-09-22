package com.example.Customer_Onboarding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class CustomerOnboardingApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerOnboardingApplication.class, args);
	}

}
