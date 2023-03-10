package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.SuperUser;



@Repository
public interface SuperUserRepo extends JpaRepository<SuperUser, Long>{
	List<SuperUser> findByName(String name);
	
}