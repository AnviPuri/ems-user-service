package com.ems.user.service;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public interface ReportService {

	public void generateEmployeeReport(HttpServletResponse response, String fileType) throws IOException;
	
}
