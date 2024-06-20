package com.example.runner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.example.entity.CitizenPlan;
import com.example.repo.CitizenRepo;

@Component
public class DataLoader implements ApplicationRunner {

	@Autowired
	private CitizenRepo repo;

	public void run(ApplicationArguments args) throws Exception {

		//delete all records and insert again
		repo.deleteAll();
		
		CitizenPlan c1 = new CitizenPlan();

		// cash plan data
		c1.setCitizenName("Rohan");
		c1.setGender("Male");
		c1.setPlanName("Cash");
		c1.setPlanStatus("Approved");
		c1.setPlanStartDate(LocalDate.now());
		c1.setPlanEndDate(LocalDate.now().plusMonths(6));
		c1.setBenefitAmount(5000.00);

		CitizenPlan c2 = new CitizenPlan();
		c2.setCitizenName("Johan");
		c2.setGender("Male");
		c2.setPlanName("Cash");
		c2.setPlanStatus("Denied");
		c2.setDenialReason("Rental income");

		CitizenPlan c3 = new CitizenPlan();
		c3.setCitizenName("Cathy");
		c3.setGender("FeMale");
		c3.setPlanName("Cash");
		c3.setPlanStatus("Terminated");
		c3.setPlanStartDate(LocalDate.now().minusMonths(4));
		c3.setPlanEndDate(LocalDate.now().plusMonths(6));
		c3.setBenefitAmount(5000.00);
		c3.setTermintedDate(LocalDate.now());
		c3.setTerminatedReason("Employed");

		// food plan data
		CitizenPlan c4 = new CitizenPlan();
		c4.setCitizenName("Rani");
		c4.setGender("female");
		c4.setPlanName("Food");
		c4.setPlanStatus("Approved");
		c4.setPlanStartDate(LocalDate.now());
		c4.setPlanEndDate(LocalDate.now().plusMonths(6));
		c4.setBenefitAmount(4000.00);

		CitizenPlan c5 = new CitizenPlan();
		c5.setCitizenName("Jaghan");
		c5.setGender("Male");
		c5.setPlanName("Food");
		c5.setPlanStatus("Denied");
		c5.setDenialReason("property income");

		CitizenPlan c6 = new CitizenPlan();
		c6.setCitizenName("Caamroni");
		c6.setGender("FeMale");
		c6.setPlanName("Food");
		c6.setPlanStatus("Terminated");
		c6.setPlanStartDate(LocalDate.now().minusMonths(4));
		c6.setPlanEndDate(LocalDate.now().plusMonths(6));
		c6.setBenefitAmount(4000.00);
		c6.setTermintedDate(LocalDate.now());
		c6.setTerminatedReason(" Salaried Employed");

		// medical plan data
		CitizenPlan c7 = new CitizenPlan();
		c7.setCitizenName("Ramani");
		c7.setGender("female");
		c7.setPlanName("Medical");
		c7.setPlanStatus("Approved");
		c7.setPlanStartDate(LocalDate.now());
		c7.setPlanEndDate(LocalDate.now().plusMonths(6));
		c7.setBenefitAmount(8000.00);

		CitizenPlan c8 = new CitizenPlan();
		c8.setCitizenName("Chandra");
		c8.setGender("Male");
		c8.setPlanName("Medical");
		c8.setPlanStatus("Denied");
		c8.setDenialReason("Hospital income");

		CitizenPlan c9 = new CitizenPlan();
		c9.setCitizenName("Caamroni");
		c9.setGender("FeMale");
		c9.setPlanName("Medical");
		c9.setPlanStatus("Terminated");
		c9.setPlanStartDate(LocalDate.now().minusMonths(4));
		c9.setPlanEndDate(LocalDate.now().plusMonths(6));
		c9.setBenefitAmount(8000.00);
		c9.setTermintedDate(LocalDate.now());
		c9.setTerminatedReason("Health");
		
		List<CitizenPlan> list = Arrays.asList(c1,c2,c3,c4,c5,c6,c7,c8,c9);
		repo.saveAll(list);

	}
}