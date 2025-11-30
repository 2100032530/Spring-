package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Roles;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Integer>{
	
	Roles getByRoleName(String roleName);

}
