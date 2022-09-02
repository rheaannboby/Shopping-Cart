package com.capg.productservice.config;

public class SwaggerConfig {

}

/*
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
}*/
