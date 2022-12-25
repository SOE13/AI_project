package com.aceinspiration.trainingmanagementsystem.formmodel;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CourseMarkingForm {
	
	private Long id;
	
	
	@NotNull(message = "Course Name must not be Blank!")
	private Long course;
	
	
	@Min(1 )
	@Max(100)
	@NotNull(message="Attendance Mark must not be Blank!")
	private Integer attendanceMark;
	

	@Min(1)
	@Max(100)
	@NotNull(message="Assignment Mark must not be Blank!")
	private Integer assignmentMark;
	
	/*
	 * @Min(1)
	 * 
	 * @Max(100)
	 */
	@NotNull(message="Midterm Mark must not be Blank!")
	private Integer midTermMark;
	
	/*
	 * @Min(1)
	 * 
	 * @Max(100)
	 */
	@NotNull(message="Final Mark must not be Blank!")
	private Integer finalMark;
	
	/*
	 * @Min(1)
	 * 
	 * @Max(100)
	 */
	@NotNull(message="Project Mark must not be Blank!")
	private Integer projectMark;


	public CourseMarkingForm(Long id, Long course, @Min(1) @Max(100) Integer attendanceMark,
			@Min(1) @Max(100) Integer assignmentMark,  Integer midTermMark,  Integer finalMark,
			Integer projectMark) {
		super();
		this.id = id;
		this.course = course;
		this.attendanceMark = attendanceMark;
		this.assignmentMark = assignmentMark;
		this.midTermMark = midTermMark;
		this.finalMark = finalMark;
		this.projectMark = projectMark;
	}


	public CourseMarkingForm(Long course, @Min(1) @Max(100) Integer attendanceMark,
			@Min(1) @Max(100) Integer assignmentMark, Integer midTermMark,Integer finalMark,
			Integer projectMark) {
		super();
		this.course = course;
		this.attendanceMark = attendanceMark;
		this.assignmentMark = assignmentMark;
		this.midTermMark = midTermMark;
		this.finalMark = finalMark;
		this.projectMark = projectMark;
	}


	public CourseMarkingForm() {
		super();
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getCourse() {
		return course;
	}


	public void setCourse(Long course) {
		this.course = course;
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
