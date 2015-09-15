package com.usmle.automation.tests;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.ascendlearning.automation.ui.assertions.VerificationHandler;
import com.ascendlearning.automation.ui.test.BaseTest;
import com.usmle.automation.pages.DataFlushLoginPage;
import com.usmle.automation.pages.DataFlushPage;
import com.usmle.automation.pages.HomePage;
import com.usmle.automation.pages.LogonPage;

@Test
public class LogonTest extends BaseTest {

	Logger logger = LogManager.getLogger(this.getClass());

	/*
	 * Verifying whether Data flush is working fine or not
	 */

	@Test(groups = { "smoke" })
	public void openUSMLEPages() throws Exception {

		LogonPage logonPage = new LogonPage(driver);
		HomePage homePage = new HomePage(driver);
		DataFlushPage dataFlushPage = new DataFlushPage(driver);
		DataFlushLoginPage dataFlushLoginPage = new DataFlushLoginPage(driver);

		// Login to USMLE DataFlush Login Page
		dataFlushPage = dataFlushLoginPage.loginToUsmleDataFlushApplicationPage();
		// Flush the existing USMLE data
		dataFlushPage.dataFlush();
		// Login to USMLE application
		homePage = logonPage.loginToUsmleApplicationPage();
		// Verify whether the login is successful
		VerificationHandler.verifyTrue(logonPage.verifyLogin(), "Error has occured when trying to login to the application");
		// Navigating to Dashboard page
		homePage.openUSMLEDashboard();
	}
}