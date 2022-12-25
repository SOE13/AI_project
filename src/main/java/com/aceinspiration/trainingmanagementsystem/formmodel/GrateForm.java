package com.aceinspiration.trainingmanagementsystem.formmodel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class GrateForm {
	private Long id;
	
	@NotNull(message = "Course must not be Blank!")
	private Long course;
	
	@NotNull(message = "From mark must not be Blank!")
	private Integer fromMark;
	
	@NotNull(message = "To mark must not be Blank!")
	private Integer toMark;
	
	@NotBlank(message = "Grate mark must not be Blank!")
	private String sate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCourse() {
		return course;
	}
	public void setCourse(Long course) {
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
	public GrateForm(Long id, @NotNull(message = "Course Name must not be Blank! ") Long course,
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
	public GrateForm() {
		super();
	}
	public GrateForm(@NotNull(message = "Course Name must not be Blank! ") Long course,
			@NotNull(message = "From mark must not be Blank!") Integer fromMark,
			@NotNull(message = "To mark must not be Blank!") Integer toMark,
			
			@NotBlank(message = "Grate mark must not be Blank!") String sate) {
		super();
		this.course = course;
		this.fromMark=fromMark;
		this.toMark = toMark;
		
		this.sate = sate;
	}
	
}
