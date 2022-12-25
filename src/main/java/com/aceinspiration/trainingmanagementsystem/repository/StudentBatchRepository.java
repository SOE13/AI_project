package com.aceinspiration.trainingmanagementsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.aceinspiration.trainingmanagementsystem.formmodel.RatingForm;
import com.aceinspiration.trainingmanagementsystem.formmodel.StudentRelatedForm;
import com.aceinspiration.trainingmanagementsystem.model.Batch;
import com.aceinspiration.trainingmanagementsystem.model.Student;
import com.aceinspiration.trainingmanagementsystem.model.StudentBatch;

@Repository
public interface StudentBatchRepository extends JpaRepository<StudentBatch, Long> {

	public List<StudentBatch> findByStudent(Student student);

	
	public List<StudentBatch> findBybatch(Batch key);
	

	@Query("select new com.aceinspiration.trainingmanagementsystem.formmodel.StudentRelatedForm(b.id,b.batchName, s.stid, s.id, sb.rating) from StudentBatch sb join sb.batch b on sb.batch=b.id join sb.student s on s.id=sb.student where s.stid=?1")
	public List<StudentRelatedForm> findBatchNamebyStid(String stid);

	@Query("select new com.aceinspiration.trainingmanagementsystem.formmodel.StudentRelatedForm(b.id,b.batchName,s.stid,s.name) from StudentBatch sb join sb.batch b on sb.batch=b.id join sb.student s on s.id=sb.student order by s.stid desc")
	public List<StudentRelatedForm> findAllStudentOrderbyStid();
	
	@Query("select sb from StudentBatch sb where sb.batch.id = ?1 and sb.student.id=?2")
	public StudentBatch findByStudentIdAndBatchId(Long batchId, Long studentId);

	@Query("select new com.aceinspiration.trainingmanagementsystem.formmodel.RatingForm(b.id, b.batchName,sb.rating, sum(sb.rating), count(b.id), t.name) from StudentBatch sb join sb.batch b on sb.batch=b.id join Teacher t on t.id = b.teacher.id where sb.rating <> 0 group by sb.batch order by b.id desc")
	public List<RatingForm> getBatchCountByBatchId();
	
	@Query("select count(*) from StudentBatch sb where sb.batch.id=?1")
	public Long getCountBatch(Long batchId);
	
	@Query("select sb from StudentBatch sb where sb.student.id=?1 ORDER BY sb.id DESC")
	public List<StudentBatch> findByStudent(Long stId);

}