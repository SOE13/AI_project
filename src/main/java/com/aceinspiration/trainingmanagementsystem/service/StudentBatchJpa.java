package com.aceinspiration.trainingmanagementsystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aceinspiration.trainingmanagementsystem.formmodel.RatingForm;
import com.aceinspiration.trainingmanagementsystem.formmodel.StudentRelatedForm;
import com.aceinspiration.trainingmanagementsystem.model.Batch;
import com.aceinspiration.trainingmanagementsystem.model.DiscountType;
import com.aceinspiration.trainingmanagementsystem.model.Student;
import com.aceinspiration.trainingmanagementsystem.model.StudentBatch;
import com.aceinspiration.trainingmanagementsystem.repository.StudentBatchRepository;

@Service
@Transactional
public class StudentBatchJpa {

	@Autowired
	private StudentBatchRepository studentBatchRepository;

	@Autowired
	private StudentJpa studentService;

	@Autowired
	private BatchJpa batchService;

	@Autowired
	private DiscountTypeJpa discountTypeService;

	public Optional<StudentBatch> getStudentBatchById(long id) {
		return studentBatchRepository.findById(id);
	}

	public List<StudentBatch> selectByStudent(Long id){
		return studentBatchRepository.findByStudent(id);
	}
	
	public List<StudentBatch> selectAll(Batch key) {

		if (key == null) {
			return studentBatchRepository.findAll();
		}
		return studentBatchRepository.findBybatch(key);
	}
	
	public Long getStudentCount(Long id) {
		return studentBatchRepository.getCountBatch(id);
	}

	public List<StudentBatch> getAllStudentBatch() {
		return studentBatchRepository.findAll();
	}

	public void saveStudentBatch(long studentId, long batchId, long discountTypeId, Double totalAmount,
			Double actualAmount, Double discountAmount, String status) {
		StudentBatch studentBatch = new StudentBatch();

		Optional<Student> studentOption = studentService.selectOneOp(studentId);

		Optional<Batch> batchOption = batchService.getBatchById(batchId);

		DiscountType discount = null;
		if (discountTypeId == 0) {
			discount = null;
		} else {
			Optional<DiscountType> discountTypeOption = discountTypeService.getDiscountTypById(discountTypeId);
			if (discountTypeOption.isPresent()) {
				discount = discountTypeOption.get();
			}

		}

		if (studentOption.isPresent() && batchOption.isPresent()) {
			studentBatch.setBatch(batchOption.get());
			studentBatch.setStudent(studentOption.get());
			studentBatch.setDiscountType(discount);
			studentBatch.setActualAmount(actualAmount);
			studentBatch.setTotalAmount(totalAmount);
			studentBatch.setDiscountAmount(discountAmount);
			studentBatch.setStatus(status);
			studentBatchRepository.save(studentBatch);
		}

	}

	
	
	public List<StudentBatch> getStudentBatchInfo(String studentCode) {
		List<Student> students = studentService.getStudentByStudentCode(studentCode);
		List<StudentBatch> studentBatchs = new ArrayList<>();
		if (students != null) {
			for (Student student : students) {
				List<StudentBatch> studentBatchList = studentBatchRepository.findByStudent(student);
				for (StudentBatch studentBatch : studentBatchList) {
					studentBatchs.add(studentBatch);
				}
			}
			return studentBatchs;
		}

		return null;
	}
	
	public List<StudentRelatedForm> findBatchNamebyStudent(String studId) {
		List<StudentRelatedForm> lstBatch = studentBatchRepository.findBatchNamebyStid(studId);
		System.out.println("BatchSize : " + lstBatch.size());
		for (StudentRelatedForm cr : lstBatch) {
			System.out.println("Batch Name : " + cr.getBatchName());
		}
		return lstBatch;
	}

	public List<StudentRelatedForm> findAllStudentOrderbyStid() {
		List<StudentRelatedForm> lstBatch = studentBatchRepository.findAllStudentOrderbyStid();
		System.out.println("BatchSize : " + lstBatch.size());
		for (StudentRelatedForm cr : lstBatch) {
			System.out.println("Batch Name : " + cr.getBatchName());
		}
		return lstBatch;
	}

	
	
	public void update(StudentBatch studBatch) {
		studentBatchRepository.save(studBatch);
	}
	
	public StudentBatch findByStudentIdAndBatchId(Long batchId, Long studentId) {
		System.out.println("batch : "+batchId+", student : "+studentId);
		 return studentBatchRepository.findByStudentIdAndBatchId(batchId, studentId);
	 }
	
	
	public List<RatingForm> getBatchCountByBatchId() {

		return studentBatchRepository.getBatchCountByBatchId();
	}
	
	public List<StudentBatch> findByStudent(Student student){
		return studentBatchRepository.findByStudent(student);
	}
}
