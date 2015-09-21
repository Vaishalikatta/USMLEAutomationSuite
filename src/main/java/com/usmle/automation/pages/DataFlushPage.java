package com.usmle.automation.pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.ascendlearning.automation.ui.config.PropertiesRepository;
import com.ascendlearning.automation.ui.exceptions.DriverException;
import com.ascendlearning.automation.ui.handlers.DropDownHandler;
import com.ascendlearning.automation.ui.handlers.LinkHandler;
import com.ascendlearning.automation.ui.handlers.TextHandler;
import com.ascendlearning.automation.ui.page.BasePage;

public class DataFlushPage extends BasePage {

	private Logger logger = LogManager.getLogger(this.getClass());

	TextHandler textHandler = new TextHandler(driver);
	LinkHandler linkHandler = new LinkHandler(driver);
	DropDownHandler dropdownHandler = new DropDownHandler(driver);

	public DataFlushPage(WebDriver webDriver) {
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
	public void loginToUsmleDataFlushApplicationPage() {

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
	}
	/*
	 * Flush USMLE Data
	 */
	public void dataFlush() {

		// Selecting Data flush control from dropdown
		try {
			dropdownHandler.selectByVisibleText(
					dropdownHandler.getDropDown(PropertiesRepository.getString("usmle.dataflush.dropdown.select")),
					PropertiesRepository.getString("usmle.dataflush.dropdown.value"),
					PropertiesRepository.getString("usmle.dataflush.dropdown.select.waitfor"));
		} catch (DriverException e) {
			logger.error("Unable to select value from dropdown", e);
		}
		try {
			textHandler.writeText(PropertiesRepository.getString("usmle.dataflush.textfield.isbncode"),
					PropertiesRepository.getString("usmle.dataflush.textfield.value.isbncode"));
		} catch (DriverException e) {
			logger.error("Unable to enter isbncode", e);
		}
		try {
			linkHandler.selectLink(PropertiesRepository.getString("usmle.dataflush.button.load"),
					PropertiesRepository.getString("usmle.dataflush.button.load.waitfor"));
		} catch (DriverException e) {
			logger.error("Unable to open flush USMLE data", e);
		}
	}	
}