package com.example.demo.entity;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TenantJWTResponse {
	public List<Tenants> user;
	public String token;
	public List<Tenants> getUser() {
		return user;
	}
	public void setUser(List<Tenants> user) {
		this.user = user;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	

}