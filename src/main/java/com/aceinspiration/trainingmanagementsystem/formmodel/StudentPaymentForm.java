package com.aceinspiration.trainingmanagementsystem.formmodel;



import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import javax.validation.constraints.NotNull;

import com.aceinspiration.trainingmanagementsystem.model.StudentBatch;

public class StudentPaymentForm {
	
private StudentBatch studentbatch;
	
    @NotBlank(message = "Paid Date must not be Blank!")
	private String studentBatchId;
	
	@NotBlank(message = "Paid Date must not be Blank!")
	private String paidDate;
	
	@NotBlank
	private String searchCode;
	
	

	@NotBlank(message = "Installment  must not be Blank!")
	private String installment;
	
	@NotNull(message="Actual Amount must not be Blank!")
	private Double actualAmount;
	
	@NotNull(message = "Please enter Paid Amount")
	@Min(value = (long) 1000, message = "Payment should enroll to minimum 1000 MMK")
	private Double paidAmount;
	
	
	private Double remainAmount;
	
	@NotBlank(message = "Please enter Voucher No")
	private String voucherNo;


	public StudentBatch getStudentbatch() {
		return studentbatch;
	}


	public void setStudentbatch(StudentBatch studentbatch) {
		this.studentbatch = studentbatch;
	}


	public String getPaidDate() {
		return paidDate;
	}


	public void setPaidDate(String paidDate) {
		this.paidDate = paidDate;
	}


	public String getInstallment() {
		return installment;
	}


	public void setInstallment(String installment) {
		this.installment = installment;
	}


	public Double getActualAmount() {
		return actualAmount;
	}


	public void setActualAmount(Double actualAmount) {
		this.actualAmount = actualAmount;
	}


	public Double getPaidAmount() {
		return paidAmount;
	}


	public void setPaidAmount(Double paidAmount) {
		this.paidAmount = paidAmount;
	}


	public Double getRemainAmount() {
		return remainAmount;
	}


	public void setRemainAmount(Double remainAmount) {
		this.remainAmount = remainAmount;
	}


	public String getVoucherNo() {
		return voucherNo;
	}


	public void setVoucherNo(String voucherNo) {
		this.voucherNo = voucherNo;
	}


	public String getStudentBatchId() {
		return studentBatchId;
	}


	public void setStudentBatchId(String studentBatchId) {
		this.studentBatchId = studentBatchId;
	}
	
	
	public String getSearchCode() {
		return searchCode;
	}


	public void setSearchCode(String searchCode) {
		this.searchCode = searchCode;
	}
	

}














