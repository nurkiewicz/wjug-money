package com.blogspot.nurkiewicz.money;

import com.google.groups.warszawajug.money.MoneyTransfer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Tomasz Nurkiewicz
 * @since 2010-02-08, 20:19:28
 */
public class FakeMoneyTransferProcessor {

	private static final Logger log = LoggerFactory.getLogger(FakeMoneyTransferProcessor.class);

	private long delay = 1000;

	public TransferResponse process(MoneyTransfer moneyTransfer) {
		log.info("Processing: {}", moneyTransfer);
		try {
			Thread.sleep(delay);
		} catch(InterruptedException e) {
			log.warn("", e);
		}
		final boolean validTransfer = !moneyTransfer.getAccountNo().startsWith("99");
		final TransferResponse response = new TransferResponse(validTransfer, moneyTransfer);
		log.info("Processing done: {}", response);
		return response;
	}

	public void setDelay(long delay) {
		this.delay = delay;
	}
}
