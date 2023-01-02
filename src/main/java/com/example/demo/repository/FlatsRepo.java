package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Flats;

@Repository
public interface FlatsRepo extends JpaRepository<Flats, Long>{

	List<Flats> findByCommunityId(long id);
	List<Flats> findById(long id);
	List<Flats> findByFlatNumber(String num);
}
