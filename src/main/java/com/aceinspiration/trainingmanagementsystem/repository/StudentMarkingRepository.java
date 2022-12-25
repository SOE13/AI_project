package com.aceinspiration.trainingmanagementsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.aceinspiration.trainingmanagementsystem.formmodel.StudentMarkingForm;
import com.aceinspiration.trainingmanagementsystem.model.Student;
import com.aceinspiration.trainingmanagementsystem.model.StudentBatch;
import com.aceinspiration.trainingmanagementsystem.model.StudentMarking;

public interface StudentMarkingRepository extends JpaRepository<StudentMarking, Long> {
	
	public StudentMarking findByStudentBatchId(long studentBatchId);
	List<StudentMarking> findByStudentBatch(StudentBatch studentBatch);
	
	@Query("select new com.aceinspiration.trainingmanagementsystem.formmodel.StudentMarkingForm(sb.id,s.stid,s.name,b.batchName,sm.grade, count(att.status), cm.assignmentMark, cm.midTermMark, cm.finalMark, cm.projectMark)\r\n"
			+ "FROM Batch b\r\n" + 
			"JOIN CourseMarking cm\r\n" + 
			"ON b.course=cm.course\r\n" + 
			"JOIN StudentBatch sb\r\n" + 
			"ON b.id = sb.batch\r\n" + 
			"JOIN Student s\r\n" + 
			"ON sb.student = s.id\r\n" + 
			"JOIN Attendace att\r\n" + 
			"ON sb.id = att.studentBatch\r\n" + 
			"JOIN StudentMarking sm\r\n" + 
			"ON sb.id = sm.studentBatch\r\n" + 
			"where b.batchName = ?1\r\n" + 
			"and att.status='Present'\r\n" + 
			"group by sb.student")
	List<StudentMarkingForm> findMarkingByBatchName(String batchName);
	
	
}
