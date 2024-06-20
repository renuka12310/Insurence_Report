package com.example.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.entity.CitizenPlan;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;

@Component
public class PdfUtils {
	
	public boolean generatePdf(HttpServletResponse res, List<CitizenPlan> plans,File f) throws Exception
	{
		Document document = new Document(PageSize.A4);
		
		PdfWriter.getInstance(document, res.getOutputStream());
		PdfWriter.getInstance(document, new FileOutputStream(f)  );
		
		document.open();
		
		Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		font.setSize(20);
		
		Paragraph p=new Paragraph("Citizen Plan Info",font);
		
		p.setAlignment(Element.ALIGN_CENTER);
		document.add(p);
		
		// Create a table with 4 columns
        PdfPTable table = new PdfPTable(6);
        table.addCell("ID");
        table.addCell("Citizen Name");
        table.addCell("Plan Name");
        table.addCell("Plan Status");
        table.addCell("Start Date");
        table.addCell("End Date");
        
        
        
        for(CitizenPlan plan:plans) {
        	table.addCell(String.valueOf(plan.getCitizenId()));
        	table.addCell(plan.getCitizenName());
        	table.addCell(plan.getPlanName());
        	table.addCell(plan.getPlanStatus());
        	
        	if(null!= plan.getPlanStartDate()) {
        		table.addCell(plan.getPlanStartDate()+"");
			}
			else 
				{
				   table.addCell("N/A");
				}
			if(null!=plan.getPlanEndDate()) {
				table.addCell(plan.getPlanEndDate()+"");
	        	
			}
			else 
			{
				table.addCell("N/A");
			}
        }
        
        document.add(table);
		document.close();
		
		return true;
	}

	

}
