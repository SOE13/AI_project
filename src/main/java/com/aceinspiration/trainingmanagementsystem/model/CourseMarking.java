package com.aceinspiration.trainingmanagementsystem.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
public class CourseMarking {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@OneToOne
	private Course course;
	
	
	@Min(1 )
	@Max(100)
	private Integer attendanceMark;
	

	@Min(1)
	@Max(100)
	private Integer assignmentMark;
	
	
	//@Max(100)
	private Integer midTermMark;
	
	
	//@Max(100)
	private Integer finalMark;
	
	
	//@Max(100)
	private Integer projectMark;

	
	public CourseMarking() {
		super();
	}

	public CourseMarking(Course courseName) {
		super();
		this.course = courseName;
	}

	public CourseMarking(Course course, @Min(1) @Max(100) Integer attendanceMark,
			@Min(1) @Max(100) Integer assignmentMark,  Integer midTermMark, Integer finalMark,
			 Integer projectMark) {
		super();
		this.course = course;
		this.attendanceMark = attendanceMark;
		this.assignmentMark = assignmentMark;
		this.midTermMark = midTermMark;
		this.finalMark = finalMark;
		this.projectMark = projectMark;
	}

	public CourseMarking(Long id, Course courseName, Integer attendanceMark, Integer assignmentMark,
			Integer midTermMark,Integer finalMark, Integer projectMark) {
		super();
		this.id = id;
		this.course=courseName;
		this.attendanceMark=attendanceMark;
		this.assignmentMark=assignmentMark;
		this.midTermMark=midTermMark;
		this.finalMark=finalMark;
		this.projectMark=projectMark;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course courseName) {
		this.course = courseName;
	}

	public Integer getAttendanceMark() {
		return attendanceMark;
	}

	public void setAttendanceMark(Integer attendanceMark) {
		this.attendanceMark = attendanceMark;
	}

	public Integer getAssignmentMark() {
		return assignmentMark;
	}

	public void setAssignmentMark(Integer assignmentMark) {
		this.assignmentMark = assignmentMark;
	}

	public Integer getMidTermMark() {
		return midTermMark;
	}

	public void setMidTermMark(Integer midTermMark) {
		this.midTermMark = midTermMark;
	}

	public Integer getFinalMark() {
		return finalMark;
	}

	public void setFinalMark(Integer finalMark) {
		this.finalMark = finalMark;
	}

	public Integer getProjectMark() {
		return projectMark;
	}

	public void setProjectMark(Integer projectMark) {
		this.projectMark = projectMark;
	}

}
