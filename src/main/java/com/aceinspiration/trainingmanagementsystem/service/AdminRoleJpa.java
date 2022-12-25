package com.aceinspiration.trainingmanagementsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aceinspiration.trainingmanagementsystem.model.Role;
import com.aceinspiration.trainingmanagementsystem.repository.AdminRoleRepository;

@Service
public class AdminRoleJpa {

	@Autowired
	AdminRoleRepository adminRoleJpa;
	
	public List<Role> findAll(){
	return	adminRoleJpa.findAll();
	}
	
	public Role findById(Long id) {
		Optional<Role> optional=adminRoleJpa.findById(id);
		return optional.get();
	}
	
	public void update(Role role) {
		adminRoleJpa.save(role);
	}
	public void insert(Role role) {
		adminRoleJpa.save(role);
	}
	public void deleteById(Long id) {
		adminRoleJpa.deleteById(id);
	}
}
