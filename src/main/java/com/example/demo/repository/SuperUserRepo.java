package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Super_user;


@Repository
public interface SuperUserRepo extends JpaRepository<Super_user, Long>{
	List<Super_user> findByName(String name);

}