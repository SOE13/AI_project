package com.aceinspiration.trainingmanagementsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aceinspiration.trainingmanagementsystem.model.Course;
import com.aceinspiration.trainingmanagementsystem.model.Grate;
import com.aceinspiration.trainingmanagementsystem.repository.GrateRepository;

@Service
public class GrateJpa {

	@Autowired
	GrateRepository repo;
	
	@Autowired
	CourseJpa courseJpa;
	
	public void save(Grate grate) {
		repo.save(grate);
	}
	public void update(Grate grate) {
		repo.save(grate);
	}
	public List<Grate> select(){
		return repo.findAll();
	}
	public Grate selectOne(Long id) {
		Optional<Grate> op=repo.findById(id);
		return op.get();
	}
	
	public List<Grate> selectGrate(String k1,Integer k2){
		Course course=courseJpa.selectOneForMarking(k1);
		return repo.findForCourse(k2, course);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
