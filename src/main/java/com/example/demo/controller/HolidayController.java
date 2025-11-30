package com.example.demo.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Holiday;
import com.example.demo.repository.HolidayRepository;


@Controller
public class HolidayController {
	
	@Autowired
	HolidayRepository hr;
	
	 @GetMapping("/holidays/{display}")
	 public String displayHolidays(@PathVariable String display, Model model) {
		 
		 if(display!=null && display.equals("all")) {
			 model.addAttribute("festival",true);
             model.addAttribute("federal",true);
		 }
		 else if(display!=null && display.equals("federal")) {
			 model.addAttribute("federal",true);
		 }
		 else if(display!=null && display.equals("festival")) {
			 model.addAttribute("festival",true);
		 }
             
	        List<Holiday> holidays = hr.findAll();
//	                new Holiday(" Jan 1 ","New Year's Day", Holiday.Type.FESTIVAL),
//	                new Holiday(" Oct 31 ","Halloween", Holiday.Type.FESTIVAL),
//	                new Holiday(" Nov 24 ","Thanksgiving Day", Holiday.Type.FESTIVAL),
//	                new Holiday(" Dec 25 ","Christmas", Holiday.Type.FESTIVAL),
//	                new Holiday(" Jan 17 ","Martin Luther King Jr. Day", Holiday.Type.FEDERAL),
//	                new Holiday(" July 4 ","Independence Day", Holiday.Type.FEDERAL),
//	                new Holiday(" Sep 5 ","Labor Day", Holiday.Type.FEDERAL),
//	                new Holiday(" Nov 11 ","Veterans Day", Holiday.Type.FEDERAL)
//	        );
	        Holiday.Type[] types = Holiday.Type.values();
	        for (Holiday.Type type : types) {
	            model.addAttribute(type.toString(),
	                    (holidays.stream().filter(holiday -> holiday.getType().equals(type)).collect(Collectors.toList())));
	        }
	        return "holidays";
	    }
}


