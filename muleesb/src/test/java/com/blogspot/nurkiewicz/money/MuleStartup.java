package com.blogspot.nurkiewicz.money;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.mule.api.MuleContext;
import org.mule.api.config.ConfigurationBuilder;
import org.mule.config.spring.SpringXmlConfigurationBuilder;
import org.mule.context.DefaultMuleContextFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Tomasz Nurkiewicz
 * @since 2010-01-05, 18:48:46
 */
public class MuleStartup {

	private static final Logger log = LoggerFactory.getLogger(MuleStartup.class);

	public static void main(String[] args) {
		try {
			final ConfigurationBuilder configurationBuilder = new SpringXmlConfigurationBuilder(new String[] {"mule-config.xml", "fakes-mule-config.xml"});
			MuleContext context = new DefaultMuleContextFactory().createMuleContext(configurationBuilder);
			context.start();
		} catch (Exception e) {
			log.error("Mule ESB startup failed", e);
		}
	}

}
