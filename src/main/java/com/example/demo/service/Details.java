package com.example.demo.service;

import java.util.Collection;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.demo.entity.SuperUser;
import com.example.demo.entity.Tenants;

@Service
public class Details implements UserDetails {

	private static final long serialVersionUID = 1L;
	public SuperUser user;
	public Tenants user1;
	public SuperUser getUser() {
		return user;
	}

	public void setUser(SuperUser user) {
		this.user = user;
	}
	public Tenants getTenant() {
		return user1;
	}

	public void setTenant(Tenants user1) {
		this.user1 = user1;
	}

//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//	  Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//	  SimpleGrantedAuthority authority = new SimpleGrantedAuthority(this.user.getEmail());
//	  System.out.println(authority.getAuthority());
//	  authorities.add(authority);
//	  return authorities;
//	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		if(user!=null) {
			return user.getPassword();
		}
		else {
			return user1.getPassword();
		}
		
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		if(user!=null) {
		return user.getName();
	}
		else {
			return user1.getPersonName();
		}
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
