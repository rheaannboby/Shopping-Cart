package com.casestudy.productservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket postsApi() {
		return new Docket (DocumentationType.SWAGGER_2).groupName("product-service").apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.any()).paths(regex("/product.*")).build();
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Product REST API").description("API for product management").termsOfServiceUrl("http://rhea.com").license("Rhea license").licenseUrl("ron123rhea@gmail.com").version("1.0").build();
	}
}
