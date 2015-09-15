package com.usmle.automation.pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.ascendlearning.automation.ui.config.PropertiesRepository;
import com.ascendlearning.automation.ui.exceptions.DriverException;
import com.ascendlearning.automation.ui.handlers.LinkHandler;
import com.ascendlearning.automation.ui.handlers.MenuHandler;
import com.ascendlearning.automation.ui.handlers.TextHandler;
import com.ascendlearning.automation.ui.page.BasePage;

public class LogonPage extends BasePage {

	private Logger logger = LogManager.getLogger(this.getClass());

	public LogonPage(WebDriver webDriver) {
		super(webDriver);
	}

	TextHandler textHandler = new TextHandler(driver);
	MenuHandler menuHandlder = new MenuHandler(driver);
	LinkHandler linkHandler = new LinkHandler(driver);

	/*
	 * Opening of USMLE Logon page
	 */
	private void openUsmleLogonPage() {
		driver.get(PropertiesRepository.getString("usmle.logonpage.url"));
		setDriverWait(PropertiesRepository.getString("usmle.logonpage.url.waitfor"));
	}

	/*
	 * Login to USMLE application
	 */
	public HomePage loginToUsmleApplicationPage() {
		// OPening of USMLE application
		openUsmleLogonPage();
		// Entering UserId and Password
		try {
			textHandler.writeText(PropertiesRepository.getString("usmle.logonpage.textfield.username"),
					PropertiesRepository.getString("usmle.logonpage.textfield.value.username"));

			textHandler.writeText(PropertiesRepository.getString("usmle.logonpage.textfield.password"),
					PropertiesRepository.getString("usmle.logonpage.textfield.value.password"));
		} catch (DriverException e) {
			logger.error("Unable to enter username and/or password", e);
		}
		try {
			linkHandler.selectLink(PropertiesRepository.getString("usmle.logonpage.button.login"),
					PropertiesRepository.getString("usmle.logonpage.button.login.waitfor"));
		} catch (DriverException e) {
			logger.error("Unable to login to USMLE application", e);
		}
		return new HomePage(driver);
	}

	/*
	 * Verifying whether user have logged in successfully
	 */
	public boolean verifyLogin() throws DriverException {
		boolean isDisplayed = false;
		try {
			isDisplayed = menuHandlder.getMenuItem(PropertiesRepository.getString("usmle.homepage.title")).isDisplayed();
			logger.info("Login to USMLE application is successful");
		} catch (DriverException e) {
			logger.error(e.getLocalizedMessage(), e);
		}
		return isDisplayed;
	}
}