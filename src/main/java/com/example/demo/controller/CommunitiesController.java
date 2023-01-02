package com.example.demo.controller;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
import com.example.demo.repository.CommunitiesRepo;

@RestController
@CrossOrigin("http://localhost:4200/")
public class CommunitiesController {

		@Autowired
		CommunitiesRepo comRepo;
		
		 
		   @GetMapping("/community")
		    public List < Communities > getAllSuperUsers() {
			   System.out.println("inside get request");
		        return comRepo.findAll();
		    }
		 
		   
		    @GetMapping("/community/{name}")
		    public List<Communities> getCommunityName(@PathVariable(value = "name") String name) {
		        List<Communities> coms = comRepo.findByCreatedBy(Sort.by(Sort.Direction.ASC,"id"), name);
		        if(coms.isEmpty()) {
		        	return null;
		        }
		        else {
		        	return coms;
		        } 
		    }
		    @GetMapping("/communityId/{id}")
		    public List<Communities> getCommunityById(@PathVariable(value = "id") long id) {
		        List<Communities> coms = comRepo.findById(id);
		        if(coms.isEmpty()) {
		        	return null;
		        }
		        else {
		        	return coms;
		        } 
		    }
		    @GetMapping("/communityId2/{id}")
		    public String getCommunityById2(@PathVariable(value = "id") long id) {
		       List< Communities >coms =  comRepo.findById(id);
//		        if(coms.isEmpty()) {
//		        	return null;
//		        }
//		        else {
//		        	return coms;
//		        } 
		        return coms.get(0).communityName;
		    }
		
		  
		    @PostMapping("/putCommunities")
		    public Communities createEmployee( @RequestBody Communities com) {
		    	List<Communities> com1=comRepo.findByCommunityNameAndCommunityAddress(com.communityName,com.communityAddress);
		    	if(com1.size()==0) {
		             return comRepo.save(com);
		    	}else{
		    		return null;
		    	}
		   }
		    @PutMapping("/updateCommunity/{id}")
			public ResponseEntity<Communities> updateTutorial(@PathVariable("id") long id, @RequestBody Communities expenses) {
				List<Communities> tutorialData = comRepo.findById(id);

				if (tutorialData.size()!=0) {
					Communities _tutorial = tutorialData.get(0);
					_tutorial.setCommunityName(expenses.communityName);
					_tutorial.setCommunityAddress(expenses.communityAddress);
					_tutorial.setRegisteredDate(expenses.registeredDate);
					return new ResponseEntity<>(comRepo.save(_tutorial), HttpStatus.OK);
				} else {
					return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
				}
			}


}
