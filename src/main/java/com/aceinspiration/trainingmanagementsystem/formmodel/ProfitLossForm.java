package com.aceinspiration.trainingmanagementsystem.formmodel;

public class ProfitLossForm {

	private String batchName;
	private String teacherName;
	private long totalStudent;
	private Double totalIncome;
	private Double teacherFee;
	private Double supervisorFee;
	private Double judgeFee;
	private Double otherExpense;
	private Double result;

	public ProfitLossForm() {
		super();
	}

	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public long getTotalStudent() {
		return totalStudent;
	}

	public void setTotalStudent(long totalStudent) {
		this.totalStudent = totalStudent;
	}

	public Double getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(Double totalIncome) {
		this.totalIncome = totalIncome;
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

	public Double getResult() {
		return result;
	}

	public void setResult(Double result) {
		this.result = result;
	}

	public ProfitLossForm(String batchName, String teacherName, long totalStudent,Double totalIncome, Double teacherFee,
			Double supervisorFee, Double judgeFee, Double otherExpense) {
		super();
		this.batchName = batchName;
		this.teacherName = teacherName;
		this.totalStudent=totalStudent;
		this.totalIncome = totalIncome;
		this.teacherFee = teacherFee;
		this.supervisorFee = supervisorFee;
		this.judgeFee = judgeFee;
		this.otherExpense = otherExpense;

		System.out.println("this.batch : " + this.batchName);
		System.out.println("batch : " + batchName);

		if (this.supervisorFee == null) {
			this.supervisorFee = 0.0;
		}

		if (this.judgeFee == null) {
			this.judgeFee = 0.0;
		}

		if (this.otherExpense == null) {
			this.otherExpense = 0.0;
		}

		if (this.teacherFee == null) {
			this.teacherFee = 0.0;
		}

		calculateRemainBalance(this.totalIncome, this.teacherFee, this.supervisorFee, this.judgeFee, this.otherExpense);
	}

	public Double calculateRemainBalance(Double totalIncome, Double teacherFee, Double supervisorFee, Double judgeFee,
			Double otherExpense) {
		System.out.println("this.batch : " + this.batchName);
		System.out.println("this.supervisor : " + this.supervisorFee);

		this.result = totalIncome - (teacherFee + supervisorFee + judgeFee + otherExpense);
		return this.result;
	}

}
