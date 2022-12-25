package com.aceinspiration.trainingmanagementsystem.formmodel;

import javax.validation.constraints.NotEmpty;

public class StudentBatchForm {

	@NotEmpty(message = "Student Name must not be blank!")
	private String studentName;

	private String studentId;
	
	private String code;
	
	private String batchName;
	
	@NotEmpty(message = "Batch Name must not be blank!")
	private String batchNo;

	private String couname;
	private String courseFee;

	private String discountType;

	private String discountPercent;

	private String discountAmount;

	private String actualAmount;

	private boolean status;

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	public String getCourseFee() {
		return courseFee;
	}

	public void setCourseFee(String courseFee) {
		this.courseFee = courseFee;
	}

	public String getDiscountType() {
		return discountType;
	}

	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}

	public String getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(String discountPercent) {
		this.discountPercent = discountPercent;
	}

	public String getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(String discountAmount) {
		this.discountAmount = discountAmount;
	}

	public String getActualAmount() {
		return actualAmount;
	}

	public void setActualAmount(String actualAmount) {
		this.actualAmount = actualAmount;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getCouname() {
		return couname;
	}

	public void setCouname(String couname) {
		this.couname = couname;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "StudentBatchForm [studentName=" + studentName + ", studentId=" + studentId + ", code=" + code
				+ ", batchName=" + batchName + ", batchNo=" + batchNo + ", couname=" + couname + ", courseFee="
				+ courseFee + ", discountType=" + discountType + ", discountPercent=" + discountPercent
				+ ", discountAmount=" + discountAmount + ", actualAmount=" + actualAmount + ", status=" + status + "]";
	}

	

	
}
