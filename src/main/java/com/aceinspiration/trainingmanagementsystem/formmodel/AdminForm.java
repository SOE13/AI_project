package com.aceinspiration.trainingmanagementsystem.formmodel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;



public class AdminForm {
	private Long id;

	@NotBlank(message = "Name  can not be blank!")
	private String name;

	@NotBlank(message = "Mail  can not be blank!")
	private String mail;

	@NotBlank(message = "Password  can not be blank!")
	private String pass;

	private String confpass;
	
	@NotNull(message = "Admin's Role can not be Blank!")
	private Long role;
	private String[] roles;
	
	private String permit;
	
	public String getPermit() {
		return permit;
	}
	public void setPermit(String permit) {
		this.permit = permit;
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
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getConfpass() {
		return confpass;
	}
	public void setConfpass(String confpass) {
		this.confpass = confpass;
	}
	
	public Long getRole() {
		return role;
	}
	public void setRole(Long role) {
		this.role = role;
	}
	public String[] getRoles() {
		return roles;
	}
	public void setRoles(String[] roles) {
		this.roles = roles;
	}
	
	public AdminForm(@NotBlank(message = "Name  can not be blank!") String name,
			@NotBlank(message = "Mail  can not be blank!") String mail,
			@NotBlank(message = "Password  can not be blank!") String pass, String confpass,
			@NotBlank(message = "Admin's role can not be blank!") Long role, String[] roles) {
		super();
		this.name = name;
		this.mail = mail;
		this.pass = pass;
		this.confpass = confpass;
		this.role = role;
		this.roles = roles;
	}
	public AdminForm(Long id, @NotBlank(message = "Name  can not be blank!") String name,
			@NotBlank(message = "Mail  can not be blank!") String mail,
			@NotBlank(message = "Password  can not be blank!") String pass, String confpass,
			@NotBlank(message = "Admin's role can not be blank!") Long role, String[] roles,  String permit) {
		super();
		this.id = id;
		this.name = name;
		this.mail = mail;
		this.pass = pass;
		this.confpass = confpass;
		this.role = role;
		this.roles = roles;
		this.permit=permit;
	}
	public AdminForm() {
		super();
	}

}
