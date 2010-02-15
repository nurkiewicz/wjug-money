package com.google.groups.warszawajug.money;

import javax.ejb.Remote;

/**
 * @author Tomasz Nurkiewicz
 * @since 2010-02-14, 21:14:03
 */
public interface BankInfoService {

	Bank getBankInfoByAccountNo(String accountNo);

}
