package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Communities;
import com.example.demo.entity.Flats;
import com.example.demo.repository.FlatsRepo;

@RestController
@CrossOrigin("http://localhost:4200/")
public class FlatsController {
	
	@Autowired
	FlatsRepo flatsRepo;
	
	@GetMapping("/flats/{comId}")
    public List < Flats > getAllFlatsByCommunityId(@PathVariable(value = "comId") long comId) {
	   System.out.println("inside get request");
        return flatsRepo.findByCommunityId(comId);
    }
	@GetMapping("/flatsId/{id}")
    public List < Flats > getFlatsById(@PathVariable(value = "id") long id) {
	   System.out.println("inside get request");
        return flatsRepo.findById(id);
    }
	@PostMapping("/putFlats/")
    public Flats putNewFlats( @RequestBody Flats com) {
    	List<Flats> com1=flatsRepo.findByFlatNumber(com.flatNumber);
    	if(com1.size()==0) {
             return flatsRepo.save(com);
    	}else{
    		return null;
    	}
   }
	@PutMapping("/updateFlats/{id}/")
	public ResponseEntity<Flats> updateTutorial(@PathVariable("id") long id, @RequestBody Flats expenses) {
		List<Flats> tutorialData = flatsRepo.findById(id);
		List<Flats> str=flatsRepo.findByFlatNumber(expenses.flatNumber);
		System.out.println("---------------000000000-------------"+tutorialData.get(0).flatNumber+expenses.flatNumber);
		if (tutorialData.size()!=0 && str.size()==0 ) {
			Flats _tutorial = tutorialData.get(0);
			_tutorial.setSqft(expenses.Sqft);
			_tutorial.setFlatNumber(expenses.flatNumber);
			_tutorial.setNumberOfRooms(expenses.numberOfRooms);
			return new ResponseEntity<>(flatsRepo.save(_tutorial), HttpStatus.OK);
		}
		else if(str.size()!=0 && tutorialData.get(0).flatNumber.equals(expenses.flatNumber)) {
			Flats _tutorial = tutorialData.get(0);
			_tutorial.setSqft(expenses.Sqft);
			_tutorial.setFlatNumber(expenses.flatNumber);
			_tutorial.setNumberOfRooms(expenses.numberOfRooms);
			return new ResponseEntity<>(flatsRepo.save(_tutorial), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(null,HttpStatus.OK);
		}
	}
}
