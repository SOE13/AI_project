package com.aceinspiration.trainingmanagementsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aceinspiration.trainingmanagementsystem.model.Review;
import com.aceinspiration.trainingmanagementsystem.repository.ReviewRepository;

@Service
public class ReviewJpa {

	@Autowired
	ReviewRepository repo;
	
	public void delete(Long id) {
		repo.deleteById(id);
	}
	public void update(Review review) {
		repo.save(review);
	}
	public Review selectOne(Long id) {
		Optional<Review> optional=repo.findById(id);
		 return optional.get();
	}
	public List<Review> selectAll(){
		return repo.findAll();
	}
	public void insert(Review review) {
		repo.save(review);
	}
}
