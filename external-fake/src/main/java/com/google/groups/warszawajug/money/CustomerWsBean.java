package com.google.groups.warszawajug.money;

import com.google.groups.warszawajug.money.AccountOwner;
import com.google.groups.warszawajug.money.CustomerWs;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

/**
 * @author Tomasz Nurkiewicz
 * @since 2010-02-14, 18:49:22
 */
@Stateless
@WebService
public class CustomerWsBean implements CustomerWs {

	@EJB
	private CustomerDaoLocal customerDaoLocal;

	@Override
	public AccountOwner getAccountOwnerByAccountNo(String accountNo) {
		return customerDaoLocal.getAccountOwnerByAccountNo(accountNo);
	}
}
