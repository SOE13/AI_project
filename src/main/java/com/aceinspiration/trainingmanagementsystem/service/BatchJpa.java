package com.aceinspiration.trainingmanagementsystem.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aceinspiration.trainingmanagementsystem.formmodel.BatchForm;
import com.aceinspiration.trainingmanagementsystem.model.Batch;
import com.aceinspiration.trainingmanagementsystem.model.Course;

import com.aceinspiration.trainingmanagementsystem.model.Teacher;
import com.aceinspiration.trainingmanagementsystem.repository.BatchRepository;

@Service
public class BatchJpa {

	@Autowired
	private BatchRepository batchrepo;

	
	@Autowired
	private CourseJpa courseService;

	@Autowired
	private TeacherJpa teacherService;
	@Autowired
	private EntityManager entity;

	public List<Batch> getAllBatch() {
		return batchrepo.findAll();
	}

	public Optional<Batch> getBatchById(Long id) {
		return batchrepo.findById(id);
	}

	public Page<Batch> findAll(int i, String key) {
		Pageable pageable = PageRequest.of(i - 1, 5);
		if (key == null) {
			return batchrepo.findLatestBatch(pageable);
		}
		return batchrepo.findAll(key, pageable);
	}

	public Batch Select(Long id) {
		BatchForm batchModel = new BatchForm();

		return entity.find(Batch.class, id);
	}

	public Batch saveBatch(Batch batch) {
		
		return batchrepo.save(batch);
	}

	public void Batch(String batchName, String planStartDate, String planEndDate, String actualStartDate,
			String actualEndDate, Double teacherFee, Double supervisorFee, Double judgeFee, Double otherExpense,
			Long course, Long teacher, String days, String startTime, String endTime) {

		Batch Batch = new Batch();

		Optional<Course> courseOption = courseService.getCourseById(course);

		Optional<Teacher> teacherOption = teacherService.getTeacherById(teacher);

		if (courseOption.isPresent() && teacherOption.isPresent()) {

			Batch.setBatchName(batchName);
			Batch.setPlanStartDate(planStartDate);
			Batch.setPlanEndDate(planEndDate);
			Batch.setActualStartDate(actualStartDate);
			Batch.setActualEndDate(actualEndDate);
			Batch.setTeacherFee(teacherFee);
			Batch.setSupervisorFee(supervisorFee);
			Batch.setJudgeFee(judgeFee);
			Batch.setOtherExpense(otherExpense);
			Batch.setCourse(courseOption.get());
			Batch.setTeacher(teacherOption.get());
			Batch.setDays(days);
			Batch.setStartTime(startTime);
			Batch.setEndTime(endTime);
			batchrepo.save(Batch);

		}

	}
	public List<Batch> getBatchByName(String batchName){
		return batchrepo.findByBatchName(batchName);
	}
	
	public List<Batch> findByCourseId(long courseId){
		return batchrepo.findByCourseId(courseId);
	}

}
