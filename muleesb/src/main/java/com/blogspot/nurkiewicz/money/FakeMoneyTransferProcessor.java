package com.blogspot.nurkiewicz.money;

import com.google.groups.warszawajug.money.MoneyTransfer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Tomasz Nurkiewicz
 * @since 6.3.0 (CR-7763), 2010-02-08, 20:19:28
 */
public class FakeMoneyTransferProcessor {

	private static final Logger log = LoggerFactory.getLogger(FakeMoneyTransferProcessor.class);

	private long delay = 1000;

	public Object process(MoneyTransfer moneyTransfer) {
		log.info("Processing: {}", moneyTransfer);
		try {
			Thread.sleep(delay);
		} catch(InterruptedException e) {
			log.warn("", e);
		}
		log.info("Processing done: {}", moneyTransfer);
		if(moneyTransfer.getAccountNo().startsWith("99"))
			return "Invalid account no: " + moneyTransfer.getAccountNo();
		return moneyTransfer;
	}

	public void setDelay(long delay) {
		this.delay = delay;
	}
}
