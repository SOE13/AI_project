package com.aceinspiration.trainingmanagementsystem.formmodel;

public class StudentRelatedForm {
	private long studentBatchId;

	private long bId;
	private String batchName;
	private String stid;
	private String name;
	private String phone;
	private String email;
	private Long id;
	private int rating;
	private String grade;
	private String start;
	private String end;
	private String status;

	public long getbId() {
		return bId;
	}

	public void setbId(long bId) {
		this.bId = bId;
	}

	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}
	
	

	public long getStudentBatchId() {
		return studentBatchId;
	}

	public void setStudentBatchId(long studentBatchId) {
		this.studentBatchId = studentBatchId;
	}

	public String getStid() {
		return stid;
	}

	public void setStid(String stid) {
		this.stid = stid;
	}
	
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public StudentRelatedForm(long bId, String batchName, String stid, Long id, int rating) {
		super();
		this.bId = bId;
		this.batchName = batchName;
		this.stid = stid;
		this.id = id;
		this.rating = rating;
	}

	public StudentRelatedForm(long bId, String batchName, String stid, String name) {
		super();
		this.bId = bId;
		this.batchName = batchName;
		this.stid = stid;
		this.name = name;
	}

	public StudentRelatedForm(String stid, String name, String batchName, String grade) {
		super();
		this.batchName = batchName;
		this.stid = stid;
		this.name = name;
		this.grade = grade;
	}

	public StudentRelatedForm() {
		super();
	}

	public StudentRelatedForm(String batchName, String stid, String grade, String start, String end) {
		super();
		this.batchName = batchName;
		this.stid = stid;
		this.grade = grade;
		this.start = start;
		this.end = end;
	}

	public StudentRelatedForm(long studentBatchId,long bId, String batchName,Long id, String stid, String name, String phone, String email,String status) {
		super();
		this.studentBatchId=studentBatchId;
		this.bId = bId;
		this.batchName = batchName;
		this.id=id;
		this.stid = stid;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.status=status;
	}
	
	
	
	

}
