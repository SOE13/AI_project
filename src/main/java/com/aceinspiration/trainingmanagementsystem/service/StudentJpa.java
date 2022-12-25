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

import com.aceinspiration.trainingmanagementsystem.formmodel.PaymentReportForm;
import com.aceinspiration.trainingmanagementsystem.formmodel.StudentRelatedForm;
import com.aceinspiration.trainingmanagementsystem.model.Student;
import com.aceinspiration.trainingmanagementsystem.repository.StudentRepository;

@Service
@Transactional
public class StudentJpa {

	@Autowired
	private StudentRepository entity;
	@PersistenceContext
	EntityManager entityManager;

	public Student selectForProfile(String name) {
		TypedQuery<Student> query = entityManager.createQuery("from Student s where s.stid=?1", Student.class);
		query.setParameter(1, name);
		return query.getSingleResult();
	}

	public List<Student> selectAll() {
		return entity.findAll();
	}

	public Page<StudentRelatedForm> select(int pageNumber, String p1, String p2) {
		Pageable pageable = PageRequest.of(pageNumber - 1, 10);

		//if (p1 != null || p2 !=null) {
			return entity.findAll(p1,p2, pageable);
		//}
		//return null;

		//return entity.findAll(pageable);
	}

	public void delete(Long id) {
		entity.deleteById(id);
	}

	public Student selectOne(Long id) {
		Optional<Student> optional = entity.findById(id);
		return optional.get();
	}

	public void update(Student student) {
		entity.save(student);
	}

	public void save(Student student) {
		entity.save(student);
	}

	public Optional<Student> selectOneOp(long id) {
		return entity.findById(id);
	}

	public List<Student> getStudentByStudentCode(String studentCode) {
		return entity.findByStidOrNameOrPhone(studentCode);
	}
	
	
	public Student findByStidOrName(String code) {
		return entity.findByStidOrName(code);
	}

	public List<StudentRelatedForm> findByID(long id) {
		return entity.findByID(id);
	}
}
