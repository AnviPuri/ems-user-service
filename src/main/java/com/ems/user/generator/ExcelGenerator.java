package com.ems.user.generator;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ems.user.dto.response.UserResponse;

public class ExcelGenerator {

	private List<UserResponse> userList;
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;

	public ExcelGenerator(List<UserResponse> userList) {
		this.userList = userList;
		workbook = new XSSFWorkbook();
	}

	private void writeHeader() {
		// String sheetName, List<String> cellTitles
		sheet = workbook.createSheet("Exam Records");

		Row row = sheet.createRow(0);

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);

		createCell(row, 0, "User ID", style);
		createCell(row, 1, "First Name", style);
		createCell(row, 2, "Last Name", style);
		createCell(row, 3, "Email", style);
		createCell(row, 4, "Phone Number", style);

	}

	private void createCell(Row row, int columnCount, Object value, CellStyle style) {
		sheet.autoSizeColumn(columnCount);
		Cell cell = row.createCell(columnCount);
		if (value instanceof Integer) {
			cell.setCellValue((Integer) value);
		} else if (value instanceof Long) {
			cell.setCellValue((Long) value);
		} else if (value instanceof Boolean) {
			cell.setCellValue((Boolean) value);
		} else {
			cell.setCellValue((String) value);
		}
		cell.setCellStyle(style);
	}

	private void write() {
		int rowCount = 1;

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontHeight(14);
		style.setFont(font);

		for (UserResponse user : userList) {
			Row row = sheet.createRow(rowCount++);
			int columnCount = 0;

			createCell(row, columnCount++, user.getUserId(), style);
			createCell(row, columnCount++, user.getFirstName(), style);
			createCell(row, columnCount++, user.getLastName(), style);
			createCell(row, columnCount++, user.getPersonalEmail(), style);
			createCell(row, columnCount++, user.getCountryCode() + "-" + user.getPhoneNumber(), style);

		}
	}

	public void generate(HttpServletResponse response) throws IOException {
		writeHeader();
		write();
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();

		outputStream.close();
	}

}
