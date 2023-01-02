package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.management.AttributeNotFoundException;

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

import com.example.demo.entity.Tenants;
import com.example.demo.repository.CommunitiesRepo;
import com.example.demo.repository.FlatsRepo;
import com.example.demo.repository.TenantsRepo;

@RestController
@CrossOrigin("http://localhost:4200/")
public class TenantsController {
	
	@Autowired
	TenantsRepo tenentsRepo;
	@Autowired
	FlatsRepo flatRepo;
	@Autowired
	CommunitiesRepo communityRepo;
//	@Autowired
//    private AuthenticationManager authenticationManager;
//    @Autowired
//    private JwtUtil jwtUtil;
	  @GetMapping("/tenants")
	    public List < Tenants > getAllFlats() {
		   System.out.println("inside get request");
	        return tenentsRepo.findAll();
	    }

	 @PostMapping("/putTenants")
	    public Tenants createFlats( @RequestBody Tenants tenant) {
	             return tenentsRepo.save(tenant);
	   }
	 @GetMapping("/tenants/{name}")
	    public List<Tenants> getEmployeeById(@PathVariable(value = "name") String name) {
	        List<Tenants> Cuser = tenentsRepo.findByPersonName(name);
	        if(Cuser.isEmpty()) {
	        	return null;
	        }
	        else {
	        	return Cuser;
	        }
	        
	    }
//	 @PostMapping("/verify-tenant")
//	    public List<Tenants> createEmployee( @RequestBody tenantsVerify verify) {
//	             List<Tenants> obj = tenentsRepo.findByPersonName(verify.getName());
//	              if(obj.get(0).getPassword().equals(verify.getPassword())) {
//	            	  return obj;
//	              }
//	              else {
//	            	  return null;
//	              }
//}
	 //====================================================================================================================
//	 @PostMapping("/verify-tenant")
//	    public TenantJWTResponse generateToken(@RequestBody tenantsVerify authRequest) throws Exception {
//		 
//	    	 List<Tenants> user = tenentsRepo.findByPersonName(authRequest.getName());
//	        try {
//	            authenticationManager.authenticate(
//	                    new UsernamePasswordAuthenticationToken(authRequest.getName(), authRequest.getPassword())
//	            );
//	        } catch (Exception ex) {
//	            throw new Exception("inavalid username/password");
//	        }
//	        TenantJWTResponse response=new TenantJWTResponse();
//	        String token=jwtUtil.generateToken(authRequest.getName(),user.get(0).getId());
//	        if(user.get(0).getPassword().equals(authRequest.getPassword())) {
//          	  response.setUser(user);
//            }
//            else {
//          	  response.setUser(null);
//            }
//	       response.setToken(token);
//	       return response;
//	    }  //reutrns a list of tenats by name along with jwt token ------------ change josh
	 //====================================================================================================================
	 @PostMapping("/putTenants/{communityId}")
	  public ResponseEntity<Tenants> AddTenantsByCommunityId(@PathVariable(value = "communityId") Long communityId,
	      @RequestBody Tenants commentRequest) {
	    Tenants comment = null;
	    List<Tenants> str = new ArrayList<>();
	    str=tenentsRepo.findByCommunityIdAndPersonName(communityId,commentRequest.personName);
	    if(str.size()==0) {
		try {
			comment = communityRepo.findById(communityId).map(community -> {
			  commentRequest.setCommunity(community);
			  return tenentsRepo.save(commentRequest);
			}).orElseThrow(() -> new AttributeNotFoundException("Not found Tutorial with id = " + communityId));
		} catch (AttributeNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    return new ResponseEntity<>(comment, HttpStatus.CREATED);
	  }
	    return new ResponseEntity<>(null, HttpStatus.CREATED);
	 }

		
	 
	 @GetMapping("/tenantsByComId/{communityId}")
	  public ResponseEntity<List<Tenants>> GetAllTenantsByCommunityId(@PathVariable(value = "communityId") Long communityId) throws AttributeNotFoundException {
	    if (!communityRepo.existsById(communityId)) {
	      throw new AttributeNotFoundException("Not found Tutorial with id = " + communityId);
	    }

	    List<Tenants> comments = tenentsRepo.findByCommunityId(communityId);
	    return new ResponseEntity<>(comments, HttpStatus.OK);
	  }
	 @GetMapping("/tenantsByComId/{communityId}/{name}")
	  public ResponseEntity<List<Tenants>> GetAllTenantsByCommunityIdAndName(@PathVariable(value = "communityId") Long communityId,@PathVariable(value = "name") String name) throws AttributeNotFoundException {
	    if (!communityRepo.existsById(communityId)) {
	      throw new AttributeNotFoundException("Not found Tutorial with id = " + communityId);
	    }

	    List<Tenants> comments = tenentsRepo.findByCommunityIdAndPersonName(communityId,name);
	    return new ResponseEntity<>(comments, HttpStatus.OK);
	  }
	 @GetMapping("/managersByComId/{communityId}")
	  public ResponseEntity<List<Tenants>> GetAllManagersByCommunityId(@PathVariable(value = "communityId") Long communityId) throws AttributeNotFoundException {
	    Date date=new Date();
		 if (!communityRepo.existsById(communityId)) {
	      throw new AttributeNotFoundException("Not found Tutorial with id = " + communityId);
	    }
	    List<Tenants> comments = tenentsRepo.findByCommunityId(communityId);
	    List<Tenants> str = new ArrayList<Tenants>();
	    for(int i=0;i<comments.size();i++) {
	    	System.out.print(comments.get(i).adminStartDate);
	    	if(comments.get(i).adminStartDate!=null && comments.get(i).adminEndDate==null) {
	    		str.add(comments.get(i));
	    	}
	    	else if(comments.get(i).adminStartDate!=null && comments.get(i).adminEndDate!=null){
	    		if(comments.get(i).adminEndDate.compareTo(date)>0) {
	    			str.add(comments.get(i));
	    		}
	    	}
	    }
	    return new ResponseEntity<>(str, HttpStatus.OK);
	  }
	 @PutMapping("/updateTenants/{id}")
		public ResponseEntity<Tenants> updateTutorial(@PathVariable("id") long id, @RequestBody Tenants expenses) {
			List<Tenants> tutorialData = tenentsRepo.findById(id);
			if (tutorialData.size()!=0) {
				Tenants _tutorial = tutorialData.get(0);
				_tutorial.setFlatId(expenses.flatId);
				_tutorial.setPersonName(expenses.personName);
				_tutorial.setEmail(expenses.email);
				_tutorial.setAdminStartDate(expenses.adminStartDate);
				_tutorial.setAdminEndDate(expenses.adminEndDate);
				_tutorial.setPhoneNumber(expenses.phoneNumber);
				
				return new ResponseEntity<>(tenentsRepo.save(_tutorial), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
			}
		}
	 

}
