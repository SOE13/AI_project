package com.aceinspiration.trainingmanagementsystem.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Attendace {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private StudentBatch studentBatch;
	
	private String status;
	
	private Date date;

	
	
	public Attendace() {
		super();
	}

	public Attendace(StudentBatch studentBatch, String status, Date date) {
		super();
		this.studentBatch = studentBatch;
		this.status = status;
		this.date = date;
	}

	public Attendace(StudentBatch studentBatch, Date date) {
		super();
		this.studentBatch = studentBatch;
		this.date = date;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}
