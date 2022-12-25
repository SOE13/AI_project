package com.aceinspiration.trainingmanagementsystem.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String stid;

	@NotEmpty(message = "Name must not be blank!")
	private String name;
	@NotEmpty(message = "Father name must not be blank!")
	private String father;

	private String NRC;
	@NotBlank(message = "Date of Birth must not be Blank!")
	private String DOB;
	@NotBlank(message = "Pnone No must not be Blank!")
	private String phone;
	
	@NotBlank(message = "Please select Gender!")
	private String gender;
	private String email;

	@NotBlank(message = "Password  can not be blank!")
	private String password;
	
	
	
	@Transient
	private String cpassword;
	
	@NotBlank(message = "Register Date must not be Blank!")
	private String rdate;
	private String address;
	private String knownfrom;
	private String edu;
	private String other;
	private String img;
	
	private String QR;
	

	

	public String getQR() {
		return "/images/Student/QRCode/"+ QR;
	}

	public void setQR(String qR) {
		QR = qR;
	}

	public String getStid() {
		return stid;
	}

	public void setStid(String stid) {
		this.stid = stid;
	}

	public String getNRC() {
		return NRC;
	}

	public void setNRC(String nRC) {
		NRC = nRC;
	}

	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCpassword() {
		return cpassword;
	}

	public void setCpassword(String cpassword) {
		this.cpassword = cpassword;
	}
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	

	public String getDOB() {
		return DOB;
	}

	public void setDOB(String dOB) {
		DOB = dOB;
	}

	public String getRdate() {
		return rdate;
	}

	public void setRdate(String rdate) {
		this.rdate = rdate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getKnownfrom() {
		return knownfrom;
	}

	public void setKnownfrom(String knownfrom) {
		this.knownfrom = knownfrom;
	}

	public String getEdu() {
		return edu;
	}

	public void setEdu(String edu) {
		this.edu = edu;
	}

	

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getImg() {
		return "/images/Student/StudentPhoto/"+img;
	}

	public void setImg(String img) {
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

	
	public String getFather() {
		return father;
	}

	public void setFather(String father) {
		this.father = father;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", stid=" + stid + ", name=" + name + ", father=" + father + ", NRC=" + NRC
				+ ", DOB=" + DOB + ", phone=" + phone + ", gender=" + gender + ", email=" + email + ", password="
				+ password + ", cpassword=" + cpassword + ", rdate=" + rdate + ", address=" + address + ", knownfrom="
				+ knownfrom + ", edu=" + edu + ", other=" + other + ", img=" + img + ", QR=" + QR + "]";
	}
	
	
	
}
