package com.example.UserWishList;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@OpenAPIDefinition(
		info = @Info(
				title = "User WishList Service",
				description = "This service provides the user to add, remove and read the items from wish list also this is fully secured service /n note: only user/register is open for all else authentication is required to get authenticated please go to /login and enter your credentials",
				version = "v1",
				contact = @Contact(
						name = "Arjunagi A Rehman",
						email = "abdul123arj@gmail.com"
				),
				license = @License(
						name = "Apache 2.0"
				)
		)
)
@SecurityScheme(name = "javainuseapi", scheme = "basic", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "AuditAwareImp")
public class UserWishListApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserWishListApplication.class, args);
	}

}
