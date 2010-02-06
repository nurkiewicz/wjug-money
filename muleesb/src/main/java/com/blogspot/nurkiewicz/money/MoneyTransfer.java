package com.blogspot.nurkiewicz.money;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author Tomasz Nurkiewicz
 * @since 2010-01-05, 20:25:00
 */
public class MoneyTransfer implements Serializable {

	private String name;
	private String accountNo;
	private BigDecimal amount;

	public MoneyTransfer() {
	}

	public MoneyTransfer(String name, String accountNo, BigDecimal amount) {
		this.name = name;
		this.accountNo = accountNo;
		this.amount = amount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).
				append("name", name).
				append("accountNo", accountNo).
				append("amount", amount).
				toString();
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(!(o instanceof MoneyTransfer)) return false;

		MoneyTransfer that = (MoneyTransfer) o;

		if(accountNo != null ? !accountNo.equals(that.accountNo) : that.accountNo != null) return false;
		if(amount != null ? amount.compareTo(that.amount) != 0 : that.amount != null) return false;
		if(name != null ? !name.equals(that.name) : that.name != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = name != null ? name.hashCode() : 0;
		result = 31 * result + (accountNo != null ? accountNo.hashCode() : 0);
		result = 31 * result + (amount != null ? amount.hashCode() : 0);
		return result;
	}
}
