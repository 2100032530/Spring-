package com.example.demo.service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import com.example.demo.constants.Constants;
import com.example.demo.model.Contact;
import com.example.demo.repository.ContactRepository;

import lombok.extern.slf4j.Slf4j;


/*
@Slf4j, is a Lombok-provided annotation that will automatically generate an SLF4J
Logger static property in the class at compilation time.
* */
@Slf4j
@Service
//@RequestScope
//@SessionScope
@ApplicationScope
public class ContactService {
	
	@Autowired
	ContactRepository cr;

    private int counter = 0;

    public ContactService(){
        System.out.println("Contact Service Bean initialized");
    }

    /**
     * Save Contact Details into DB
     * @param contact
     * @return boolean
     */
    public boolean saveContactDetails(Contact contact){
        boolean isSaved = false;
        contact.setStatus(Constants.OPEN);
        contact.setCreatedAt(LocalDateTime.now());
        contact.setCreatedBy(Constants.user);
        Contact savedContact = cr.save(contact);
        if(null != savedContact && savedContact.getContact_id()>0) {
            isSaved = true;
        }
        return isSaved;
    }

	public List<Contact> findMessagesWithOpenStatus() {
		// TODO Auto-generated method stub
		List<Contact> contactMsgs = cr.findByStatus(Constants.OPEN);
        return contactMsgs;
	}

	public boolean updateMsgStatus(int contact_id){
        boolean isUpdated = false;
        Optional<Contact> contact = cr.findById(contact_id);
        contact.ifPresent(contact1 -> {
            contact1.setStatus(Constants.CLOSE);
        });
        Contact updatedContact = cr.save(contact.get());
        if(null != updatedContact && updatedContact.getUpdatedBy()!=null) {
            isUpdated = true;
        }
        return isUpdated;
    }
		
		
	}

//    public int getCounter() {
//        return counter;
//    }
//
//    public void setCounter(int counter) {
//        this.counter = counter;
//    }
