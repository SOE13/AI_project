package com.aceinspiration.trainingmanagementsystem.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "student_batch")
public class StudentBatch {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne()
	@JoinColumn(name = "student_id")
	private Student student;
	
	@ManyToOne()
	@JoinColumn(name = "batch_id")
	private Batch batch;
	
	@ManyToOne()
	@JoinColumn(name = "discount_type_id")
	private DiscountType discountType;
	
	@Column(name = "actual_amount")
	private Double actualAmount;
	
	@Column(name = "total_amount")
	private Double totalAmount;
	
	@Column(name = "discount_amount")
	private Double discountAmount;
	
	private String status;

	private int rating;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
	public DiscountType getDiscountType() {
		return discountType;
	}

	public void setDiscountType(DiscountType discountType) {
		this.discountType = discountType;
	}

	
	
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Batch getBatch() {
		return batch;
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
	}

	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getActualAmount() {
		return actualAmount;
	}

	public void setActualAmount(Double actualAmount) {
		this.actualAmount = actualAmount;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(Double discountAmount) {
		this.discountAmount = discountAmount;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "StudentBatch [id=" + id + ", student=" + student + ", batch=" + batch + ", discountType=" + discountType
				+ ", actualAmount=" + actualAmount + ", totalAmount=" + totalAmount + ", discountAmount="
				+ discountAmount + ", status=" + status + ", rating=" + rating + "]";
	}

	

	
	
}
