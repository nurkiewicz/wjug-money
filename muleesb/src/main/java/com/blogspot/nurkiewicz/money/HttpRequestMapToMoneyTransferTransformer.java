package com.blogspot.nurkiewicz.money;

import com.google.groups.warszawajug.money.MoneyTransfer;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractTransformer;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author Tomasz Nurkiewicz
 * @since 2010-02-18, 23:08:22
 */
public class HttpRequestMapToMoneyTransferTransformer extends AbstractTransformer{

	public HttpRequestMapToMoneyTransferTransformer() {
		registerSourceType(Map.class);
		setReturnClass(List.class);
	}

	@Override
	protected Object doTransform(Object src, String encoding) throws TransformerException {
		Map<String, String> parameters = (Map<String, String>) src;
		final MoneyTransfer moneyTransfer = new MoneyTransfer(parameters.get("accountNo"), new BigDecimal(parameters.get("amount")));
		return Collections.singletonList(moneyTransfer);
	}

}
