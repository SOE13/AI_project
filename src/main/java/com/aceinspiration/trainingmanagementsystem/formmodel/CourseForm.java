package com.aceinspiration.trainingmanagementsystem.formmodel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CourseForm {
	private Long id;
	@NotBlank(message = "Name can not be blank!")
	private String name;
	@NotBlank(message = "Description Name can not be blank!")
	private String desName;
	@NotNull(message = "Fee cannot be blank!")
	private Double fee;
	@NotNull(message = "Course Type must not be Blank!")
	private Long type;
	@NotBlank(message = "Duration can not be blank!")
	private String duration;
	@NotBlank(message = "Course Detail can not be blank!")
	private String detail;
	private String img;
	
	public CourseForm() {
		super();
	}
	public CourseForm(@NotBlank(message = "Name can not be blank!") String name,
			@NotBlank(message = "Description Name can not be blank!") String desName,
			@NotNull(message = "Fee cannot be blank!") Double fee, Long type,
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
	
	public CourseForm(Long id, @NotBlank(message = "Name can not be blank!") String name,
			@NotBlank(message = "Description Name can not be blank!") String desName,
			@NotNull(message = "Fee cannot be blank!") Double fee, Long type,
			@NotBlank(message = "Duration can not be blank!") String duration,
			@NotBlank(message = "Course Detail can not be blank!") String detail, String img) {
		super();
		this.id = id;
		this.name = name;
		this.desName = desName;
		this.fee = fee;
		this.type = type;
		this.duration = duration;
		this.detail = detail;
		this.img = img;
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
	public String getDesName() {
		return desName;
	}
	public void setDesName(String desName) {
		this.desName = desName;
	}
	public Double getFee() {
		return fee;
	}
	public void setFee(Double fee) {
		this.fee = fee;
	}
	public Long getType() {
		return type;
	}
	public void setType(Long type) {
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
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
}
