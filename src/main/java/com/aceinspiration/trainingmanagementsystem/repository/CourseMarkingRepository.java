package com.aceinspiration.trainingmanagementsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aceinspiration.trainingmanagementsystem.model.Course;
import com.aceinspiration.trainingmanagementsystem.model.CourseMarking;

public interface CourseMarkingRepository extends JpaRepository<CourseMarking, Long> {

	
	List<CourseMarking> findByCourse(Course course);
	

}
