package com.capg.cartservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket postsApi() {
		return new Docket (DocumentationType.SWAGGER_2).groupName("cart-service").apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.any()).paths(regex("/cart.*")).build();
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Cart REST API").description("API for cart management").termsOfServiceUrl("http://rhea.com").license("Rhea license").licenseUrl("ron123rhea@gmail.com").version("1.0").build();
	}
}
*/

public class SwaggerConfig {
	
}
