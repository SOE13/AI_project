package com.aceinspiration.trainingmanagementsystem.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aceinspiration.trainingmanagementsystem.model.Admin;
import com.aceinspiration.trainingmanagementsystem.repository.AdminRepository;

@Repository
@Transactional
public class AdminJpa {

	@Autowired
	AdminRepository repo;

	@PersistenceContext
	EntityManager entityManager;

	public Admin selectForProfile(String mail) {
		TypedQuery<Admin> query = entityManager.createQuery("from Admin a where a.mail=?1", Admin.class);
		query.setParameter(1, mail);
		return query.getSingleResult();
	}

	public List<String> selectForSecurity() {
		TypedQuery<Admin> query = entityManager.createQuery("from Admin", Admin.class);
		List<Admin> roles = query.getResultList();
		List<String> list = new ArrayList<String>();
		list.add("Admin");

		for (int i = 0; i < roles.size(); i++) {
			list.add(roles.get(i).getRole().getName());
		}

		return list;
	}

	public void delete(Long id) {
		repo.deleteById(id);

	}

	public void update(Admin admin) {
		repo.save(admin);
	}

	public Admin selectOne(Long id) {
		return entityManager.find(Admin.class, id);
	}

	public List<Admin> selectAll() {
		return repo.findAll();
	}

	public void insert(Admin admin) {
		repo.save(admin);
	}
}
