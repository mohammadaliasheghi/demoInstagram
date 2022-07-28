package com.google.demoinstagram.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@Profile("swagger")
@Slf4j
@Order()
@EnableSwagger2
@ConditionalOnProperty(name = "app.api.swagger.enable", havingValue = "true")
public class SwaggerConfig {
    @Bean
    public Docket documentation() {
        log.info("Swagger started successfully!");
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.google.demoinstagram.restController"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metadata());
    }

    private ApiInfo metadata() {
        return new ApiInfoBuilder()
                .title("API documentation of Loan App")
                .description("Use this documentation as a reference how to interact with app's API")
                .contact(new Contact("sirMohammad", "https://github.com/mohammadaliasheghi", "mohammad.ali.asehghi@gmail.com"))
                .build();
    }
}