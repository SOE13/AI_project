package com.aceinspiration.trainingmanagementsystem.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotBlank;

@Entity
@NamedQuery(name = "find_all_roles", query = "select r from Role r")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Role must not be blank")
	private String name;

	
	public Role() {
	}

	public Role(Long id) {
		super();
		this.id = id;
	}

	

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Role(Long id, @NotBlank(message = "Role must not be blank") String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Role(@NotBlank(message = "Role must not be blank") String name) {
		super();
		this.name = name;
	}

	

	
	
	
}
