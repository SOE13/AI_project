package com.aceinspiration.trainingmanagementsystem.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Grate {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne 
	private Course course;
	
	@NotNull(message = "From mark must not be Blank!")
	private Integer fromMark;
	
	@NotNull(message = "To mark must not be Blank!")
	private Integer toMark;
	
	@NotBlank(message = "Grate mark must not be Blank!")
	private String sate;
	
	private String d;
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
	
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	
	public Integer getFromMark() {
		return fromMark;
	}
	public void setFromMark(Integer fromMark) {
		this.fromMark = fromMark;
	}
	public Integer getToMark() {
		return toMark;
	}
	public void setToMark(Integer toMark) {
		this.toMark = toMark;
	}
	public String getSate() {
		return sate;
	}
	public void setSate(String sate) {
		this.sate = sate;
	}
	
	public Grate() {
		super();
	}
	public Grate(Long id, Course course, 
			@NotNull(message = "From mark must not be Blank!") Integer fromMark,
			@NotNull(message = "To mark must not be Blank!") Integer toMark,
			@NotBlank(message = "Grate mark must not be Blank!") String sate) {
		super();
		this.id = id;
		this.course = course;
		this.fromMark=fromMark;
		this.toMark = toMark;
		this.sate = sate;
	}
	public Grate(Course course, @NotNull(message = "From mark must not be Blank!") Integer fromMark,
			@NotNull(message = "To mark must not be Blank!") Integer toMark,
			@NotBlank(message = "Grate mark must not be Blank!") String sate) {
		super();
		this.course = course;
		this.fromMark=fromMark;
		this.toMark = toMark;
		this.sate = sate;
	}
}


