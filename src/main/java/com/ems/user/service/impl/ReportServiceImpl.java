package com.ems.user.service.impl;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.ems.user.dto.response.UserResponse;
import com.ems.user.generator.ExcelGenerator;
import com.ems.user.generator.PdfGenerator;
import com.ems.user.service.ReportService;
import com.ems.user.service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private UserService userServiceImpl;

	@Override
	public void generateEmployeeReport(HttpServletResponse response, String fileType) throws IOException {

		String fileExtension = "";
		String mediaType = "";

		switch (fileType) {
		case "EXCEL":
			fileExtension = ".xlsx";
			mediaType = MediaType.APPLICATION_OCTET_STREAM.getType();
			break;
		case "PDF":
			fileExtension = ".pdf";
			mediaType = MediaType.APPLICATION_PDF.getType();
			break;
		}

		DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
		String currentDateTime = dateFormat.format(new Date());

		String type = "attachment; filename=employee_details_" + currentDateTime + fileExtension;
		response.setHeader("Content-Disposition", type);
		response.setContentType(mediaType);

		HashMap<String, Object> resultBody = userServiceImpl.getAllUsers("EMPLOYEE", 0, 0);
		Object sourceObject = resultBody.get("userResponseList");
		List<UserResponse> listOfUsers = new ObjectMapper().convertValue(sourceObject,
				new TypeReference<List<UserResponse>>() {
				});

		System.out.println(listOfUsers.size());

		switch (fileType) {
		case "EXCEL":
			generateEmployeeExcel(response, listOfUsers);
			break;
		case "PDF":
			generateEmployeePdf(response, listOfUsers);
			break;
		}

	}

	public void generateEmployeeExcel(HttpServletResponse response, List<UserResponse> listOfUsers) throws IOException {

		ExcelGenerator generator = new ExcelGenerator(listOfUsers);
		generator.generate(response);

	}

	public void generateEmployeePdf(HttpServletResponse response, List<UserResponse> listOfUsers) throws IOException {

		PdfGenerator generator = new PdfGenerator();
		generator.generate(listOfUsers, response);
	}

}
