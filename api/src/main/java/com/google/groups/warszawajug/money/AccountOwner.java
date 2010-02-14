package com.google.groups.warszawajug.money;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author Tomasz Nurkiewicz
 */
public class AccountOwner {

	private String name;
	private String country;
	private String city;
	private String street;
	private String postalCode;

	public AccountOwner() {
	}

	public AccountOwner(String name, String country, String city, String street, String postalCode) {
		this.name = name;
		this.country = country;
		this.city = city;
		this.street = street;
		this.postalCode = postalCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).
				append("name", name).
				append("country", country).
				append("city", city).
				append("street", street).
				append("postalCode", postalCode).
				toString();
	}
}
