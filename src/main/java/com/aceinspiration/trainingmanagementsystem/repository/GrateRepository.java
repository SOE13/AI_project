package com.aceinspiration.trainingmanagementsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.aceinspiration.trainingmanagementsystem.model.Course;
import com.aceinspiration.trainingmanagementsystem.model.Grate;

public interface GrateRepository extends JpaRepository<Grate, Long>{

	@Query("SELECT g FROM Grate g WHERE g.d=null")
	public List<Grate> findAll();
	@Query("SELECT g FROM Grate g WHERE g.fromMark<=?1 and g.toMark>=?1 and g.course=?2 and g.d=null")
	public List<Grate> findForCourse(Integer k2, Course course);
	
	
	
}
