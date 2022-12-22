package com.ems.user.config;

import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
public class SwaggerConfig {

////	@Override
////	public void addResourceHandlers(ResourceHandlerRegistry registry) {
////		SwaggerHandler swaggerHandler = new SwaggerHandler();
////		registry.addResourceHandler(swaggerHandler.getResourceHandler_1())
////				.addResourceLocations(swaggerHandler.getResourceLocation_1());
////		registry.addResourceHandler(swaggerHandler.getResourceHandler_2())
////				.addResourceLocations(swaggerHandler.getResourceLocation_2());
////	}
//
//	public static SwaggerConfig getHandler() {
//		return new SwaggerConfig();
//	}
//
//	@Bean
//	public static Docket api() {
//		return new Docket(DocumentationType.SWAGGER_2).select().paths(PathSelectors.any())
//				.apis(RequestHandlerSelectors.basePackage("com.ems.user")).build();
//	}
//
////	public static ApiInfo getApiInfo() {
////		String title = DaoLayer.getDAOConfig().getEnv() + " : " + DaoLayer.getDAOConfig().getRoleName();
////		String description = "Started At : " + DAODT.getLocalDateTime();
////		return new ApiInfoBuilder().title(title).description(description).build();
////	}
//
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().paths(PathSelectors.any())
				.apis(RequestHandlerSelectors.basePackage("com.ems.user")).build();
	}

//
//	private ApiInfo apiInfo() {
//		ApiInfo apiInfo = new ApiInfo("EMS User Service", "Service to manage users in the employee management system",
//				"Version 1.0", null, null, null, null);
//		return apiInfo;
//
//	}

}
