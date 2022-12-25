package com.aceinspiration.trainingmanagementsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aceinspiration.trainingmanagementsystem.model.DiscountType;
import com.aceinspiration.trainingmanagementsystem.repository.DiscountTypeRepository;


@Service
@Transactional
public class DiscountTypeJpa {
	
	@Autowired
	DiscountTypeRepository discountTypeEntity;
	
	public List<DiscountType> findAll(){
		return discountTypeEntity.findAll();
	}
	
	public DiscountType findById(long id){
		Optional<DiscountType> discount = discountTypeEntity.findById(id);
		if(discount.isPresent()){
			return discount.get();
		}
		return null;
	}
	public Optional<DiscountType> getDiscountTypById(long id) {
		return discountTypeEntity.findById(id);
	}

	
	public void insert(DiscountType discount){
		
		discountTypeEntity.save(discount);
	}
	public void update(DiscountType discount){
		
		discountTypeEntity.save(discount);
	}
	
	public void deleteById(long id) {
        discountTypeEntity.deleteById(id);
    }
	
	

}
