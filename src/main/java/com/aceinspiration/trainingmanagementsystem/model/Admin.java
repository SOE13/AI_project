package com.aceinspiration.trainingmanagementsystem.model;

import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

@Entity
public class Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Name  can not be blank!")
	private String name;

	@NotBlank(message = "Mail  can not be blank!")
	private String mail;

	@NotBlank(message = "Password  can not be blank!")
	private String pass;

	private String confpass;

	private boolean enable;

	@ManyToOne
	private Role role;

	private String permit;

	@Transient
	private String[] roles;

	public String[] getRoles() {
		return roles;
	}

	public void setRoles(String[] roles) {
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getPermit() {
		return permit;
	}

	public void setPermit(String permit) {
		this.permit = permit;
	}

	public Admin() {
		super();
	}

	public Admin(Long id, @NotBlank(message = "Name  can not be blank!") String name,
			@NotBlank(message = "Mail  can not be blank!") String mail,
			@NotBlank(message = "Password  can not be blank!") String pass, String confpass, boolean enable,
			@NotBlank(message = "Admin's role can not be blank!") Role role, String permit, String[] roles) {
		super();
		this.id = id;
		this.name = name;
		this.mail = mail;
		this.pass = pass;
		this.confpass = confpass;
		this.enable = enable;
		this.role = role;
		this.permit = permit;
		this.roles = roles;
	}

	public Admin(@NotBlank(message = "Name  can not be blank!") String name,
			@NotBlank(message = "Mail  can not be blank!") String mail,
			@NotBlank(message = "Password  can not be blank!") String pass, String confpass, boolean enable,
			@NotBlank(message = "Admin's role can not be blank!") Role role, String permit, String[] roles) {
		super();
		this.name = name;
		this.mail = mail;
		this.pass = pass;
		this.confpass = confpass;
		this.enable = enable;
		this.role = role;
		this.permit = permit;
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "Admin [id=" + id + ", name=" + name + ", mail=" + mail + ", pass=" + pass + ", confpass=" + confpass
				+ ", enable=" + enable + ", role=" + role + ", permit=" + permit + ", roles=" + Arrays.toString(roles)
				+ "]";
	}
	
	
	

}
