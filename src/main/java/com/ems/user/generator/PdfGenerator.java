package com.ems.user.generator;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.ems.user.dto.response.UserResponse;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class PdfGenerator {

	public void generate(List<UserResponse> userList, HttpServletResponse response)
			throws DocumentException, IOException {

		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();

		Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		fontTiltle.setSize(20);
		Paragraph paragraph1 = new Paragraph("Employee List", fontTiltle);
		paragraph1.setAlignment(Paragraph.ALIGN_CENTER);
		paragraph1.setSpacingAfter(72f);
		document.add(paragraph1);
		
		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(100f);
		table.setWidths(new int[] { 3, 3, 3, 3, 3});
		table.setSpacingBefore(5);
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(CMYKColor.BLUE);
		cell.setPadding(5);
		Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		font.setColor(CMYKColor.WHITE);
		cell.setPhrase(new Phrase("User ID", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("First Name", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Last Name", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Email", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Phone Number", font));
		table.addCell(cell);

		for (UserResponse user : userList) {
			table.addCell(String.valueOf(user.getUserId()));
			table.addCell(user.getFirstName());
			table.addCell(user.getLastName());
			table.addCell(user.getPersonalEmail());
			table.addCell(user.getCountryCode() + "-" + user.getPhoneNumber());
		}
		document.add(table);
		document.close();
	}

}
