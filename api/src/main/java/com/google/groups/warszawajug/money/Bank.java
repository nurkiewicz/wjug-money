package com.google.groups.warszawajug.money;

import java.util.Locale;

/**
 * @author Tomasz Nurkiewicz
 */
public class Bank {

	private final String name;
	private final Locale country;

	public Bank(String name, Locale country) {
		this.name = name;
		this.country = country;
	}

	public String getName() {
		return name;
	}

	public Locale getCountry() {
		return country;
	}

}
