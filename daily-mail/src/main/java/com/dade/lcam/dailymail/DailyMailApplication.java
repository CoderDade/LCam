package com.dade.lcam.dailymail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DailyMailApplication {

	public static void main(String[] args) {
		SpringApplication.run(DailyMailApplication.class, args);
	}
}
