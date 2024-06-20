package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.entity.CitizenPlan;
import com.example.request.SearchRequest;
import com.example.service.ReportService;

import jakarta.servlet.http.HttpServletResponse;


@Controller
public class ReportController {

	@Autowired
	private ReportService service;
	
	
	@PostMapping("/searchData")
	public String handlerSearch(@ModelAttribute("search") SearchRequest search , Model model)
	{
		
		
		List<CitizenPlan> plans = service.search(search);
		model.addAttribute("plans", plans);
		
		model.addAttribute("search", new SearchRequest()); 
		
		init(model);
		return "index";
	}
	
	
	@GetMapping("/")
	public String indexPage(Model model)
	{
		model.addAttribute("search", new SearchRequest());
		init(model);
		return "index";
	}
	
	private void init(Model model) {
		//model.addAttribute("search", new SearchRequest());
		model.addAttribute("names", service.getPlanNames());
		model.addAttribute("status", service.getPlanStatus());
	}
	
	@GetMapping("/excel")
	public void excelExport(HttpServletResponse res,Model model) throws Exception
	{
		res.setContentType("application/octet-stream");
		res.addHeader("Content-Disposition", "attachment;filename=plans.xls");
		
		boolean status = service.exportExcel(res);
		
		/*
		 * if(status) {
		 * model.addAttribute("msg", "excel report send to email");
		 * }
		 * model.addAttribute("search", new SearchRequest()); // Ensure search object is
		 * added init(model); return "index";
		 */
	}
	
	@GetMapping("/pdf")
	public void pdfExport(HttpServletResponse res,Model model) throws Exception
	{
		res.setContentType("application/pdf");
		res.addHeader("Content-Disposition", "attachment;filename=plans.pdf");
		
		boolean status = service.exportpdf(res);
		
		/*
		 * if(status) {
		 * 
		 * model.addAttribute("msg", "pdf report send to email");
		 * 
		 * 
		 * } model.addAttribute("search", new SearchRequest()); // Ensure search object
		 * is added init(model); return "index";
		 */
	}
	
}
