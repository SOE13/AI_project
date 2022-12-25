package com.aceinspiration.trainingmanagementsystem.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Entity
public class Timetable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@NotBlank(message = "Name must not be blank!")
	private String name;
	@NotNull
	@NotBlank(message = "Duration must not be blank!")
	private String duration;
	@NotNull(message = "StartDate can not be null!")
	private Date startDate;
	@NotNull(message = "EndDate can not be null!")
	private Date endDate;
	public Timetable() {
		super();
	}
	public String getDuration() {
		return duration;
	}
	public Timetable(String name, String duration, Date startDate, Date endDate) {
		super();
		this.name = name;
		this.duration = duration;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	public Timetable(Long id, String name, String duration, Date startDate, Date endDate) {
		super();
		this.id = id;
		this.name = name;
		this.duration = duration;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	@Override
	public String toString() {
		return "Timetable [id=" + id + ", name=" + name + ", duration=" + duration + ", startDate=" + startDate
				+ ", endDate=" + endDate + "]";
	}
}
