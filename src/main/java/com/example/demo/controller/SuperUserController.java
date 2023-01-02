package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.SuperVerify;
import com.example.demo.entity.Super_user;
import com.example.demo.repository.SuperUserRepo;

@RestController
@CrossOrigin("http://localhost:4200/")
public class SuperUserController{
    
    @Autowired
     private SuperUserRepo superUserRepo;
    @Autowired
    private SuperVerify superVerify;
    
   @GetMapping("/employees")
    public List<Super_user> getAllEmployees() {
	   System.out.println("inside get request");
        return superUserRepo.findAll();
    }
    @PostMapping("/verify")
    public List<Super_user> createEmployee(@RequestBody SuperVerify body) {
       List<Super_user> user=new ArrayList<>();
       user=superUserRepo.findByName(body.name);
       System.out.println(user.get(0).getPassword());
       System.out.println(user.get(0).getName());
       System.out.println(body.password);
       System.out.println(body.name);
       if(user.get(0).getName().equalsIgnoreCase(body.name) && user.get(0).getPassword().contentEquals(body.password) ) {
    	   return user;
       }
       else {
    	   return null;
       }
}
    
}