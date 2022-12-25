package com.aceinspiration.trainingmanagementsystem.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Gallery {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String img;
	
	public Gallery() {
		super();
	}
	public Gallery(String img) {
		super();
		this.img = img;
	}
	public Gallery(Long id, String img) {
		super();
		this.id = id;
		this.img = img;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getImg() {
		return "/images/Information/Gallery/"+img;
	}
	public void setImg(String img) {
		this.img = img;
	}

}
