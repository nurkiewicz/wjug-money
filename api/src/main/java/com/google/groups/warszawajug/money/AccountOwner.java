package com.google.groups.warszawajug.money;

import java.util.Calendar;

/**
 * @author Tomasz Nurkiewicz
 */
public class AccountOwner {

	private final String name;
	private final String country;
	private final String city;
	private final String street;
	private final String postalCode;

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

	public String getCountry() {
		return country;
	}

	public String getCity() {
		return city;
	}

	public String getStreet() {
		return street;
	}

	public String getPostalCode() {
		return postalCode;
	}
}
