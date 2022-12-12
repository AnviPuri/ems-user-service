package com.ems.user.config;

public class SwaggerHandler {

	private String resourceHandler_1 = "swagger-ui.html";
	private String resourceLocation_1 = "classpath:/META-INF/resources/";
	private String resourceHandler_2 = "/webjars/**";
	private String resourceLocation_2 = "classpath:/META-INF/resources/webjars/";

	public String getResourceHandler_1() {
		return resourceHandler_1;
	}

	public String getResourceLocation_1() {
		return resourceLocation_1;
	}

	public String getResourceHandler_2() {
		return resourceHandler_2;
	}

	public String getResourceLocation_2() {
		return resourceLocation_2;
	}
}
