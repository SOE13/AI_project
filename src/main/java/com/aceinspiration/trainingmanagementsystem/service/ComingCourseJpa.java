package com.aceinspiration.trainingmanagementsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aceinspiration.trainingmanagementsystem.model.ComingCourse;
import com.aceinspiration.trainingmanagementsystem.repository.ComingCourseRepository;

@Service
public class ComingCourseJpa {

	@Autowired
	ComingCourseRepository repo;
	
	public void delete(Long id) {
		repo.deleteById(id);
	}
	public void update(ComingCourse course) {
		repo.save(course);
	}
	public ComingCourse selectOne(Long id) {
		Optional<ComingCourse> optional=repo.findById(id);
		 return optional.get();
	}
	public List<ComingCourse> selectAll(){
		return repo.findAll();
	}
	public void insert(ComingCourse course) {
		repo.save(course);
	}
}
