package com.ems.user.config;

import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().paths(PathSelectors.any())
				.apis(RequestHandlerSelectors.any()).build();
	}

//	private ApiInfo apiInfo() {
//        return new ApiInfo(
//                "EMS User Service", //title
//                "Service to manage users in the employee management system", //description
//                "Version 1.0", //version
//   
//               
//    }

}
