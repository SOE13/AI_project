package com.aceinspiration.trainingmanagementsystem.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.aceinspiration.trainingmanagementsystem.model.Batch;
import com.aceinspiration.trainingmanagementsystem.model.Register;
import com.aceinspiration.trainingmanagementsystem.model.StudentBatch;

public interface RegisterRepository extends JpaRepository<Register, Long>{

	@Query("SELECT r FROM Register r WHERE r.status=null order by r.id desc")
	Page<Register> findAll(Pageable pageable);

	@Query("SELECT r FROM Register r WHERE r.date BETWEEN ?1 and ?2 order by r.id desc")
	Page<Register> findAllforRepord(String k1, String k2, Pageable pageable);

	@Query("SELECT r FROM Register r WHERE r.knownfrom =?1 order by r.id desc")
	Page<Register> findAllforRepord(String k1, Pageable pageable);

	@Query("SELECT r FROM Register r order by r.id desc")
	Page<Register> findAllforRepord(Pageable pageable);
	
	@Query("SELECT r FROM Register r WHERE r.status =?1")
	public List<Register> findByStatus(String status);

}
