package com.aceinspiration.trainingmanagementsystem.service;


import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aceinspiration.trainingmanagementsystem.model.StudentBatch;
import com.aceinspiration.trainingmanagementsystem.model.StudentPayment;
import com.aceinspiration.trainingmanagementsystem.repository.StudentPaymentRepository;

@Service
@Transactional
public class StudentPaymentJpa {
	
	@Autowired
	private StudentPaymentRepository stuPayRepo;
	
	@Autowired
	private StudentBatchJpa stBatchService;
	
	
	public List<StudentPayment> getAllBatch(){
		return stuPayRepo.findAll();
	}
	
	public List<StudentPayment> getStudentPaymentByStBatchId(long studentBatchId){
		Optional<StudentBatch> studentBatchOptional = stBatchService.getStudentBatchById(studentBatchId);
		if(studentBatchOptional.isPresent()) {
			return stuPayRepo.findByStudentBatchId(studentBatchId);
		}
		return null;
		
	}
	
	
	public Optional<StudentPayment> getStuPayById(Long id) {
		return stuPayRepo.findById(id);
	}
	
	public void delete(long id) {
        stuPayRepo.deleteById(id);
    }
	
	public void update(StudentPayment payment){
		stuPayRepo.save(payment);
	}
	
	public void saveStudentPayment(StudentBatch studentbatchId, String paidDate, String installment, Double actualAmount,
			Double paidAmount, Double remainAmount, String voucherNo) {
		

		StudentPayment studentPayment = new StudentPayment( studentbatchId, paidDate, installment, actualAmount,
				 paidAmount, remainAmount, voucherNo) ;
		stuPayRepo.save(studentPayment);
		
	}

}
