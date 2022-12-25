package com.aceinspiration.trainingmanagementsystem.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.aceinspiration.trainingmanagementsystem.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher,Long>{
	@Query("SELECT t FROM Teacher t where t.d=null")
	List<Teacher> findAll();
	@Query("SELECT t FROM Teacher t where t.d=null")
	Page<Teacher> findAll(Pageable pageable);
	@Query("SELECT t FROM Teacher t where t.d='deleted' order by t.id desc")
	List<Teacher> findDeleted();
}
