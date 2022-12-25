package com.aceinspiration.trainingmanagementsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aceinspiration.trainingmanagementsystem.model.Gallery;
import com.aceinspiration.trainingmanagementsystem.repository.GalleryRepository;

@Service
public class GalleryJpa {

	@Autowired
	GalleryRepository repo;
	 public void update(Gallery gallery) {
		repo.save(gallery);
	 }
	 public void delete(Long id) {
		 repo.deleteById(id);
	 }
	 public Gallery selectOne(Long id) {
		 Optional<Gallery> optio=repo.findById(id);
		 return optio.get();
	 }
	 public List<Gallery> selectAll(){
		 return repo.findAll();
	 }
	 public void insert(Gallery gallery) {
		 repo.save(gallery);
	 }
}
