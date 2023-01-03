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
@Table(name = "guestrooms")
public class GuestRooms {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    public long id;
	public String attachedWashRoom;
	public String bed;
	public long communityId;
	public String createdBy;
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	public Date createdDate;
	public long rentPerDay;
	public String updatedBy;
	@Temporal(TemporalType.DATE)
	public Date updatedDate;
	public String roomName;
	public String status;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAttachedWashRoom() {
		return attachedWashRoom;
	}
	public void setAttachedWashRoom(String attachedWashRoom) {
		this.attachedWashRoom = attachedWashRoom;
	}
	public String getBed() {
		return bed;
	}
	public void setBed(String bed) {
		this.bed = bed;
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
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	

}
