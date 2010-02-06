package com.blogspot.nurkiewicz.money;

import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Tomasz Nurkiewicz
 * @since 6.0.13, 2010-01-08, 23:54:41
 */
public class CsvToTransferListTransformer extends AbstractTransformer {

	private static final Logger log = LoggerFactory.getLogger(CsvToTransferListTransformer.class);

	public CsvToTransferListTransformer() {
		registerSourceType(String.class);
		setReturnClass(List.class);
	}

	@Override
	protected List<MoneyTransfer> doTransform(Object src, String encoding) throws TransformerException {
		List<MoneyTransfer> list = new ArrayList<MoneyTransfer>();
		final Scanner scanner = new Scanner(new StringReader((String) src));
		while(scanner.hasNextLine()) {
			final MoneyTransfer moneyTransfer = process(scanner.nextLine());
			if(moneyTransfer != null)
				list.add(moneyTransfer);
		}
		return list;
	}

	private MoneyTransfer process(String line) {
		log.debug("Processing line: '{}'", line);
		final String[] parts = line.split(";");
		if(parts.length != 3) {
			log.info("Line does not contain three sections separated with semicolon");
			return null;
		}
		try {
			final String name = parts[0].trim();
			final String accountNo = parts[1].trim();
			final BigDecimal amount = new BigDecimal(parts[2].trim());
			return new MoneyTransfer(name, accountNo, amount);
		} catch(NumberFormatException e) {
			log.warn("Can't parse amount: '{}'", parts[2].trim());
			return null;
		}
	}
}
