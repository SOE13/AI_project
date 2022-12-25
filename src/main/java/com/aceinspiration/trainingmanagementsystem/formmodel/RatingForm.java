package com.aceinspiration.trainingmanagementsystem.formmodel;

public class RatingForm {

	private Long bid;
	private String batchName;
	private int rating;
	private long totalRating;
	private long totalCount;
	private String teacherName;
	

	public Long getBid() {
		return bid;
	}

	public void setBid(Long bid) {
		this.bid = bid;
	}

	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public long getTotalRating() {
		return totalRating;
	}

	public void setTotalRating(long totalRating) {
		this.totalRating = totalRating;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public RatingForm() {
		super();
	}

	public RatingForm(Long bid, String batchName, int rating, long totalRating, long totalCount, String teacherName) {
		super();
		this.bid = bid;
		this.batchName = batchName;
		this.rating = rating;
		this.totalRating = totalRating;
		this.totalCount = totalCount;
		this.teacherName = teacherName;
	}

	@Override
	public String toString() {
		return "RatingForm [bid=" + bid + ", batchName=" + batchName + ", rating=" + rating + ", totalRating="
				+ totalRating + ", totalCount=" + totalCount + ", teacherName=" + teacherName + "]";
	}

}
