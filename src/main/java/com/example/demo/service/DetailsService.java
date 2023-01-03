package com.example.demo.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.SuperUser;
import com.example.demo.entity.Tenants;
import com.example.demo.repository.SuperUserRepo;
import com.example.demo.repository.TenantsRepo;

@Service
public class DetailsService implements UserDetailsService {
	
	@Autowired
	private SuperUserRepo repository;
	@Autowired
	TenantsRepo tenentsRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println(username);
		List<SuperUser> user = repository.findByName(username);
		List<Tenants> user1 = tenentsRepo.findByPersonName(username);
		Details userDetails = new Details();
		if(user.size()!=0)
		{
			userDetails = new Details();
			userDetails.setUser(user.get(0));
				}
		else if(user1.size()!=0)
		{
			userDetails = new Details();
			userDetails.setTenant(user1.get(0));
				}
		else {
			throw new UsernameNotFoundException("user not exits with the name" + username);
		}
		
	
		return userDetails;
	}

}