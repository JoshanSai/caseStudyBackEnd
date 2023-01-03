package com.example.demo.entity;

import org.springframework.stereotype.Service;

@Service
public class superJWTReponse {
	public SuperUser user;
	public String token;
	public SuperUser getUser() {
		return user;
	}
	public void setUser(SuperUser user) {
		this.user = user;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

}
