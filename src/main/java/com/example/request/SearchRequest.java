package com.example.request;

import lombok.Data;

@Data
public class SearchRequest {

	private String planName;
	private String planStatus;	
	private String gender; 
	//@DateTimeFormat(pattern = "yyyy-mm-dd")
	private String StartDate;  
	//@DateTimeFormat(pattern = "yyyy-mm-dd")
	private String EndDate;
	
}
