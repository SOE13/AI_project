package com.aceinspiration.trainingmanagementsystem.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aceinspiration.trainingmanagementsystem.model.Attendace;
import com.aceinspiration.trainingmanagementsystem.model.StudentBatch;
import com.aceinspiration.trainingmanagementsystem.repository.AttendanceRepository;

@Service
public class AttendanceJpa {

	@Autowired
	AttendanceRepository repo;
	
	public void save(Attendace attendace) {
		repo.save(attendace);
	}
	
	public void update(Attendace attendace) {
		repo.save(attendace);
	}
	
	public Attendace selectOne(Long id) {
		Optional<Attendace> optional=repo.findById(id);
		return optional.get();
	}
	
	public List<Attendace> selectByStudentBatch(StudentBatch studentBatch){
		return repo.findByStudentBatch(studentBatch);
	}
	
	public Attendace findStudentBatchDate(StudentBatch studentBatch, String date) {
		return repo.findStudentBatchDate(studentBatch, Date.valueOf(date));
	}
	
	public List<Attendace> selectByStudentBatch(StudentBatch studentBatch, String date1, String date2){
		return repo.findByStudentBatch(studentBatch, Date.valueOf(date1), Date.valueOf(date2));
	}
	
	public List<Attendace> selectByStudentBatchForValue(StudentBatch studentBatch){
		return repo.findByStudentBatchForValue(studentBatch);
	}
	public List<Attendace> selectByStudentBatchForMarking(StudentBatch studentBatch){
		return repo.findByStudentBatchForMarking(studentBatch);
	}
	
	public List<Attendace> selectByDate(Date date){
		return repo.findByDate(date);
	}
	
	public List<Attendace> select(){
		return repo.findAll();
	}
}
