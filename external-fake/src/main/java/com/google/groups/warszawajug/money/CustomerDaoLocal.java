package com.google.groups.warszawajug.money;

import com.google.groups.warszawajug.money.AccountOwner;

import javax.ejb.Local;

/**
 * @author Tomasz Nurkiewicz
 * @since 2010-02-14, 20:13:56
 */
@Local
public interface CustomerDaoLocal {

	AccountOwner getAccountOwnerByAccountNo(String accountNo);

}
