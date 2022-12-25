package com.aceinspiration.trainingmanagementsystem.formmodel;

public class PaymentReportForm2 {

	private String studentId;
	private String studentName;
	private String batchName;
	private String startend;
	private String duration;	
	private Double totalFee;
	private Double discount;
	private Double afterDis;
	private String times;
	private String paidDate;
	private String voucherNo;
	private Double paidFee;
	private Double remainFee;
	private String remark;

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

	public Double getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Double totalFee) {
		this.totalFee = totalFee;
	}

	public Double getPaidFee() {
		return paidFee;
	}

	public void setPaidFee(Double paidFee) {
		this.paidFee = paidFee;
	}

	public Double getRemainFee() {
		return remainFee;
	}

	public void setRemainFee(Double remainFee) {
		this.remainFee = remainFee;
	}
	
	

	public String getStartend() {
		return startend;
	}

	public void setStartend(String startend) {
		this.startend = startend;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Double getAfterDis() {
		return afterDis;
	}

	public void setAfterDis(Double afterDis) {
		this.afterDis = afterDis;
	}

	public String getPaidDate() {
		return paidDate;
	}

	public void setPaidDate(String paidDate) {
		this.paidDate = paidDate;
	}

	public String getVoucherNo() {
		return voucherNo;
	}

	public void setVoucherNo(String voucherNo) {
		this.voucherNo = voucherNo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	
	
	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}

	public PaymentReportForm2(String studentId, String studentName, String batchName,String startend, String duration,Double totalFee,Double discount, Double afterDis,String times, String paidDate, String voucherNo,Double paidFee,
			Double remainFee,String remark) {
		super();
		this.studentId = studentId;
		this.studentName = studentName;
		this.batchName = batchName;
		this.startend=startend;
		this.duration=duration;
		this.totalFee = totalFee;
		this.discount=discount;
		this.afterDis=afterDis;
		this.times=times;
		this.paidDate=paidDate;
		this.voucherNo=voucherNo;
		this.paidFee = paidFee;
		this.remainFee = remainFee;
		this.remark=remark;
		
	}

	public PaymentReportForm2() {
		super();
	}

	public PaymentReportForm2(String batchName, String startend) {
		super();
		this.batchName = batchName;
		this.startend = startend;
	}
	
	
	
	

}
