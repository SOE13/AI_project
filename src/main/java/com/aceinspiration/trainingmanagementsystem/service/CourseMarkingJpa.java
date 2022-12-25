package com.aceinspiration.trainingmanagementsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aceinspiration.trainingmanagementsystem.model.Course;
import com.aceinspiration.trainingmanagementsystem.model.CourseMarking;
import com.aceinspiration.trainingmanagementsystem.repository.CourseMarkingRepository;

@Service
public class CourseMarkingJpa {

	@Autowired
	CourseMarkingRepository repo;
	
	public CourseMarking selectOne(Long id) {
		Optional<CourseMarking> op=repo.findById(id);
		return op.get();
	}
	
	public List<CourseMarking> selectByCourse(Course course){
		return repo.findByCourse(course);
	}

	public List<CourseMarking> selectAll() {
		return repo.findAll();
	}
	
	public void delete(CourseMarking courseMarking) {
		repo.delete(courseMarking);
	}
	
	public void update(CourseMarking courseMarking) {
    	repo.save(courseMarking);
    }
	public void insert(CourseMarking courseMarking) {
		repo.save(courseMarking);
    }
}
