package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Person;
import com.example.demo.service.PublicService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/public")
public class PublicController {

	@Autowired
	PublicService ps;

	@GetMapping("/register")
	public String displayRegisterPage(Model model) {
		model.addAttribute("person", new Person());
		return "register";
	}

	@PostMapping("/createUser")
	public String createUser(@Valid @ModelAttribute("person") Person person, Errors errors) {
		if (errors.hasErrors()) {
			return "register";
		}
		boolean isSaved = ps.createNewPerson(person);
		if (isSaved) {
			return "redirect:/login?register=true";
		} else {
			return "register";
		}
	}

}
