package com.aceinspiration.trainingmanagementsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aceinspiration.trainingmanagementsystem.model.Timetable;
import com.aceinspiration.trainingmanagementsystem.repository.TimetableRepository;

@Service
public class TimetableJpa {

	@Autowired
	TimetableRepository repo;
	public void insert(Timetable timetable) {
		repo.save(timetable);
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}
	public void update(Timetable timetable) {
		repo.save(timetable);
	}
	public Timetable selectOne(Long id) {
		Optional<Timetable> opt=repo.findById(id);
		return opt.get();
	}
	public List<Timetable> selectAll(){
		return repo.findAll();
	}
}
