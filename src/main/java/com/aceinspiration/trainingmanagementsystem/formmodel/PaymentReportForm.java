package com.aceinspiration.trainingmanagementsystem.formmodel;

public class PaymentReportForm {

	private long no;
	private String studentId;
	private String studentName;
	private String batchName;
	private String startend;
	private String duration;
	private Double trainingFee;

	private Double discount;
	private Double afterDis;

	private String firstDate;
	private String firstVrNo;
	private Double firstAmount;
	private String secondDate;
	private String secondVrNo;
	private Double secondAmount;
	private String thirdDate;
	private String thirdVrNo;
	private Double thirdAmount;
	private String fourthDate;
	private String fourthVrNo;
	private Double fourthAmount;
	private String fifthDate;
	private String fifthVrNo;
	private Double fifthAmount;
	private int total;

	private double remainFee;
	private String remark;
	
	
	
	

	public long getNo() {
		return no;
	}

	public void setNo(long no) {
		this.no = no;
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

	public Double getTrainingFee() {
		return trainingFee;
	}

	public void setTrainingFee(Double trainingFee) {
		this.trainingFee = trainingFee;
	}

	public String getFirstDate() {
		return firstDate;
	}

	public void setFirstDate(String firstDate) {
		this.firstDate = firstDate;
	}

	public String getFirstVrNo() {
		return firstVrNo;
	}

	public void setFirstVrNo(String firstVrNo) {
		this.firstVrNo = firstVrNo;
	}

	public Double getFirstAmount() {
		return firstAmount;
	}

	public void setFirstAmount(Double firstAmount) {
		this.firstAmount = firstAmount;
	}

	public String getSecondDate() {
		return secondDate;
	}

	public void setSecondDate(String secondDate) {
		this.secondDate = secondDate;
	}

	public String getSecondVrNo() {
		return secondVrNo;
	}

	public void setSecondVrNo(String secondVrNo) {
		this.secondVrNo = secondVrNo;
	}

	public Double getSecondAmount() {
		return secondAmount;
	}

	public void setSecondAmount(Double secondAmount) {
		this.secondAmount = secondAmount;
	}

	public String getThirdDate() {
		return thirdDate;
	}

	public void setThirdDate(String thirdDate) {
		this.thirdDate = thirdDate;
	}

	public String getThirdVrNo() {
		return thirdVrNo;
	}

	public void setThirdVrNo(String thirdVrNo) {
		this.thirdVrNo = thirdVrNo;
	}

	public Double getThirdAmount() {
		return thirdAmount;
	}

	public void setThirdAmount(Double thirdAmount) {
		this.thirdAmount = thirdAmount;
	}

	public String getFourthDate() {
		return fourthDate;
	}

	public void setFourthDate(String fourthDate) {
		this.fourthDate = fourthDate;
	}

	public String getFourthVrNo() {
		return fourthVrNo;
	}

	public void setFourthVrNo(String fourthVrNo) {
		this.fourthVrNo = fourthVrNo;
	}

	public Double getFourthAmount() {
		return fourthAmount;
	}

	public void setFourthAmount(Double fourthAmount) {
		this.fourthAmount = fourthAmount;
	}

	public String getFifthDate() {
		return fifthDate;
	}

	public void setFifthDate(String fifthDate) {
		this.fifthDate = fifthDate;
	}

	public String getFifthVrNo() {
		return fifthVrNo;
	}

	public void setFifthVrNo(String fifthVrNo) {
		this.fifthVrNo = fifthVrNo;
	}

	public Double getFifthAmount() {
		return fifthAmount;
	}

	public void setFifthAmount(Double fifthAmount) {
		this.fifthAmount = fifthAmount;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public double getRemainFee() {
		return remainFee;
	}

	public void setRemainFee(double remainFee) {
		this.remainFee = remainFee;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public PaymentReportForm() {
		super();
	}

	public PaymentReportForm(String batchName, String startend) {
		super();
		this.batchName = batchName;
		this.startend = startend;
	}
	

	public PaymentReportForm(long no,String studentId, String studentName, String batchName, String startend, String duration,
			Double trainingFee, Double discount, Double afterDis, String firstDate, String firstVrNo,
			Double firstAmount, String secondDate, String secondVrNo, Double secondAmount, String thirdDate,
			String thirdVrNo, Double thirdAmount, String fourthDate, String fourthVrNo, Double fourthAmount,
			String fifthDate, String fifthVrNo, Double fifthAmount, int total, double remainFee, String remark) {
		super();
		this.no=no;
		this.studentId = studentId;
		this.studentName = studentName;
		this.batchName = batchName;
		this.startend = startend;
		this.duration = duration;
		this.trainingFee = trainingFee;
		this.total = total;
		this.discount = discount;
		this.afterDis = afterDis;
		this.firstDate = firstDate;
		this.firstVrNo = firstVrNo;
		this.firstAmount = firstAmount;
		this.secondDate = secondDate;
		this.secondVrNo = secondVrNo;
		this.secondAmount = secondAmount;
		this.thirdDate = thirdDate;
		this.thirdVrNo = thirdVrNo;
		this.thirdAmount = thirdAmount;
		this.fourthDate = fourthDate;
		this.fourthVrNo = fourthVrNo;
		this.fourthAmount = fourthAmount;
		this.fifthDate = fifthDate;
		this.fifthVrNo = fifthVrNo;
		this.fifthAmount = fifthAmount;
		this.remainFee = remainFee;
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "PaymentReportForm [no=" + no + ", studentId=" + studentId + ", studentName=" + studentName
				+ ", batchName=" + batchName + ", startend=" + startend + ", duration=" + duration + ", trainingFee="
				+ trainingFee + ", discount=" + discount + ", afterDis=" + afterDis + ", firstDate=" + firstDate
				+ ", firstVrNo=" + firstVrNo + ", firstAmount=" + firstAmount + ", secondDate=" + secondDate
				+ ", secondVrNo=" + secondVrNo + ", secondAmount=" + secondAmount + ", thirdDate=" + thirdDate
				+ ", thirdVrNo=" + thirdVrNo + ", thirdAmount=" + thirdAmount + ", fourthDate=" + fourthDate
				+ ", fourthVrNo=" + fourthVrNo + ", fourthAmount=" + fourthAmount + ", fifthDate=" + fifthDate
				+ ", fifthVrNo=" + fifthVrNo + ", fifthAmount=" + fifthAmount + ", total=" + total + ", remainFee="
				+ remainFee + ", remark=" + remark + "]";
	}
	
	
	

}
