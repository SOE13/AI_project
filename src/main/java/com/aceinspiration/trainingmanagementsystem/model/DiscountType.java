package com.aceinspiration.trainingmanagementsystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Entity
@Table(name = "discounttype")
public class DiscountType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank(message = "Discount Type Name can not be null!")
	@Column(name = "discount_name")
	private String discountName;

	@NotNull(message = "Discount Percent can not be null!")
	@DecimalMax("100.0")
	@DecimalMin("0.0")
	@Column(name = "discount_percent")
	private Double discountPercent;

	public DiscountType(@NotBlank(message = "Discount Type Name can not be null!") String discountName,
			@NotNull @DecimalMax("100.0") @DecimalMin("0.0") Double discountPercent) {
		this.discountName = discountName;
		this.discountPercent = discountPercent;
	}

	public DiscountType() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDiscountName() {
		return discountName;
	}

	public void setDiscountName(String discountName) {
		this.discountName = discountName;
	}

	public Double getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(Double discountPercent) {
		this.discountPercent = discountPercent;
	}

}
