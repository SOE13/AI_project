package com.aceinspiration.trainingmanagementsystem.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class CourseType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@NotBlank(message = "Course Type can not be blank!")
	private String name; 
	
	
	public CourseType() {
		super();
	}
	

	public CourseType(@NotNull @NotBlank(message = "Name can not be null!") String name) {
		super();
		this.name = name;
	}

	public CourseType(Long id) {
		super();
		this.id = id;
	}


	public CourseType(Long id, @NotNull @NotBlank(message = "Name can not be null!") String name) {
		super();
		this.id = id;
		this.name = name;
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


	
	
}
