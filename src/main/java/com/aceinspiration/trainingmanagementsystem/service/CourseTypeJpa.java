package com.aceinspiration.trainingmanagementsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aceinspiration.trainingmanagementsystem.model.CourseType;
import com.aceinspiration.trainingmanagementsystem.repository.CourseTypeRepository;

@Service
public class CourseTypeJpa {

	@Autowired
	CourseTypeRepository repo;
	public void insert(CourseType courseType) {
		repo.save(courseType);
	}
	
	public List<CourseType> selectAll() {
		return repo.findAll();
	}
	
	public CourseType selectOne(Long id) {
		Optional<CourseType> optio=repo.findById(id);
		return optio.get();
	}
	
	public void update(CourseType courseType) {
		repo.save(courseType);
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}
}
