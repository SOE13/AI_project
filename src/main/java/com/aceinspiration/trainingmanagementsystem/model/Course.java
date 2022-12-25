package com.aceinspiration.trainingmanagementsystem.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "Name can not be blank!")
	private String name;
	@NotBlank(message = "Description Name can not be blank!")
	private String desName;
	@NotNull(message = "Fee cannot be blank!")
	private Double fee;
	@ManyToOne
	@JoinColumn(name = "courseType_id")
	private CourseType type;
	@NotBlank(message = "Duration can not be blank!")
	private String duration;
	@NotBlank(message = "Course Detail can not be blank!")
	private String detail;
	private String img;
	
	/* @OneToOne(mappedBy = "course")
	private CourseMarking courseMark; */
	
	private String d;
	
	public Course(Long id) {
		super();
		this.id = id;
	}
	public Course(@NotBlank(message = "Name can not be blank!") String name,
			@NotBlank(message = "Description Name can not be blank!") String desName,
			@NotNull(message = "Fee cannot be blank!") Double fee, CourseType type,
			@NotBlank(message = "Duration can not be blank!") String duration,
			@NotBlank(message = "Course Detail can not be blank!") String detail, String img) {
		super();
		this.name = name;
		this.desName = desName;
		this.fee = fee;
		this.type = type;
		this.duration = duration;
		this.detail = detail;
		this.img = img;
	}
	public Course() {
		super();
	}
	
	
	public String getD() {
		return d;
	}
	public void setD(String d) {
		this.d = d;
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
	public Double getFee() {
		return fee;
	}
	public void setFee(Double fee) {
		this.fee = fee;
	}
	public CourseType getType() {
		return type;
	}
	public void setType(CourseType type) {
		this.type = type;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getImg() {
		return "/images/CourseImg/"+img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getDesName() {
		return desName;
	}
	public void setDesName(String desName) {
		this.desName = desName;
	}
	
	/*
	 * public CourseMarking getCourseMark() { return courseMark; } public void
	 * setCourseMark(CourseMarking courseMark) { this.courseMark = courseMark; }
	 */
	public String getCourseId() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
