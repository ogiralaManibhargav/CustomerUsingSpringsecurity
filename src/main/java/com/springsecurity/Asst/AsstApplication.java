package com.springsecurity.Asst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity(prePostEnabled = true,securedEnabled = true,jsr250Enabled = true)

public class AsstApplication {

	public static void main(String[] args) {
		SpringApplication.run(AsstApplication.class, args);
	}
/*{
   "name":"happy",
   "email":"happy@example.com",
    "password":"12345",

    "roles":"Admin"
}*/
}
