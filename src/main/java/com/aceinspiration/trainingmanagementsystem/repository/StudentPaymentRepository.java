package com.aceinspiration.trainingmanagementsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aceinspiration.trainingmanagementsystem.model.StudentBatch;
import com.aceinspiration.trainingmanagementsystem.model.StudentPayment;

@Repository
public interface StudentPaymentRepository extends JpaRepository<StudentPayment, Long> {
	
	public List<StudentPayment> findByStudentBatchId(long studentBatchId);
	//public List<StudentPayment> findByVoucherNo(String voucherNo);
	

}
