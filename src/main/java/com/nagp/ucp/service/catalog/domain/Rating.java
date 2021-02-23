package com.nagp.ucp.service.catalog.domain;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Rating {

	private int id;

	private int serviceId;

	private String name;

	private double totalRating;

	private String comment;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp createdOn;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp modifiedOn;

	public Rating() {
		super();
	}

	public Rating(int id, int serviceId, String name, double totalRating, String comment, Timestamp createdOn,
			Timestamp modifiedOn) {
		super();
		this.id = id;
		this.serviceId = serviceId;
		this.name = name;
		this.totalRating = totalRating;
		this.comment = comment;
		this.createdOn = createdOn;
		this.modifiedOn = modifiedOn;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getTotalRating() {
		return totalRating;
	}

	public void setTotalRating(double totalRating) {
		this.totalRating = totalRating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Timestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public Timestamp getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Timestamp modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	@Override
	public String toString() {
		return "Rating [id=" + id + ", serviceId=" + serviceId + ", name=" + name + ", totalRating=" + totalRating
				+ ", comment=" + comment + ", createdOn=" + createdOn + ", modifiedOn=" + modifiedOn + "]";
	}

}
