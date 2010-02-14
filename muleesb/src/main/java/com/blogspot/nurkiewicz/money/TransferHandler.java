package com.blogspot.nurkiewicz.money;

import com.google.groups.warszawajug.money.MoneyTransfer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Tomasz Nurkiewicz
 */
public class TransferHandler {

	private static final Logger log = LoggerFactory.getLogger(TransferHandler.class);

	public MoneyTransfer handle(MoneyTransfer transfer) {
		log.info("Handling transfer: {}", transfer);
		return transfer;
	}

}
