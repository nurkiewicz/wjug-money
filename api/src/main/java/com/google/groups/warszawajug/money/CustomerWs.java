package com.google.groups.warszawajug.money;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @author Tomasz Nurkiewicz
 * @since 2010-02-14, 18:49:22
 */
@WebService
public interface CustomerWs {

	@WebMethod
	AccountOwner getAccountOwnerByAccountNo(@WebParam(name="accountNo") String accountNo);

}
