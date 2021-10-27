package com.test.gcpstorage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@SpringBootApplication
public class GcpstorageApplication {

	public static void main(String[] args) {
		SpringApplication.run(GcpstorageApplication.class, args);
	}

}
