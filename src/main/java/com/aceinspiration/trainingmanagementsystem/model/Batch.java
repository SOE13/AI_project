package com.aceinspiration.trainingmanagementsystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Batch {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "batch_name")
	private String batchName;

	@Column(name = "plan_start_date")
	private String planStartDate;

	@Column(name = "plan_end_date")
	private String planEndDate;

	@Column(name = "actual_start_date")
	private String actualStartDate;

	@Column(name = "actual_end_date")
	private String actualEndDate;

	@Column(name = "teacher_fee")
	private Double teacherFee;

	@Column(name = "supervisor_fee")
	private Double supervisorFee;

	@Column(name = "judge_fee")
	private Double judgeFee;

	@Column(name = "other_expense")
	private Double otherExpense;

	@ManyToOne()
	@JoinColumn(name = "course_id")
	private Course course;

	@ManyToOne()
	@JoinColumn(name = "teacher_id")
	private Teacher teacher;
	private String days;
	private String startTime;
	private String endTime;
	
	private int ratebyStudent;
	
	

	public Batch() {

	}

	public Batch(String batchName, String planStartDate, String planEndDate, String actualStartDate,
			String actualEndDate, Double teacherFee, Double supervisorFee, Double judgeFee, Double otherExpense,
			Course course, Teacher teacher, String days, String startTime, String endTime) {
		super();
		this.batchName = batchName;
		this.planStartDate = planStartDate;
		this.planEndDate = planEndDate;
		this.actualStartDate = actualStartDate;
		this.actualEndDate = actualEndDate;
		this.teacherFee = teacherFee;
		this.supervisorFee = supervisorFee;
		this.judgeFee = judgeFee;
		this.otherExpense = otherExpense;
		this.course = course;
		this.teacher = teacher;
		this.days = days;
		this.startTime = startTime;
		this.endTime = endTime;

	}

	public String getDays() {
		return days;
	}

	public String setDays(String days) {
		return this.days = days;
	}

	public String getStartTime() {
		return startTime;
	}

	public String setStartTime(String startTime) {
		return this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public String setEndTime(String endTime) {
		return this.endTime = endTime;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBatchName() {
		return batchName;
	}

	public String setBatchName(String batchName) {
		return this.batchName = batchName;
	}

	public String getPlanStartDate() {
		return planStartDate;
	}

	public String setPlanStartDate(String planStartDate) {
		return this.planStartDate = planStartDate;
	}

	public String getPlanEndDate() {
		return planEndDate;
	}

	public String setPlanEndDate(String planEndDate) {
		return this.planEndDate = planEndDate;
	}

	public String getActualStartDate() {
		return actualStartDate;
	}

	public String setActualStartDate(String actualStartDate) {
		return this.actualStartDate = actualStartDate;
	}

	public String getActualEndDate() {
		return actualEndDate;
	}

	public String setActualEndDate(String actualEndDate) {
		return this.actualEndDate = actualEndDate;
	}

	public Double getTeacherFee() {
		return teacherFee;
	}

	public Double setTeacherFee(Double teacherFee) {
		return this.teacherFee = teacherFee;
	}

	public Double getSupervisorFee() {
		return supervisorFee;
	}

	public Double setSupervisorFee(Double supervisorFee) {
		return this.supervisorFee = supervisorFee;
	}

	public Double getJudgeFee() {
		return judgeFee;
	}

	public Double setJudgeFee(Double judgeFee) {
		return this.judgeFee = judgeFee;
	}

	public Double getOtherExpense() {
		return otherExpense;
	}

	public Double setOtherExpense(Double otherExpense) {
		return this.otherExpense = otherExpense;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public int getRatebyStudent() {
		return ratebyStudent;
	}

	public void setRatebyStudent(int ratebyStudent) {
		this.ratebyStudent = ratebyStudent;
	}

	@Override
	public String toString() {
		return "Batch [id=" + id + ", batchName=" + batchName + ", planStartDate=" + planStartDate + ", planEndDate="
				+ planEndDate + ", actualStartDate=" + actualStartDate + ", actualEndDate=" + actualEndDate
				+ ", teacherFee=" + teacherFee + ", supervisorFee=" + supervisorFee + ", judgeFee=" + judgeFee
				+ ", otherExpense=" + otherExpense + ", course=" + course + ", teacher=" + teacher + ", days=" + days
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", ratebyStudent=" + ratebyStudent + "]";
	}

	
	
}
