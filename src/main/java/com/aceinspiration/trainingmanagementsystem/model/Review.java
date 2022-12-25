package com.aceinspiration.trainingmanagementsystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String img;
	
	@NotNull
	@NotBlank(message = "Name can not be null!")
	private String name;
	
	@NotNull
	@NotBlank(message = "Review Detail can not be null!")
	@Column(columnDefinition = "TEXT")
	private String detail;
	 
	

	public Review() {
		super();
	}

	public Review(String img) {
		super();
		this.img = img;
	}

	public Review(Long id, String img, String name,String detail) {
		super();
		this.id = id;
		this.img = img;
		this.name = name;
		this.detail=detail;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImg() {
		return "/images/Information/Review/"+img;
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

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
}
