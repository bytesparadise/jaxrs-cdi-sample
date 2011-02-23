package org.bytesparadise.tools.jaxrs.sample.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

	private String street;

	private String city;

	private String zip;
	
	private String state;

	private String country;
	
	public Address() {
		super();
	}
	
	public Address(String street, String city, String zip, String state,
			String country) {
		super();
		this.street = street;
		this.city = city;
		this.zip = zip;
		this.state = state;
		this.country = country;
	}



	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getZip() {
		return zip;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}

}
