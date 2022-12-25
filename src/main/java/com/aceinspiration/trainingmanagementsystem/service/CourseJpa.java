package com.aceinspiration.trainingmanagementsystem.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aceinspiration.trainingmanagementsystem.model.Course;
import com.aceinspiration.trainingmanagementsystem.repository.CourseRepository;

@Service
public class CourseJpa {

	@Autowired
	CourseRepository repo;
	@Autowired
	EntityManager entity;

	public void save(Course course) {
		repo.save(course);
	}
	
	public Course selectOneForMarking(String course) {
		TypedQuery<Course> query=entity.createQuery("from Course c where c.name=?1 and c.d=null",Course.class);
		query.setParameter(1, course);
		return query.getSingleResult();
	}

	public List<Course> selectAll() {
		return repo.findAll();
	}

	public Course select(Long id) {
		return entity.find(Course.class, id);
	}

	public Course selectOne(Long id) {
		Optional<Course> optional = repo.findById(id);
		return optional.get();
	}

	public void update(Course course) {
		repo.save(course);
	}

	public Optional<Course> getCourseById(long id) {
		return repo.findById(id);
	}
}
