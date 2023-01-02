package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Tenants;

@Repository
public interface TenantsRepo extends JpaRepository<Tenants, Long>{

	List<Tenants> findByPersonName(String name);

	List<Tenants> findByCommunityIdAndPersonName(Long communityId, String personName);

	List<Tenants> findByCommunityId(Long communityId);

	List<Tenants> findById(long id);

}
