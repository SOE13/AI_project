package com.aceinspiration.trainingmanagementsystem.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.aceinspiration.trainingmanagementsystem.model.Attendace;
import com.aceinspiration.trainingmanagementsystem.model.StudentBatch;

public interface AttendanceRepository extends JpaRepository<Attendace, Long>{

	@Query("Select a from Attendace a where a.studentBatch=?1 and a.status='Present'")
	List<Attendace> findByStudentBatchForValue(StudentBatch studentBatch);
	
	List<Attendace> findByStudentBatch(StudentBatch studentBatch);
	
	@Query("SELECT att FROM Attendace att where att.date between ?2 and ?3 and att.studentBatch = ?1")
	List<Attendace> findByStudentBatch(StudentBatch studentBatch, Date date1, Date date2);
	
	@Query("Select a from Attendace a where a.studentBatch=?1  GROUP BY a.date")
	List<Attendace> findByStudentBatchForMarking(StudentBatch studentBatch);
	
	List<Attendace> findByDate(Date date);
	
	@Query("SELECT a FROM Attendace a where a.studentBatch=?1 and a.date=?2")
	Attendace findStudentBatchDate(StudentBatch studentBatch, Date date);
}
