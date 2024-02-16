package com.example.UserWishList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "AuditAwareImp")
public class UserWishListApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserWishListApplication.class, args);
	}

}
