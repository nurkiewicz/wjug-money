package com.google.groups.warszawajug.money;

import javax.ejb.Remote;

/**
 * @author Tomasz Nurkiewicz
 * @since 2010-02-14, 21:14:03
 */
@Remote
public interface BankInfoRemote {

	Bank getBankInfoByAccountNo(String accountNo);

}
