package com.aceinspiration.trainingmanagementsystem.formmodel;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class StudentMarkingForm {
	private Long studentBatchId;
	private String studentId;
	private String studentName;
	private String batchName;
	private String grade;
	private Long attendanceMark;
	@Min(1 )
	@Max(100)
	private Integer assignmentMark;	
	@Max(100)
	private Integer midTermMark;
	@Max(100)
	private Integer finalMark;
	@Max(100)
	private Integer projectMark;
	public Long getStudentBatchId() {
		return studentBatchId;
	}
	public void setStudentBatchId(Long studentBatchId) {
		this.studentBatchId = studentBatchId;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getBatchName() {
		return batchName;
	}
	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}
	
	
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public Long getAttendanceMark() {
		return attendanceMark;
	}
	public void setAttendanceMark(Long attendanceMark) {
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
	
	
	
	
	public StudentMarkingForm(Long studentBatchId, String studentId, String studentName, String batchName,String grade,
			Long attendanceMark, @Min(1) @Max(100) Integer assignmentMark, @Max(100) Integer midTermMark,
			@Max(100) Integer finalMark, @Max(100) Integer projectMark) {
		super();
		this.studentBatchId = studentBatchId;
		this.studentId = studentId;
		this.studentName = studentName;
		this.batchName = batchName;
		this.grade=grade;
		this.attendanceMark = attendanceMark;
		this.assignmentMark = assignmentMark;
		this.midTermMark = midTermMark;
		this.finalMark = finalMark;
		this.projectMark = projectMark;
	}
	@Override
	public String toString() {
		return "StudentMarkingForm [studentBatchId=" + studentBatchId + ", studentId=" + studentId + ", studentName="
				+ studentName + ", batchName=" + batchName + ", attendanceMark=" + attendanceMark + ", assignmentMark="
				+ assignmentMark + ", midTermMark=" + midTermMark + ", finalMark=" + finalMark + ", projectMark="
				+ projectMark + "]";
	}
	
	
	
	
}
