package com.blogspot.nurkiewicz.money;

import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

/**
 * @author Tomasz Nurkiewicz
 * @since 6.0.13, 2010-01-07, 21:20:38
 */
public class SimpleComponent implements Callable {

	private static final Logger log = LoggerFactory.getLogger(SimpleComponent.class);

	public void process(Object obj) {
		log.info("Object '{}' of type {}", obj, obj.getClass());
	}

	public Object onCall(MuleEventContext eventContext) throws Exception {
		process(eventContext.getMessage().getPayload());
		eventContext.transformMessage();
		process(eventContext.getMessage().getPayload());
//		eventContext.getMessageAsString();
		return eventContext.getMessage();
	}
}
