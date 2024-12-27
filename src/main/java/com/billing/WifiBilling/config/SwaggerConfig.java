package com.billing.WifiBilling.config;

import org.springframework.context.annotation.Bean; // Import to create bean definitions
import org.springframework.context.annotation.Configuration; // Mark this class as a configuration class
import springfox.documentation.builders.PathSelectors; // Import for path selection
import springfox.documentation.builders.RequestHandlerSelectors; // Import for handler selection
import springfox.documentation.spi.DocumentationType; // Import documentation types
import springfox.documentation.spring.web.plugins.Docket; // Import Docket for configuring Swagger

@Configuration // Mark this class as a configuration class for Spring
public class SwaggerConfig {
    @Bean // Define a bean for the Docket
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2) // Specify Swagger 2 documentation type
                .select() // Start the selection process
                .apis(RequestHandlerSelectors.basePackage("com.billing.WifiBilling.controller")) // Select APIs in this package
                .paths(PathSelectors.any()) // Match any path
                .build(); // Build the Docket with the specified configuration
    }
}
