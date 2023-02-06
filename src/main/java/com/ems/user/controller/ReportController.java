package com.ems.user.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ems.user.service.ReportService;

@RestController
@RequestMapping("/api/report")
public class ReportController {

	@Autowired
	private ReportService reportServiceImpl;

	@GetMapping(value = "/employee/export", produces = "application/json")
	public void exportActiveUserDataInExcel(HttpServletResponse response, @RequestParam String fileType)
			throws IOException {

		reportServiceImpl.generateEmployeeReport(response, fileType);

	}

}
