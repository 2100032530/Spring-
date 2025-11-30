package com.example.demo.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.constants.Constants;
import com.example.demo.model.Person;
import com.example.demo.model.Roles;
import com.example.demo.repository.PersonRepository;
import com.example.demo.repository.RoleRepository;

import jakarta.validation.Valid;

@Service
public class PublicService {
	
	@Autowired
	PasswordEncoder pe;
	
	@Autowired
	RoleRepository rr;
	
	@Autowired
	PersonRepository pr;

	public boolean createNewPerson(@Valid Person person) {
		boolean isSaved = false;
        Roles role = rr.getByRoleName(Constants.STUDENT_ROLE);
        person.setRoles(role);
        person.setCreatedAt(LocalDateTime.now());
        person.setCreatedBy(Constants.STUDENT_ROLE);
        person.setPwd(pe.encode(person.getPwd()));
        Person savedperson = pr.save(person);
        if (null != savedperson && savedperson.getPersonId() > 0)
        {
            isSaved = true;
        }
        return isSaved;
	}

}
