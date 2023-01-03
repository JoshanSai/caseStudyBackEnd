package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.FunctionHalls;
import com.example.demo.entity.GuestRooms;
import com.example.demo.repository.FunctionHallRepo;
import com.example.demo.repository.GuestRoomRepo;

@RestController
@CrossOrigin("http://localhost:4200/")
public class FunctionHallController {

	@Autowired
	FunctionHallRepo functionHallRepo;
	
	 @GetMapping("/functionHalls/{comId}")
	    public List<FunctionHalls> getGuestRoomsByCommunityId(@PathVariable(value = "comId") long comId) {
	        List<FunctionHalls> coms = functionHallRepo.findByCommunityId(comId);
	        if(coms.isEmpty()) {
	        	return null;
	        }
	        else {
	        	return coms;
	        } 
	    }

}
