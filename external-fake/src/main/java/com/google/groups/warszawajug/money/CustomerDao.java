package com.google.groups.warszawajug.money;

import com.google.groups.warszawajug.money.AccountOwner;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author Tomasz Nurkiewicz
 * @since 2010-02-14, 20:15:30
 */
@Stateless
public class CustomerDao implements CustomerDaoLocal {

	private final Map<String, AccountOwner> accountPrefixToOwner = new HashMap<String, AccountOwner>();

	@PostConstruct
	public void populateData() {
		accountPrefixToOwner.put("0", new AccountOwner("Registered Corp.", Locale.UK.getDisplayCountry(Locale.US), "Birmingham", "Jennens Rd 128", "63-745"));
		accountPrefixToOwner.put("1", new AccountOwner("John Smith", Locale.UK.getDisplayCountry(Locale.US), "Glasgow", "George Square 10", "66-992"));
		accountPrefixToOwner.put("2", new AccountOwner("Hans Moore", Locale.GERMANY.getDisplayCountry(Locale.US), "Dortmund", "Wilhelmstrasse 76", "08-984"));
		accountPrefixToOwner.put("3", new AccountOwner("Maria Vincenza", Locale.ITALIAN.getDisplayCountry(Locale.US), "Palermo", "Via Vittorio Emanuele", "65-335"));
		accountPrefixToOwner.put("4", new AccountOwner("Francois Lappe", Locale.FRANCE.getDisplayCountry(Locale.US), "Rennes", "Rue Gambetta 87", "86-991"));
		accountPrefixToOwner.put("5", new AccountOwner("Jan Kowalski", new Locale("pl", "PL").getDisplayCountry(Locale.US), "Wroclaw", "Kazimierza Wielkiego 34", "50-077"));
		accountPrefixToOwner.put("6", new AccountOwner("GreatSoft Inc.", Locale.US.getDisplayCountry(Locale.US), "San Francisco", "Golden Gate Ave 5", "99-865"));
		accountPrefixToOwner.put("7", new AccountOwner("Charity org.", Locale.US.getDisplayCountry(Locale.US), "Washington D.C.", "NW 54th St. 95", "11-294"));
		accountPrefixToOwner.put("8", new AccountOwner("Anna Nowak", new Locale("pl", "PL").getDisplayCountry(Locale.US), "Szczecin", "Plac Grunwaldzki 7", "70-001"));
	}

	@Override
	public AccountOwner getAccountOwnerByAccountNo(String accountNo) {
		if(accountNo.length() < 26)
			throw new IllegalArgumentException("Account no too short: '" + accountNo + "'");
		final AccountOwner owner = accountPrefixToOwner.get(String.valueOf(accountNo.charAt(0)));
		if(owner == null) {
			throw new IllegalArgumentException("Owner not found for account: '" + accountNo + "'");
		}
		return owner;
	}
}
