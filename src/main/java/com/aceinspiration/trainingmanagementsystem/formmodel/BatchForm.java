package com.aceinspiration.trainingmanagementsystem.formmodel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class BatchForm {
	
	
	private String courseId;
	
	@NotEmpty (message = "BatchNo cann't be null")
	private String batchNo;
	
	 private String courseName;
	@NotBlank
	private String teacherId;
	private Double courseFee;

	private String teacherName;
	public Double getCourseFee() {
		return courseFee;
	}


	public void setCourseFee(Double courseFee) {
		this.courseFee = courseFee;
	}


	@NotEmpty (message = "planStartDate cann't be null")
	private String planStartDate;
	
	@NotEmpty (message = "planEndDate cann't be null")
	private String planEndDate;
	
	

	private String actualStartDate;
	
	
	private String actualEndDate;
	
	
	
	private Double teacherFee;
	
	
	private Double supervisorFee;
	
	
	private Double judgeFee;
	
	
	private Double otherExpense;
	@NotBlank

private String days;
@NotBlank
private String startTime;
@NotBlank

private String endTime;

	public String getDays() {
	return days;
}


public void setDays(String days) {
	this.days = days;
}


public String getStartTime() {
	return startTime;
}


public void setStartTime(String startTime) {
	this.startTime = startTime;
}


public String getEndTime() {
	return endTime;
}


public void setEndTime(String endTime) {
	this.endTime = endTime;
}


	public String getCourseName() {
		return courseName;
	}


	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}


	public String getCourseNo() {
		return batchNo;
	}


	public void setCourseNo(String courseNo) {
		this.batchNo = courseNo;
	}


	public String getTeacherId() {
		return teacherId;
	}


	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}


	public String getPlanStartDate() {
		return planStartDate;
	}


	public void setPlanStartDate(String planStartDate) {
		this.planStartDate = planStartDate;
	}


	public String getPlanEndDate() {
		return planEndDate;
	}


	public void setPlanEndDate(String planEndDate) {
		this.planEndDate = planEndDate;
	}


	public String getActualStartDate() {
		return actualStartDate;
	}


	public void setActualStartDate(String actualStartDate) {
		this.actualStartDate = actualStartDate;
	}


	public String getActualEndDate() {
		return actualEndDate;
	}


	public void setActualEndDate(String actualEndDate) {
		this.actualEndDate = actualEndDate;
	}





	public Double getTeacherFee() {
		return teacherFee;
	}


	public void setTeacherFee(Double teacherFee) {
		this.teacherFee = teacherFee;
	}


	public Double getSupervisorFee() {
		return supervisorFee;
	}


	public void setSupervisorFee(Double supervisorFee) {
		this.supervisorFee = supervisorFee;
	}


	public Double getJudgeFee() {
		return judgeFee;
	}


	public void setJudgeFee(Double judgeFee) {
		this.judgeFee = judgeFee;
	}


	public Double getOtherExpense() {
		return otherExpense;
	}


	public void setOtherExpense(Double otherExpense) {
		this.otherExpense = otherExpense;
	}

	
	public String getBatchNo() {
		return batchNo;
	}


	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}


	public String getCourseId() {
		return courseId;
	}


	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}


	public String getTeacherName() {
		return teacherName;
	}


	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	



}
