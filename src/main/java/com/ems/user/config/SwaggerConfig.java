package com.ems.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//@EnableSwagger2
//@Configuration
public class SwaggerConfig  {
	
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
////	@Bean
////	public Docket api() {
////		return new Docket(DocumentationType.SWAGGER_2).select().paths(PathSelectors.any())
////				.apis(RequestHandlerSelectors.any()).build();
////	}
//
////	private ApiInfo apiInfo() {
////        return new ApiInfo(
////                "EMS User Service", //title
////                "Service to manage users in the employee management system", //description
////                "Version 1.0", //version
////   
////               
////    }

}
