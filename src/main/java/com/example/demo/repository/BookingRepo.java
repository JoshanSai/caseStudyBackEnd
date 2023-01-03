package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Booking;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Long>{	
	List<Booking> findByFlatIdAndTypeAndCreatedBy(long id,String type,String name);
	List<Booking> findByType(Sort sort, String type);
	List<Booking> findById(long id);
	List<Booking> findByRoomNameAndApprovedByManager(String name,String status);
}