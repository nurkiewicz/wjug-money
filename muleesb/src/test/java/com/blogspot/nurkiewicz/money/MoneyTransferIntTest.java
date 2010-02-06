package com.blogspot.nurkiewicz.money;

import java.io.File;
import java.net.URL;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.time.FastDateFormat;
import org.mule.module.client.MuleClient;
import org.mule.tck.FunctionalTestCase;
import org.subethamail.wiser.Wiser;
import org.subethamail.wiser.WiserMessage;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author Tomasz Nurkiewicz
 * @since 2010-01-03, 15:31:50
 */
public class MoneyTransferIntTest extends FunctionalTestCase {

	private Wiser wiser;
	private static final int SMTP_PORT = 60725;
	private static final String EMPTY_XLSX = "com/blogspot/nurkiewicz/money/empty.xlsx";

	@Override
	protected String getConfigResources() {
		return "mule-config.xml";
	}

	@Override
	protected void doSetUp() throws Exception {
		wiser = new Wiser();
		wiser.setPort(SMTP_PORT);
		wiser.start();
	}

	@Override
	protected void doTearDown() throws Exception {
		wiser.stop();
	}

	public void testEmailSending() throws Exception {
		//given
		final MuleClient client = new MuleClient();
		final String content = FastDateFormat.getDateTimeInstance(FastDateFormat.FULL, FastDateFormat.FULL, Locale.ENGLISH).format(Calendar.getInstance());

		//when
		client.send("vm://moneyTransfer", content, null);
		Thread.sleep(500);

		//then
		final List<WiserMessage> mailMessages = wiser.getMessages();
		assertThat(mailMessages.size(), equalTo(1));
		final WiserMessage message = mailMessages.get(0);
		assertThat(message.getMimeMessage().getSubject(), equalTo("Money transfer"));
		Thread.sleep(500);
	}

	public void testShouldProcessEmptyFile() throws Exception {
		//given
		final File inputDir = new File("target/mule/input");
		inputDir.mkdir();
		FileUtils.cleanDirectory(inputDir);
		assertThat(inputDir.listFiles().length, equalTo(0));

		final File processedDir = new File("target/mule/processed");
		processedDir.mkdir();
		FileUtils.cleanDirectory(processedDir);
		assertThat(processedDir.listFiles().length, equalTo(0));

		final URL emptyXslsFileUrl = getClass().getClassLoader().getResource(EMPTY_XLSX);
		assertThat(emptyXslsFileUrl, notNullValue());
		final File emptyXslxFile = new File(emptyXslsFileUrl.toURI());
		FileUtils.copyFile(emptyXslxFile, new File(inputDir, EMPTY_XLSX));
		assertThat(inputDir.listFiles().length, equalTo(1));
		assertThat(inputDir.listFiles()[0].getName(), equalTo(EMPTY_XLSX));


		//when
		Thread.sleep(5000);

		//then
		assertThat(inputDir.listFiles().length, equalTo(0));
		assertThat(processedDir.listFiles().length, equalTo(1));
		assertThat(processedDir.listFiles()[0].getName(), equalTo("com/blogspot/nurkiewicz/money/empty.xlsx"));

	}

}
