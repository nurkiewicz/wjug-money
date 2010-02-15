package com.google.groups.warszawajug.money;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Locale;

/**
 * @author Tomasz Nurkiewicz
 */
public class Bank implements Serializable {

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

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).
				append("name", name).
				append("country", country.getDisplayCountry(Locale.US)).
				toString();
	}
}
