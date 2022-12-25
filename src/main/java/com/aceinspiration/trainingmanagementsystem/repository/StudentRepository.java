package com.aceinspiration.trainingmanagementsystem.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.aceinspiration.trainingmanagementsystem.formmodel.PaymentReportForm;
import com.aceinspiration.trainingmanagementsystem.formmodel.StudentRelatedForm;
import com.aceinspiration.trainingmanagementsystem.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

	/*
	 * @Query("SELECT s FROM Student s WHERE CONCAT(s.stid,s.name,s.phone,s.NRC,s.email) LIKE %?1%"
	 * ) public Page<Student> findAll(String p1,Pageable pageable);
	 */
	
	
	
	
	@Query("SELECT new com.aceinspiration.trainingmanagementsystem.formmodel.StudentRelatedForm(sb.id,b.id,b.batchName,s.id, s.stid,s.name,s.phone,s.email,sb.status) "
			+ "from StudentBatch sb join sb.student s \r\n" + 
			
			"on s.id = sb.student\r\n" + 
			"join sb.batch b\r\n" + 
			"on b.id = sb.batch\r\n" + 
			"WHERE s.name = ?1 \r\n" + 
			"OR s.stid = ?1\r\n" + 
			"OR b.batchName = ?2")
	public Page<StudentRelatedForm> findAll(String p1, String p2, Pageable pageable);
	
	
	@Query("select s from Student s where s.name = ?1 or s.stid= ?1 or s.phone = ?1")
	public List<Student> findByStidOrNameOrPhone(String code);
	 
	 
	@Query("select s from Student s where s.name = ?1 or s.stid= ?1")
	public Student findByStidOrName(String code);
	
	
	/*
	 * @Query("select new com.aceinspiration.trainingmanagementsystem.formmodel.PaymentReportForm(b.batchName, concat(b.actualStartDate,' - ' ,b.actualEndDate))\r\n"
	 * + "from StudentBatch sb\r\n" + "join sb.batch b\r\n" +
	 * "on b.id = sb.batch\r\n" + "join sb.student s\r\n" +
	 * "on s.id = sb.student\r\n" + "where s.id=?1") public List<PaymentReportForm>
	 * findByID(long id);
	 */
	 
	 
	@Query("select new com.aceinspiration.trainingmanagementsystem.formmodel.StudentRelatedForm(b.batchName,s.stid, sm.grade, b.actualStartDate, b.actualEndDate) from StudentBatch sb join sb.student s on s.id = sb.student join sb.batch b on b.id = sb.batch\r\n"
			+ "join StudentMarking sm on sm.studentBatch=s.id where s.id=?1")
	public List<StudentRelatedForm> findByID(long id);
	 
	 

}
