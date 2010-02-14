package com.blogspot.nurkiewicz.money;

import com.google.groups.warszawajug.money.AccountOwner;
import com.google.groups.warszawajug.money.BankInfoRemote;
import com.google.groups.warszawajug.money.CustomerWs;
import com.google.groups.warszawajug.money.MoneyTransfer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Tomasz Nurkiewicz
 */
public class TransferHandler {

	private static final Logger log = LoggerFactory.getLogger(TransferHandler.class);

	private CustomerWs customerWs;
	private BankInfoRemote bankInfo;

	public MoneyTransfer handle(MoneyTransfer transfer) {
		log.info("Handling transfer: {}", transfer);
		final AccountOwner accountOwner = customerWs.getAccountOwnerByAccountNo(transfer.getAccountNo());
		log.debug("Account owner: {}", accountOwner);
		transfer.setAccountOwner(accountOwner);

		return transfer;
	}

	public void setCustomerWs(CustomerWs customerWs) {
		this.customerWs = customerWs;
	}
}
