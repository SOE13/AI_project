package com.aceinspiration.trainingmanagementsystem.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class ComingCourse {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String img;
	
	@NotNull
	@NotBlank(message = "Name can not be null!")
	private String name;
	
	

	public ComingCourse() {
		super();
	}

	public ComingCourse(String img) {
		super();
		this.img = img;
	}

	public ComingCourse(Long id, String img, String name) {
		super();
		this.id = id;
		this.img = img;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImg() {
		return "/images/Information/CommingCourse/"+img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	
	

	
}
