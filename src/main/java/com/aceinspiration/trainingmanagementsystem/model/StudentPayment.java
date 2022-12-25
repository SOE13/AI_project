package com.aceinspiration.trainingmanagementsystem.model;



import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;

@Entity
@Table(name = "student_payment")
public class StudentPayment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name="student_batch_id")
	private StudentBatch studentBatch;
	
	@JoinColumn(name="paid_date")
	private String paidDate;
	
	@JoinColumn(name="installment")
	private String installment;
	
	@JoinColumn(name="actual_amount")
	private Double actualAmount;
	
	@JoinColumn(name="paid_amount")
	private Double paidAmount;
	
	@JoinColumn(name="remain_amount")
	private Double remainAmount;
	
	@JoinColumn(name="voucher_no")
	private String voucherNo;

	public StudentPayment() {
		
	}
	
	
	public StudentPayment(StudentBatch studentbatchId, String paidDate, String installment, Double actualAmount,
			Double paidAmount, Double remainAmount, String voucherNo) {
		super();
		this.studentBatch = studentbatchId;
		this.paidDate = paidDate;
		this.installment = installment;
		this.actualAmount = actualAmount;
		this.paidAmount = paidAmount;
		this.remainAmount = remainAmount;
		this.voucherNo = voucherNo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
	public StudentBatch getStudentBatch() {
		return studentBatch;
	}


	public void setStudentBatch(StudentBatch studentBatch) {
		this.studentBatch = studentBatch;
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

	public double getActualAmount() {
		return actualAmount;
	}

	public void setActualAmount(double actualAmount) {
		this.actualAmount = actualAmount;
	}

	public double getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(double paidAmount) {
		this.paidAmount = paidAmount;
	}

	public double getRemainAmount() {
		return remainAmount;
	}

	public void setRemainAmount(double remainAmount) {
		this.remainAmount = remainAmount;
	}

	public String getVoucherNo() {
		return voucherNo;
	}

	public void setVoucherNo(String voucherNo) {
		this.voucherNo = voucherNo;
	}
	
	
	
	
}






















