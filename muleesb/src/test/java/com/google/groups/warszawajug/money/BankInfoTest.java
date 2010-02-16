package com.google.groups.warszawajug.money;

import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * @author Tomasz Nurkiewicz
 * @since 2010-02-14, 21:19:32
 */
public class BankInfoTest {

	private BankInfoService bankInfo;

	@Before
	public void setup() {
		bankInfo = new BankInfo();
	}

	@Test
	public void shouldReturnSwissBank() throws Exception {
		//given
		final String accountNo = "12 2233 3344 4455 5566 6677 7788";

		//when
		final Bank bank = bankInfo.getBankInfoByAccountNo(accountNo);

		//then
		assertThat(bank).isNotNull();
		assertThat(bank.getCountry()).isEqualTo("Switzerland");
	}

	@Test
	public void shouldReturnAmericanBank() throws Exception {
		//given
		final String accountNo = "60 2233 3344 4455 5566 6677 7788";

		//when
		final Bank bank = bankInfo.getBankInfoByAccountNo(accountNo);

		//then
		assertThat(bank).isNotNull();
		assertThat(bank.getCountry()).isEqualTo("USA");
	}

}
