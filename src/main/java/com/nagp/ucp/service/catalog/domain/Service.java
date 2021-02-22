package com.nagp.ucp.service.catalog.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

@Table
@Entity
public class Service {

	/**
	 * Unique identifier for a service
	 */
	@Id
	@NonNull
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	/**
	 * Name of Service in Catalog.
	 */
	@Column
	private String name;

	/**
	 * Is service Available for given location.
	 */
	@Column
	private boolean isAvailable;

	/**
	 * pincode at which a service is available.
	 */
	@Column
	private int pincode;

	/**
	 * pincode at which a service is available.
	 */
	@Column
	private String description;

	public Service() {

	}

	public Service(int id, String name, boolean isAvailable, int pincode, String description) {
		super();
		this.id = id;
		this.name = name;
		this.isAvailable = isAvailable;
		this.pincode = pincode;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Service [id=" + id + ", name=" + name + ", isAvailable=" + isAvailable + ", pincode=" + pincode
				+ ", description=" + description + "]";
	}

}
