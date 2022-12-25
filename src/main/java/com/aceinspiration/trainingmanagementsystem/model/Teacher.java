package com.aceinspiration.trainingmanagementsystem.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
public class Teacher {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String teacherId;
	
	@NotEmpty(message = "Name must not be blank!")
	private String name;
	
	@NotBlank(message = "NRC must not be blank!")
	private String nrc;
	
	@NotBlank(message = "Email must not be blank!")
	private String email;
	
	@NotBlank(message = "Phone Number must not be blank!")
	private String phNo;

	@NotEmpty(message = "Address must not be blank!")
	private String address;
	
	private String education;
	private String other;
	
	private String img;
	private String cv;
	
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
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	public String getName() {
		return name;
	}
	public String getImg() {
		return "/images/Teacher/TeacherPhoto/"+img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getCv() {
		return "/images/Teacher/TeacherCv/"+cv;
	}
	public void setCv(String cv) {
		this.cv = cv;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNrc() {
		return nrc;
	}
	public void setNrc(String nrc) {
		this.nrc = nrc;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhNo() {
		return phNo;
	}
	public void setPhNo(String phNo) {
		this.phNo = phNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	
	
	

}
