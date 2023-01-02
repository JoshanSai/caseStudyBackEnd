package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Communities;
import com.example.demo.entity.Super_user;

@Repository
public interface CommunitiesRepo extends JpaRepository<Communities, Long>{

	List<Communities> findByCreatedBy(Sort by, String name);
	List<Communities> findById(long id);
	List<Communities> findByCommunityNameAndCommunityAddress(String communityName, String communityAddress);

}