package com.example.service;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.example.entity.CitizenPlan;
import com.example.repo.CitizenRepo;
import com.example.request.SearchRequest;
import com.example.utils.EmailUtils;
import com.example.utils.ExcelUtils;
import com.example.utils.PdfUtils;


import jakarta.servlet.http.HttpServletResponse;

@Service
public class ReportServiceImpl implements ReportService{

	
	@Autowired
	private CitizenRepo repo;
	
	@Autowired
	private ExcelUtils excel;
	
	@Autowired
	private PdfUtils pdf; 
		
	@Autowired
	private EmailUtils email;
	
	@Override
	public List<String> getPlanNames() {
		return repo.getAllPlanNames();
	}

	@Override
	public List<String> getPlanStatus() {
		return repo.getPlanStatus();
	}

	@Override
	public List<CitizenPlan> search(SearchRequest request) {
		
		CitizenPlan entity=new CitizenPlan();
		
		
		if (request.getPlanName() != null && !request.getPlanName().isEmpty()) {
		    entity.setPlanName(request.getPlanName());
		}
		
		if (request.getPlanStatus() != null && !request.getPlanStatus().isEmpty()) {
		    entity.setPlanStatus(request.getPlanStatus());
		}
		
		if (request.getGender() != null && !request.getGender().isEmpty()) {
	        entity.setGender(request.getGender());
	    }
		
		// Handle StartDate and EndDate
	    if (request.getStartDate() != null && !request.getStartDate().isEmpty()) {
	    	String sdate = request.getStartDate();
	        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        LocalDate date = LocalDate.parse(sdate, dateFormatter);
	        entity.setPlanStartDate(date);

	    }
	    
	    if (request.getEndDate() != null && !request.getEndDate().isEmpty()) {
	    	String edate = request.getEndDate();
	        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        LocalDate endate = LocalDate.parse(edate, dateFormatter);
			entity.setPlanEndDate(endate);

	    }

		//BeanUtils.copyProperties(request, entity);
		return repo.findAll(Example.of(entity));
	}

	@Override
	public boolean exportExcel(HttpServletResponse res) throws Exception{
		
		 File f=new File("plans.xls");
		 
		List<CitizenPlan> plans = repo.findAll();
		excel.generateExceel(res, plans, f);
		
		String subject ="test this subjectl";
		String body="<h1>this is body</h1>";
		String to ="renukarajaput71@gmail.com";
		
		email.mailSender(subject, body, to,f);
		
		f.delete();
		return true;
	}

	@Override
	public boolean exportpdf(HttpServletResponse res) throws Exception {

		 File f=new File("plans.pdf");		 
	  List<CitizenPlan> plans = repo.findAll();
	  
    	pdf.generatePdf(res, plans,f);
    	
    	String subject ="test this subjectl";
		String body="<h1>this is body</h1>";
		String to ="renukarajaput71@gmail.com";
		
		email.mailSender(subject, body, to,f);
		
		f.delete();
    	
		return true;
	}

	

}
