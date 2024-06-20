package com.example.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import com.example.entity.CitizenPlan;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ExcelUtils {
	
	
	
	public boolean generateExceel(HttpServletResponse res ,List<CitizenPlan> record,File file) throws Exception
	{
		
		// Create new .xlsx workbook
				Workbook workbook=new HSSFWorkbook();
				Sheet sheet = workbook.createSheet("plans-info");// Create a new sheet
				
				Row headerRow = sheet.createRow(0);
				headerRow.createCell(0).setCellValue("ID");
				headerRow.createCell(1).setCellValue("CitizenName");
				headerRow.createCell(2).setCellValue("PlanName");
				headerRow.createCell(3).setCellValue("PlanStatus");
				headerRow.createCell(4).setCellValue("PlanStartDate");
				headerRow.createCell(5).setCellValue("PlanEndDate");
				headerRow.createCell(6).setCellValue("BenefitAmount");
				
			
				
				
				int dataRowIndex=1;
				
				for(CitizenPlan plan:record) {
					Row row = sheet.createRow(dataRowIndex);
					row.createCell(0).setCellValue(plan.getCitizenId());
					row.createCell(1).setCellValue(plan.getCitizenName());
					row.createCell(2).setCellValue(plan.getPlanName());
					row.createCell(3).setCellValue(plan.getPlanStatus());
					
					if(null!= plan.getPlanStartDate()) {
						row.createCell(4).setCellValue(plan.getPlanStartDate()+"");
					}
					else 
						{
						row.createCell(4).setCellValue("N/A");
						}
					if(null!=plan.getPlanEndDate()) {
						row.createCell(5).setCellValue(plan.getPlanEndDate()+"");
					}
					else 
					{
						row.createCell(5).setCellValue("N/A");
					}
					
					if(null!= plan.getBenefitAmount()) {
					row.createCell(6).setCellValue(plan.getBenefitAmount());
					}
					
					else 
						{
							row.createCell(6).setCellValue("N/A");
						}
					
					
					dataRowIndex++;
				}
				
				
				  FileOutputStream fos=new FileOutputStream(file);
				  workbook.write(fos);
				fos.close();
				
				
				
				
				try (ServletOutputStream outputStream = res.getOutputStream()) {
		            workbook.write(outputStream);
		        } finally {
		            workbook.close();
		        }
				return true;
			}

	
		


}
