package com.usmle.automation.tests;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.ascendlearning.automation.ui.assertions.VerificationHandler;
import com.ascendlearning.automation.ui.config.PropertiesRepository;
import com.ascendlearning.automation.ui.test.BaseTest;
import com.usmle.automation.pages.DashBoardPage;
import com.usmle.automation.pages.DataFlushPage;
import com.usmle.automation.pages.DataFlushPage;
import com.usmle.automation.pages.HomePage;
import com.usmle.automation.pages.LogonPage;


public class LogonTest extends BaseTest {

	Logger logger = LogManager.getLogger(this.getClass());
	
	HomePage homePage;
	DataFlushPage dataFlushPage;
	DashBoardPage dashBoardPage;
	LogonPage logonPage;

	/*
	 * Pre-Requisite - User have already taken Practice & Assessment test in USMLE
	 * Test Scenario - Verify USMLE Dashboard after flushing data
	 * Test cases covered:
	 * 1.Validate user is able to login to USMLE Data flush application
	 * 2.Validate user is able flush the existing USMLE data
	 * 3.Validate user is able to login to USMLE application
	 * 4.Validate user is able to navigate to Dashboard from Home page
	 * 5.Validate below sections under Review My Performance Section
	 *   a.Assessment Test Count
	 *   b.Practise test Count
	 *   c.Question Count
	 *   d.Correct & Incorrect questions count in Last Test
	 *   e.Questions Taken & Remaining in QBank section
	 */

	@Test(groups = { "smoke" })
	public void verifyUSMLEDashboard() throws Exception {

		LogonPage logonPage = new LogonPage(driver);
		DataFlushPage dataFlushPage = new DataFlushPage(driver);

		// Login to USMLE DataFlush Login Page
		dataFlushPage.loginToUsmleDataFlushApplicationPage();
		// Flush the existing USMLE data
		dataFlushPage.dataFlush();
		// Login to USMLE application
		homePage = logonPage.loginToUsmleApplicationPage();
		// Verify whether the login is successful
		VerificationHandler.verifyTrue(logonPage.verifyLogin(), "Error has occured when trying to login to the application");
		// Navigating to Dashboard page
		homePage.openUSMLEDashboard();
		//Verifying Review My Performance section
		dashBoardPage.isReviewMyPerformanceSectionDisplayed();
		//Verify the headers in Review My Performance section
		String actualValue=dashBoardPage.verifyHeaders("usmle.dashboard.performanceheader");
		VerificationHandler.verifyEquals(actualValue, "Review My Performance");
		String actualValue1=dashBoardPage.verifyHeaders("usmle.dashboard.performancesubheader");
		System.out.println(actualValue1);
		VerificationHandler.verifyEquals(actualValue1, "Completed");
		//Verify Assessment test count
		String assessmentCount = dashBoardPage.verifyAssessmentCount();
		VerificationHandler.verifyEquals(assessmentCount, PropertiesRepository.getString("usmle.dashboard.value.assessmentcount"));
		//Verify Practice test count
		String practiseCount = dashBoardPage.verifyPracticeCount();
		VerificationHandler.verifyEquals(practiseCount, PropertiesRepository.getString("usmle.dashboard.value.practicecount"));
		//Verify Questions count
		String questionCount = dashBoardPage.verifyPracticeCount();
		VerificationHandler.verifyEquals(questionCount, PropertiesRepository.getString("usmle.dashboard.value.questioncount"));	
		//Verify Correct answer count in Last Test
		String correctAnswer = dashBoardPage.verifyLastTestCorrectCount();
		VerificationHandler.verifyEquals(correctAnswer, PropertiesRepository.getString("usmle.dashboard.value.lasttest.correct"));	
		//Verify Incorrect answer count in Last Test
		String incorrectAnswer = dashBoardPage.verifyLastTestIncorrectCount();
		VerificationHandler.verifyEquals(incorrectAnswer, PropertiesRepository.getString("usmle.dashboard.value.lasttest.incorrect"));			
		//Verify Taken question count in QBank section
		String takenQuestion = dashBoardPage.verifyTakenQuestionsQBank();
		VerificationHandler.verifyEquals(takenQuestion, PropertiesRepository.getString("usmle.dashboard.value.qbank.taken"));	
		//Verify Remaining question count in QBank section
		String remainingQuestion = dashBoardPage.verifyRemainingQuestionsQBank();
		VerificationHandler.verifyEquals(remainingQuestion, PropertiesRepository.getString("usmle.dashboard.value.qbank.remaining"));			
	}
}