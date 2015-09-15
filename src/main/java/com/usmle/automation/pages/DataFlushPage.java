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

	LinkHandler linkHandler = new LinkHandler(driver);
	DropDownHandler dropdownHandler = new DropDownHandler(driver);
	TextHandler textHandler = new TextHandler(driver);

	public DataFlushPage(WebDriver webDriver) {
		super(webDriver);
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