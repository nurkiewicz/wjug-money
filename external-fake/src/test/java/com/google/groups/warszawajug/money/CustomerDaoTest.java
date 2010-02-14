package com.google.groups.warszawajug.money;

import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

import static org.fest.assertions.Assertions.assertThat;

/**
 * @author Tomasz Nurkiewicz
 * @since 2010-02-14, 23:01:57
 */
public class CustomerDaoTest {
	private CustomerDaoLocal customerDao;

	@Before
	public void setup() {
		CustomerDao customerDao = new CustomerDao();
		customerDao.populateData();
		this.customerDao = customerDao;
	}

	@Test
	public void shouldReturnBankOwner() throws Exception {
		//given
		final String accountNo = "10 2233 3344 4455 5566 6677 7788";

		//when
		final AccountOwner accountOwner = customerDao.getAccountOwnerByAccountNo(accountNo);

		//then
		assertThat(accountOwner).isNotNull();
		assertThat(accountOwner.getCountry()).isEqualTo("United Kingdom");
		assertThat(accountOwner.getCity()).isEqualTo("Glasgow");
		assertThat(accountOwner.getName()).isEqualTo("John Smith");
		assertThat(accountOwner.getStreet()).isEqualTo("George Square 10");
		assertThat(accountOwner.getPostalCode()).isEqualTo("66-992");
	}
}
