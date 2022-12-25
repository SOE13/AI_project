package com.aceinspiration.trainingmanagementsystem.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
public class StudentMarking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	private StudentBatch studentBatch;
	
	private Integer attendanceMark;
	@Min(1 )
	@Max(100)
	private Integer assignmentMark;
	
	@Max(100)
	private Integer midTermMark;
	@Max(100)
	private Integer finalMark;
	@Max(100)
	private Integer projectMark;
	
	private String grade;
	
	public StudentMarking() {
		super();
	}
	
	

	public StudentMarking(StudentBatch studentBatch) {
		super();
		this.studentBatch = studentBatch;
	}



	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public StudentBatch getStudentBatch() {
		return studentBatch;
	}
	public void setStudentBatch(StudentBatch studentBatch) {
		this.studentBatch = studentBatch;
	}
	public Integer getAttendanceMark() {
		return attendanceMark;
	}
	public void setAttendanceMark(Integer attendanceMark) {
		this.attendanceMark = attendanceMark;
	}
	public Integer getAssignmentMark() {
		return assignmentMark;
	}
	public void setAssignmentMark(Integer assignmentMark) {
		this.assignmentMark = assignmentMark;
	}
	public Integer getMidTermMark() {
		return midTermMark;
	}
	public void setMidTermMark(Integer midTermMark) {
		this.midTermMark = midTermMark;
	}
	public Integer getFinalMark() {
		return finalMark;
	}
	public void setFinalMark(Integer finalMark) {
		this.finalMark = finalMark;
	}
	public Integer getProjectMark() {
		return projectMark;
	}
	public void setProjectMark(Integer projectMark) {
		this.projectMark = projectMark;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}



	@Override
	public String toString() {
		return "StudentMarking [id=" + id + ", studentBatch=" + studentBatch + ", attendanceMark=" + attendanceMark
				+ ", assignmentMark=" + assignmentMark + ", midTermMark=" + midTermMark + ", finalMark=" + finalMark
				+ ", projectMark=" + projectMark + ", grade=" + grade + "]";
	}
	
	
}
