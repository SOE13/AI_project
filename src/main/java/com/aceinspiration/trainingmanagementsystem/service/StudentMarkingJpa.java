package com.aceinspiration.trainingmanagementsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aceinspiration.trainingmanagementsystem.formmodel.StudentMarkingForm;
import com.aceinspiration.trainingmanagementsystem.model.Batch;
import com.aceinspiration.trainingmanagementsystem.model.StudentBatch;
import com.aceinspiration.trainingmanagementsystem.model.StudentMarking;
import com.aceinspiration.trainingmanagementsystem.model.StudentPayment;
import com.aceinspiration.trainingmanagementsystem.repository.StudentMarkingRepository;

@Service
public class StudentMarkingJpa {

	@Autowired
	StudentMarkingRepository repo;
	public StudentMarking findOne(Long id) {
		Optional<StudentMarking> op=repo.findById(id);
		return op.get();
	}
	public List<StudentMarking> findAll(StudentBatch studentBatch){
		if(studentBatch==null) {
			return repo.findAll();
		}
			return repo.findByStudentBatch(studentBatch);
	}
	public void save(StudentMarking studentMark) {
		repo.save(studentMark);
	}
	public void update(StudentMarking studentMark) {
		repo.save(studentMark);
	}
	public List<StudentMarkingForm> findMarkingByBatchName(String batchName){
		System.out.println("batch name :"+batchName);
		List<StudentMarkingForm> list=repo.findMarkingByBatchName(batchName);
		System.out.println("size :"+list.size());
		return list;
	}
	
	public StudentMarking findByStudentBatchId(long studentBatchId) {
		return repo.findByStudentBatchId(studentBatchId);
		
	}
	
	
	
}
