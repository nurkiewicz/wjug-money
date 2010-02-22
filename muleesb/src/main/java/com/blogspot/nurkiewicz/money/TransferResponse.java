package com.blogspot.nurkiewicz.money;

import com.google.groups.warszawajug.money.MoneyTransfer;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;

/**
 * @author Tomasz Nurkiewicz
 * @since 2010-02-21, 16:49:36
 */
public class TransferResponse implements Serializable {

	private boolean success;

	private MoneyTransfer moneyTransfer;

	public TransferResponse() {
	}

	public TransferResponse(boolean success) {
		this.success = success;
	}

	public TransferResponse(boolean success, MoneyTransfer moneyTransfer) {
		this.success = success;
		this.moneyTransfer = moneyTransfer;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public MoneyTransfer getMoneyTransfer() {
		return moneyTransfer;
	}

	public void setMoneyTransfer(MoneyTransfer moneyTransfer) {
		this.moneyTransfer = moneyTransfer;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).
				append("success", success).
				append("moneyTransfer", moneyTransfer).
				toString();
	}
}
