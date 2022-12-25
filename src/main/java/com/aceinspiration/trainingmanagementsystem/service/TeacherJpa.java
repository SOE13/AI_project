package com.aceinspiration.trainingmanagementsystem.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aceinspiration.trainingmanagementsystem.model.Teacher;
import com.aceinspiration.trainingmanagementsystem.repository.TeacherRepository;

@Service
public class TeacherJpa {
	@Autowired
	EntityManager entityM;
	@Autowired
	TeacherRepository entity;
	
	public List<Teacher> selectLatest() {
		return  entity.findAll();
	}
	
	public List<Teacher> selectDeleted(){
		return entity.findDeleted();
	}
	
	public Teacher selectOne(Long id) {
		Optional<Teacher> optional=entity.findById(id);
		return optional.get();
	}
	
	
	public Page<Teacher> select(int pageNumber){
		Pageable pageable=PageRequest.of(pageNumber-1, 5);
		return entity.findAll(pageable);
		
	}
	public void update(Teacher teacher) {
		entity.save(teacher);
	}
	
	public void save(Teacher teacher) {
		entity.save(teacher);
	}
	
	public Optional<Teacher> getTeacherById(Long id){
		return entity.findById(id);
	}
}
