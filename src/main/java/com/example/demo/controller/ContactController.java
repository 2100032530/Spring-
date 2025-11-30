package com.example.demo.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Contact;
import com.example.demo.service.ContactService;

import jakarta.validation.Valid;



@Controller
public class ContactController {
	
	@Autowired
	ContactService cs;
	
	private static Logger log=LoggerFactory.getLogger(ContactController.class);
	
	@GetMapping("/contact")
	public String contactPage(Model model) {
		model.addAttribute("contact", new Contact());
		return "contact";
	}
	
	/*@RequestMapping(value = "/saveMsg",method = RequestMethod.POST)
    public ModelAndView saveMessage(@RequestParam String name, @RequestParam String mobileNum,
                                    @RequestParam String email, @RequestParam String subject, @RequestParam String message) {
        log.info("Name : " + name);
        log.info("Mobile Number : " + mobileNum);
        log.info("Email Address : " + email);
        log.info("Subject : " + subject);
        log.info("Message : " + message);
        return new ModelAndView("redirect:/contact");
        //ModelAndView is a class in spring mvc architecture which is used to return data and view to the ui. if there are more fields this approach doesn't look best so we choose pojo class here
    }*/
 
	@RequestMapping(value = "/saveMsg",method = RequestMethod.POST)
	public String saveMessage(@Valid @ModelAttribute("contact") Contact contact, Errors errors) {
		if(errors.hasErrors()) {
			log.error("Contact Form Validation failed due to : ", errors.toString());
			return "contact"; // but here we r not returing new html page just displaying the same page with errors
		}
		cs.saveContactDetails(contact);
		//cs.setCounter(cs.getCounter()+1);
        //log.info("Number of times the Contact form is submitted : "+cs.getCounter());
		return "redirect:/contact"; // here we are invoking contact page again from start like fresh page
	}
	
	@RequestMapping("/displayMessages")
    public ModelAndView displayMessagesWithOpenStatus(Model model) {
        List<Contact> contactMsgs = cs.findMessagesWithOpenStatus();
        ModelAndView modelAndView = new ModelAndView("messages");
        modelAndView.addObject("contactMsgs",contactMsgs);
        return modelAndView;
	}
	
	 @RequestMapping("/closeMsg")
	    public String closeMsg(@RequestParam int contact_id) {
	        cs.updateMsgStatus(contact_id);
	        return "redirect:/displayMessages";
	    }
	

	
	 
	
}
