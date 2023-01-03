package com.example.demo.controller;

import java.util.ArrayList;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.SuperUser;
import com.example.demo.entity.SuperVerify;
import com.example.demo.entity.superJWTReponse;
import com.example.demo.repository.SuperUserRepo;
import com.example.demo.util.JwtUtil;

@RestController
@CrossOrigin("http://localhost:4200/")
public class SuperUserController{
    
    @Autowired
     private SuperUserRepo superUserRepo;
    @Autowired
    private SuperVerify superVerify;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    
   @GetMapping("/employees")
    public List<SuperUser> getAllEmployees() {
	   System.out.println("inside get request");
        return superUserRepo.findAll();
    }
//    @PostMapping("/verify")
//    public List<SuperUser> createEmployee(@RequestBody SuperVerify body) {
//       List<SuperUser> user=new ArrayList<>();
//       user=superUserRepo.findByName(body.name);
//       if(user.get(0).getName().equalsIgnoreCase(body.name) && user.get(0).getPassword().contentEquals(body.password) ) {
//    	   return user;
//       }
//       else {
//    	   return null;
//       }
//}
    @PostMapping("/verify")
    public superJWTReponse generateToken(@RequestBody SuperVerify authRequest) throws Exception {
	 
    	 List<SuperUser> user = superUserRepo.findByName(authRequest.getName());
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getName(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("inavalid username/password");
        }
        superJWTReponse response = new superJWTReponse();
        String token = jwtUtil.generateToken(authRequest.getName(),user.get(0).getId());
        if(user.get(0).getPassword().equals(authRequest.getPassword())) {
        	response.setUser(user.get(0));
        }
        else {
      	  response.setUser(null);
        }
        
        response.setToken(token);
        return response;
    } 
    
}