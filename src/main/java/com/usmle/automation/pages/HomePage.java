package com.usmle.automation.pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.ascendlearning.automation.ui.config.PropertiesRepository;
import com.ascendlearning.automation.ui.exceptions.DriverException;
import com.ascendlearning.automation.ui.handlers.LinkHandler;
import com.ascendlearning.automation.ui.page.BasePage;

public class HomePage extends BasePage {

	private Logger logger = LogManager.getLogger(this.getClass());

	public HomePage(WebDriver webDriver) {
		super(webDriver);
	}

	LinkHandler linkHandler = new LinkHandler(driver);

	/*
	 * Opening of USMLE Dashboard Page
	 */
	public void openUSMLEDashboard() {
		try {
			linkHandler.selectLink(PropertiesRepository.getString("usmle.homepage.link.gototests"),
					PropertiesRepository.getString("usmle.homepage.link.gototests.waitfor"));
		} catch (DriverException e) {
			logger.error("Unable to open Home page", e);
		}

	}
}