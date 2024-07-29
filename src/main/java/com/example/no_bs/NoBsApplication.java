package com.example.no_bs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class NoBsApplication {

    public static void main(String[] args) {
        SpringApplication.run(NoBsApplication.class, args);
    }
    @OpenAPIDefinition(
            info = @Info(
                    description = "Test swagger"
            )
    )
    @Configuration
    public class OpenApiConfig {
    }
}
