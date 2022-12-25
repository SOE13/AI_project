package com.aceinspiration.trainingmanagementsystem.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.aceinspiration.trainingmanagementsystem.model.Batch;
import com.aceinspiration.trainingmanagementsystem.model.StudentBatch;

@Repository
public interface BatchRepository extends JpaRepository<Batch,Long > {
	@Query("SELECT b FROM Batch b where b.batchName=?1")
	Page<Batch> findAll(String key,Pageable pageable);
	
	@Query("select b from Batch b where actualEndDate >= current_date()")
	Page<Batch> findLatestBatch(Pageable pageable);
	
	public List<Batch> findByBatchName(String batchName);
	
	@Query("select b from Batch b where b.course.id=?1")
	public List<Batch> findByCourseId(long courseId);
	

}
