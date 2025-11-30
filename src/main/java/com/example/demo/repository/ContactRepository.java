package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Contact;
import java.util.List;


@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer>{
	
	List<Contact> findByStatus(String status);

}
