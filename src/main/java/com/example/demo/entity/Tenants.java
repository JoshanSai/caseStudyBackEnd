package com.example.demo.entity;

import java.util.Date;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="tenants")
public class Tenants {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long id;
	public long flatId;
	public String personName;
	public long phoneNumber;
	public String email;
	@JsonProperty(access=Access.WRITE_ONLY)
	public String password;
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	public Date memberSince=new Date(System.currentTimeMillis());
	@Temporal(TemporalType.DATE)
	public Date adminStartDate;
	@Temporal(TemporalType.DATE)
	public Date adminEndDate;
	public String createdBy;
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	public Date createdDate=new Date(System.currentTimeMillis());
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "communityId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
	public Communities community;
	
	public Communities getCommunity() {
		return community;
	}
	public void setCommunity(Communities community) {
		this.community = community;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getFlatId() {
		return flatId;
	}
	public void setFlatId(long flatId) {
		this.flatId = flatId;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getMemberSince() {
		return memberSince;
	}
	public void setMemberSince(Date memberSince) {
		this.memberSince = memberSince;
	}
	public Date getAdminStartDate() {
		return adminStartDate;
	}
	public void setAdminStartDate(Date adminStartDate) {
		this.adminStartDate = adminStartDate;
	}
	public Date getAdminEndDate() {
		return adminEndDate;
	}
	public void setAdminEndDate(Date adminEndDate) {
		this.adminEndDate = adminEndDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	
}
