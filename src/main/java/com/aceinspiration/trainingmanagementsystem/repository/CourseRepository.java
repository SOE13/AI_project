package com.aceinspiration.trainingmanagementsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.aceinspiration.trainingmanagementsystem.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long>{

	@Query("SELECT c FROM Course c where c.d=null")
	List<Course> findAll();
}
