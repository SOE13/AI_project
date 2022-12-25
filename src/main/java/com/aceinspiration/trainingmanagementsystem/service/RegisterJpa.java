package com.aceinspiration.trainingmanagementsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aceinspiration.trainingmanagementsystem.model.Register;
import com.aceinspiration.trainingmanagementsystem.repository.RegisterRepository;

@Service
public class RegisterJpa {

	@Autowired
	private RegisterRepository entity;
	public void save(Register register) {
		entity.save(register);
	}
	public Register selectOne(Long id) {
		Optional<Register> optional=entity.findById(id);
		return optional.get();
	}
	public void update(Register register) {
		entity.save(register);
	}
	public Page<Register> select(int i){
		Pageable pageable=PageRequest.of(i-1, 10);
		return entity.findAll(pageable);
	}
	public Page<Register> selectForReport(int i, String k1, String k2, String k3) {
		Pageable pageable = PageRequest.of(i - 1, 10);

		if (k1 != "" && k2 != "") {
			System.out.println("if");
			return entity.findAllforRepord(k1, k2, pageable);
		} else if (k3 != "") {
			System.out.println("else if");
			return entity.findAllforRepord(k3, pageable);
		}else {
			return entity.findAllforRepord(pageable);
		}

	}
	
	public List<Register> findByStatus(String status){
		return entity.findByStatus(status);
	}

	
}
