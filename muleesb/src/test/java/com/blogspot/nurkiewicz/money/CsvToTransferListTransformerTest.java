package com.blogspot.nurkiewicz.money;

import com.google.groups.warszawajug.money.MoneyTransfer;
import org.junit.Before;
import org.junit.Test;
import org.mule.api.transformer.Transformer;

import java.math.BigDecimal;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;


/**
 * @author Tomasz Nurkiewicz
 * @since 6.0.13, 2010-01-09, 10:35:16
 */
public class CsvToTransferListTransformerTest {
	private Transformer transformer;

	@Before
	public void setUp() throws Exception {
		transformer = new CsvToTransferListTransformer();
	}

	@Test
	public void shouldReturnEmptyListWhenEmptyString() throws Exception {
		//given
		String input = "";

		//when
		List<MoneyTransfer> list = (List<MoneyTransfer>) transformer.transform(input);

		//then
		assertThat(list).isEmpty();
	}

	@Test
	public void shouldReturnEmptyListWhenArbitraryString() throws Exception {
		//given
		String input = "Best regards,";

		List<MoneyTransfer> list = (List<MoneyTransfer>) transformer.transform(input);

		//then
		assertThat(list).isEmpty();
	}

	@Test
	public void shouldReturnEmptyListWhenMalformedInput() throws Exception {
		//given
		String input = "Name;1234 1234;234;0";

		List<MoneyTransfer> list = (List<MoneyTransfer>) transformer.transform(input);

		//then
		assertThat(list).isEmpty();
	}

	@Test
	public void shouldReturnEmptyListWhenBadAmountValue() throws Exception {
		//given
		String input = "Name;1234 1234;XYZ";

		List<MoneyTransfer> list = (List<MoneyTransfer>) transformer.transform(input);

		//then
		assertThat(list).isEmpty();
	}

	@Test
	public void shouldReturnTransferWhenProperInput() throws Exception {
		//given
		String input = "Name;1234 1234;123.45";

		List<MoneyTransfer> list = (List<MoneyTransfer>) transformer.transform(input);

		//then
		assertThat(list.size()).isEqualTo(1);
		assertThat(list.get(0)).isEqualTo(new MoneyTransfer("Name", "1234 1234", BigDecimal.valueOf(123.45)));
	}

	@Test
	public void shouldSkipEmptyLines() throws Exception {
		//given
		String input = "\n\nName;1234 1234;123.45\n\n";

		List<MoneyTransfer> list = (List<MoneyTransfer>) transformer.transform(input);

		//then
		assertThat(list.size()).isEqualTo(1);
		assertThat(list.get(0)).isEqualTo(new MoneyTransfer("Name", "1234 1234", BigDecimal.valueOf(123.45)));
	}

	@Test
	public void shouldReturnMultipleTransfers() throws Exception {
		//given
		String input = "\nName 1;1234 4567;123.45\nName 2;3456 5432;12.70\n\nName 3;9988 6677;76.49";

		List<MoneyTransfer> list = (List<MoneyTransfer>) transformer.transform(input);

		//then
		assertThat(list.size()).isEqualTo(3);
		assertThat(list.get(0)).isEqualTo(new MoneyTransfer("Name 1", "1234 4567", BigDecimal.valueOf(123.45)));
		assertThat(list.get(1)).isEqualTo(new MoneyTransfer("Name 2", "3456 5432", BigDecimal.valueOf(12.70)));
		assertThat(list.get(2)).isEqualTo(new MoneyTransfer("Name 3", "9988 6677", BigDecimal.valueOf(76.49)));
	}

	@Test
	public void shouldTrimSections() throws Exception {
		//given
		String input = "Name 5   \t ; 1234 9955;\t 98.56";

		List<MoneyTransfer> list = (List<MoneyTransfer>) transformer.transform(input);

		//then
		assertThat(list.size()).isEqualTo(1);
		assertThat(list.get(0)).isEqualTo(new MoneyTransfer("Name 5", "1234 9955", BigDecimal.valueOf(98.56)));
	}

}
