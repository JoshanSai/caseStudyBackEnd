package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entity.GuestRooms;
import com.example.demo.repository.GuestRoomRepo;

@RestController
@CrossOrigin("http://localhost:4200/")
public class GuestRoomController {

	@Autowired
	GuestRoomRepo guestRoomRepo;
	
	 @GetMapping("/guestRooms/{comId}")
	    public List<GuestRooms> getGuestRoomsByCommunityId(@PathVariable(value = "comId") long comId) {
	        List<GuestRooms> coms = guestRoomRepo.findByCommunityId(comId);
	        if(coms.isEmpty()) {
	        	return null;
	        }
	        else {
	        	return coms;
	        } 
	    }

}
