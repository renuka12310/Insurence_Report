package com.example.service;

import java.util.List;

import com.example.entity.CitizenPlan;
import com.example.request.SearchRequest;

import jakarta.servlet.http.HttpServletResponse;

public interface ReportService {

	//method to get plan-name drop down data
		public List<String> getPlanNames();
		
		//method to get plan-status drop down data
		public List<String> getPlanStatus();
		
		//method to handle search functionality
		public List<CitizenPlan> search(SearchRequest request);
		
		//method to export data to excel file
		public boolean exportExcel(HttpServletResponse res) throws Exception;
		
		//method to export data to pdf file
		public boolean exportpdf (HttpServletResponse res) throws Exception;

}
