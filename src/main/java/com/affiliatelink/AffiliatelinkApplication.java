package com.affiliatelink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class AffiliatelinkApplication {

	public static void main(String[] args) {
		SpringApplication.run(AffiliatelinkApplication.class, args);
	}

}
