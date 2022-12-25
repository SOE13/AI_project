package com.aceinspiration.trainingmanagementsystem.formmodel;

public class StudentProfileAttendanceCarry {
	
	String name;
	int persent;
	
	
	
	public StudentProfileAttendanceCarry(String name, int persent) {
		super();
		this.name = name;
		this.persent = persent;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPersent() {
		return persent;
	}
	public void setPersent(int persent) {
		this.persent = persent;
	}
	

}
