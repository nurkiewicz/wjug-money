package com.google.groups.warszawajug.money;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Tomasz Nurkiewicz
 */
public class MoneyTransfer implements Serializable {

	private String accountNo;
	private BigDecimal amount;
	private AccountOwner accountOwner;
	private Bank ownerBank;

	public MoneyTransfer() {
	}

	public MoneyTransfer(String accountNo, BigDecimal amount) {
		this.accountNo = accountNo;
		this.amount = amount;
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

	public AccountOwner getAccountOwner() {
		return accountOwner;
	}

	public void setAccountOwner(AccountOwner accountOwner) {
		this.accountOwner = accountOwner;
	}

	public Bank getOwnerBank() {
		return ownerBank;
	}

	public void setOwnerBank(Bank ownerBank) {
		this.ownerBank = ownerBank;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).
				append("accountNo", accountNo).
				append("amount", amount).
				append("accountOwner", accountOwner).
				append("ownerBank", ownerBank).
				toString();
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(!(o instanceof MoneyTransfer)) return false;

		MoneyTransfer that = (MoneyTransfer) o;

		if(accountNo != null ? !accountNo.equals(that.accountNo) : that.accountNo != null) return false;
		if(accountOwner != null ? !accountOwner.equals(that.accountOwner) : that.accountOwner != null) return false;
		if(amount != null ? amount.compareTo(that.amount) != 0 : that.amount != null) return false;
		if(ownerBank != null ? !ownerBank.equals(that.ownerBank) : that.ownerBank != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = accountNo != null ? accountNo.hashCode() : 0;
		result = 31 * result + (amount != null ? amount.hashCode() : 0);
		result = 31 * result + (accountOwner != null ? accountOwner.hashCode() : 0);
		result = 31 * result + (ownerBank != null ? ownerBank.hashCode() : 0);
		return result;
	}
}
