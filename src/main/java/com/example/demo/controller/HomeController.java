package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping({"/home","","/"})
	public String homePage() { //Model is an interface in spring mvc framework that act as mediator between ui and spring
		return "home";
	}

}
