package com.blogspot.nurkiewicz.money;

import com.google.groups.warszawajug.money.MoneyTransfer;
import org.junit.Before;
import org.junit.Test;
import org.mule.api.transformer.Transformer;
import org.mule.api.transformer.TransformerException;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;


/**
 * @author Tomasz Nurkiewicz
 * @since 2010-01-05, 22:01:33
 */
public class SpreadsheetToMoneyTransferListTransformerTest {

	private Transformer transformer;

	@Before
	public void setUp() throws Exception {
		transformer = new SpreadsheetToMoneyTransferListTransformer();
	}

	@Test(expected = TransformerException.class)
	public void shouldThrowWhenMalformedFile() throws Exception {
		//given
		final InputStream inputStream = getClass().getClassLoader().getResourceAsStream("logback.xml");

		//when
		transformer.transform(inputStream);

		//then throws
	}

	@Test
	public void shouldReturnEmptyListWhenParsingEmptyFile() throws Exception {
		//given
		InputStream inputStream = getClass().getResourceAsStream("empty.xls");

		//when
		List<MoneyTransfer> transferList = (List<MoneyTransfer>) transformer.transform(inputStream);

		//then
		assertThat(transferList).isEqualTo(Collections.<MoneyTransfer>emptyList());
	}

	@Test
	public void shouldReturnEmptyListWhenParsingFileWithOnlyHeaders() throws Exception {
		//given
		InputStream inputStream = getClass().getResourceAsStream("headers.xls");

		//when
		List<MoneyTransfer> transferList = (List<MoneyTransfer>) transformer.transform(inputStream);

		//then
		assertThat(transferList).isEqualTo(Collections.<MoneyTransfer>emptyList());
	}

	@Test
	public void shouldReturnSingleTransfer() throws Exception {
		//given
		InputStream inputStream = getClass().getResourceAsStream("single.xls");

		//when
		List<MoneyTransfer> transferList = (List<MoneyTransfer>) transformer.transform(inputStream);

		//then
		assertThat(transferList).hasSize(1);
		assertThat(transferList.get(0)).isEqualTo(new MoneyTransfer("Acme corp.", "40 8989 7878 6767 5656 5656", BigDecimal.valueOf(1250.90)));
	}

	@Test
	public void shouldReturnMultipleTransfer() throws Exception {
		//given
		InputStream inputStream = getClass().getResourceAsStream("multiple.xls");

		//when
		List<MoneyTransfer> transferList = (List<MoneyTransfer>) transformer.transform(inputStream);

		//then
		assertThat(transferList).hasSize(3);
		assertThat(transferList.get(0)).isEqualTo(new MoneyTransfer("Acme corp.", "56 6757 5646 4535 3424 2423", BigDecimal.valueOf(1250.90)));
		assertThat(transferList.get(1)).isEqualTo(new MoneyTransfer("Charity donation", "87 6666 4444 5555 8888 9999", BigDecimal.valueOf((double) 900)));
		assertThat(transferList.get(2)).isEqualTo(new MoneyTransfer("Example.com", "20 9988 6644 9876 4567 8767", BigDecimal.valueOf(1786.35)));
	}

}
