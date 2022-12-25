package com.aceinspiration.trainingmanagementsystem.model;

public class BatchCarry {

	private String name;
	private Integer total;
	
	
	public BatchCarry(String name, Integer total) {
		super();
		this.name = name;
		this.total = total;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	
}
