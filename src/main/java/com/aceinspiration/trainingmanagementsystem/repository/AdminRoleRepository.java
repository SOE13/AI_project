package com.aceinspiration.trainingmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aceinspiration.trainingmanagementsystem.model.Role;

public interface AdminRoleRepository extends JpaRepository<Role,Long>{

}
