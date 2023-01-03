package com.example.demo.entity;

import java.util.Date;


import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name = "functionHall")
public class FunctionHalls {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    public long id;
	public long capacity;
	public long communityId;
	public String createdBy;
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	public Date createdDate;
	public long rentPerDay;
	public String roomName;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getCapacity() {
		return capacity;
	}
	public void setCapacity(long capacity) {
		this.capacity = capacity;
	}
	public long getCommunityId() {
		return communityId;
	}
	public void setCommunityId(long communityId) {
		this.communityId = communityId;
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
	public long getRentPerDay() {
		return rentPerDay;
	}
	public void setRentPerDay(long rentPerDay) {
		this.rentPerDay = rentPerDay;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

}
