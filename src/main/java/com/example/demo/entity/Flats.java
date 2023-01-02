package com.example.demo.entity;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "flats")
public class Flats {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	public long Sqft;
	public long communityId;
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	public Date createdDate;
	public String flatNumber;
	public long numberOfRooms;
	public Date updatedDate;
	public String createdBy;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getSqft() {
		return Sqft;
	}
	public void setSqft(long Sqft) {
		this.Sqft = Sqft;
	}
	public long getCommunityId() {
		return communityId;
	}
	public void setCommunityId(long communityId) {
		this.communityId = communityId;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getFlatNumber() {
		return flatNumber;
	}
	public void setFlatNumber(String flatNumber) {
		this.flatNumber = flatNumber;
	}
	public long getNumberOfRooms() {
		return numberOfRooms;
	}
	public void setNumberOfRooms(long numberOfRooms) {
		this.numberOfRooms = numberOfRooms;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
}
