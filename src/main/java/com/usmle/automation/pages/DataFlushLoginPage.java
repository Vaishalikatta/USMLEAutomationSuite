package com.usmle.automation.pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.ascendlearning.automation.ui.config.PropertiesRepository;
import com.ascendlearning.automation.ui.exceptions.DriverException;
import com.ascendlearning.automation.ui.handlers.LinkHandler;
import com.ascendlearning.automation.ui.handlers.TextHandler;
import com.ascendlearning.automation.ui.page.BasePage;

public class DataFlushLoginPage extends BasePage {

	private Logger logger = LogManager.getLogger(this.getClass());

	TextHandler textHandler = new TextHandler(driver);
	LinkHandler linkHandler = new LinkHandler(driver);

	public DataFlushLoginPage(WebDriver webDriver) {
		super(webDriver);
	}

	/*
	 * Opening of USMLE Dataflush Login page
	 */
	private void openUsmleDataFlushLoginPage() {
		driver.get(PropertiesRepository.getString("usmle.dataflush.loginpage.url"));
		setDriverWait(PropertiesRepository.getString("usmle.dataflush.button.login"));
	}

	/*
	 * Login to USMLE Data Flush application
	 */
	public DataFlushPage loginToUsmleDataFlushApplicationPage() {

		// Opening of USMLE Dataflush login page
		openUsmleDataFlushLoginPage();
		// Entering UserId and Password
		try {
			textHandler.writeText(PropertiesRepository.getString("usmle.dataflush.textfield.username"),
					PropertiesRepository.getString("usmle.dataflush.textfield.value.username"));

			textHandler.writeText(PropertiesRepository.getString("usmle.dataflush.textfield.password"),
					PropertiesRepository.getString("usmle.dataflush.textfield.value.password"));
		} catch (DriverException e) {
			logger.error("Unable to enter username and/or password", e);
		}
		try {
			linkHandler.selectLink(PropertiesRepository.getString("usmle.dataflush.button.login"),
					PropertiesRepository.getString("usmle.dataflush.button.login.waitfor"));
		} catch (DriverException e) {
			logger.error("Unable to open USMLE DataFlush Login page", e);
		}
		return new DataFlushPage(driver);
	}
}