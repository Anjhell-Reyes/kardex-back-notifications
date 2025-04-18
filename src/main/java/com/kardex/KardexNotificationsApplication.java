package com.kardex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class KardexNotificationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(KardexNotificationsApplication.class, args);
	}

}
