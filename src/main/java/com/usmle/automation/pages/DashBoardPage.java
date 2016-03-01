package com.usmle.automation.pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ascendlearning.automation.ui.config.PropertiesRepository;
import com.ascendlearning.automation.ui.handlers.BaseHandler;
import com.ascendlearning.automation.ui.handlers.LinkHandler;

public class DashBoardPage extends USMLEBasePage {

	private Logger logger = LogManager.getLogger(this.getClass());
	boolean isDisplayed = false;

	BaseHandler baseHandler = new BaseHandler(driver);
	LinkHandler linkHandler = new LinkHandler(driver);

	public DashBoardPage(WebDriver webDriver) {
		super(webDriver);
	}

	/**
	 * Verify the presence of Review My Performance section
	 */
	public boolean isReviewMyPerformanceSectionDisplayed() {
		try {
			WebElement we = driver
					.findElement(By.cssSelector(PropertiesRepository.getString("usmle.dashboard.performance")));
			isDisplayed = baseHandler.isDisplayed(we);
		} catch (Exception e) {
			logger.error("Unable to find Review My Performance section", e);
		}
		return isDisplayed;
	}

	/**
	 * Verify the headers on dashboard page. It's a generic method to verify
	 * headers on dashboard page.
	 */
	public String verifyHeaders(String locator) {
		String actualHeader = null;
		try {
			WebElement we = driver.findElement(By.cssSelector(PropertiesRepository.getString(locator)));
			actualHeader = we.getText();
		} catch (Exception e) {
			logger.error("Unable to validate header", e);
		}
		return actualHeader;
	}

	/**
	 * Verify whether Assessment Count displayed is correct or not
	 */
	public String verifyAssessmentCount() {
		String assessmentCount = null;
		try {
			WebElement we = driver
					.findElement(By.cssSelector(PropertiesRepository.getString("usmle.dashboard.assessment")));
			setDriverWait(PropertiesRepository.getString("usmle.dashboard.assessment"));
			assessmentCount = we.getText();
		} catch (Exception e) {
			logger.error("Unable to validate Assessment value", e);
		}
		return assessmentCount;
	}

	/**
	 * Verify whether Practice Count displayed is correct or not
	 */
	public String verifyPracticeCount() {
		String practiceCount = null;
		try {
			setDriverWait(PropertiesRepository.getString("usmle.dashboard.practice"));
			WebElement we = driver
					.findElement(By.cssSelector(PropertiesRepository.getString("usmle.dashboard.practice")));
			practiceCount = we.getText();
		} catch (Exception e) {
			logger.error("Unable to validate Practice value", e);
		}
		return practiceCount;
	}

	/**
	 * Verify whether Question Count displayed is correct or not
	 */
	public String verifyQuestionCount() {
		String questionCount = null;
		try {
			WebElement we = driver
					.findElement(By.cssSelector(PropertiesRepository.getString("usmle.dashboard.questions")));
			questionCount = we.getText();
		} catch (Exception e) {
			logger.error("Unable to validate Question value", e);
		}
		return questionCount;
	}

	/**
	 * Verify the correct answer displayed in Last Test section
	 */
	public String verifyLastTestCorrectCount() {
		String correctCount = null;
		try {
			WebElement correct = driver
					.findElement(By.cssSelector(PropertiesRepository.getString("usmle.dashboard.lasttest.correct")));
			correctCount = correct.getText();
		} catch (Exception e) {
			logger.error("Unable to check correct answer value in Last test", e);
		}
		return correctCount;
	}

	/**
	 * Verify the incorrect answer displayed in Last Test section
	 */
	public String verifyLastTestIncorrectCount() {
		String incorrectCount = null;
		try {
			WebElement incorrect = driver
					.findElement(By.cssSelector(PropertiesRepository.getString("usmle.dashboard.lasttest.incorrect")));
			incorrectCount = incorrect.getText();
		} catch (Exception e) {
			logger.error("Unable to check incorrect answer value in Last test", e);
		}
		return incorrectCount;
	}

	/**
	 * Verify the taken questions in QBank section
	 */
	public String verifyTakenQuestionsQBank() {
		String taken = null;
		try {
			WebElement takenQuestion = driver
					.findElement(By.cssSelector(PropertiesRepository.getString("usmle.dashboard.qbank.taken")));
			taken = takenQuestion.getText();
		} catch (Exception e) {
			logger.error("Unable to check taken questions value in QBank", e);
		}
		return taken;
	}

	/**
	 * Verify the remaining questions displayed in QBank section
	 */
	public String verifyRemainingQuestionsQBank() {
		String remaining = null;
		try {
			WebElement remainingQuestion = driver
					.findElement(By.cssSelector(PropertiesRepository.getString("usmle.dashboard.qbank.remaining")));
			remaining = remainingQuestion.getText();
		} catch (Exception e) {
			logger.error("Unable to check remaining question value inQBank", e);
		}
		return remaining;
	}

	/**
	 * Click on "Create New Practice Test" link
	 */
	public CreateNewPracticeTest openCreateNewPracticeTest() {
		try {
			linkHandler.selectLink(PropertiesRepository.getString("usmle.dashboard.link.practicetest"),
					PropertiesRepository.getString("usmle.practicetest.checkbox.allsubjects"));
		} catch (Exception e) {
			logger.error("Unable to click on link Create_New_Practice_Test", e);
		}
		return new CreateNewPracticeTest(driver);
	}
	
	/**
	 * Verify the opening of Dashboard page
	 */
	public boolean isDashboardPageDisplayed() {
		try {
			WebElement we = driver
					.findElement(By.cssSelector(PropertiesRepository.getString("usmle.dashboard.header")));
			isDisplayed = baseHandler.isDisplayed(we);
		} catch (Exception e) {
			logger.error("Unable to open Dashboard page", e);
		}
		return isDisplayed;
	}
	
	/**
	 * Verify the presence of Review Practice Tests button
	 */
	public boolean isReviewPracticeTestsButtonDisplayed() {
		try {
			WebElement we = driver
					.findElement(By.cssSelector(PropertiesRepository.getString("usmle.dashboard.link.reviewpracticetests")));
			isDisplayed = baseHandler.isDisplayed(we);
		} catch (Exception e) {
			logger.error("Unable to find Review Practice Tests button", e);
		}
		return isDisplayed;
	}

	/**
	 * Click on "REVIEW PRACTICE TESTS" button
	 */
	public UserReports clickReviewPracticeTestsButton() {
		try {
			linkHandler.selectLink(PropertiesRepository.getString("usmle.dashboard.link.reviewpracticetests"));
			setDriverWait(PropertiesRepository.getString("usmle.userreports.barchart"));
		} catch (Exception e) {
			logger.error("Unable to click on Review Practice Test link", e);
		}
		return new UserReports(driver);
	}
	/**
	 * Click on "COMPLETE ASSESSMENT TEST" button
	 */
	public AssessmentTestPage clickCompleteAssessmentTestButton() {
		try {
			linkHandler.selectLink(PropertiesRepository.getString("usmle.dashboard.link.completeassessmenttest"));
			setDriverWait(PropertiesRepository.getString("usmle.assessment.testpopup"));
			Thread.sleep(1500);
		} catch (Exception e) {
			logger.error("Unable to click on Complete Assessment Test link", e);
		}
		return new AssessmentTestPage(driver);
	}	
	
	/**
	 * Verify the updated practice test count after submitting each practice test
	 */
	public String getUpdatedPracticeCount(String Pcount) {
		String UpdatedPcount = null;
		try {
			int Pcount1 = Integer.parseInt(Pcount)+1;
			UpdatedPcount = String.valueOf(Pcount1);
		} catch (Exception e) {
			logger.error("Unable to get Updated Practice Count", e);
		}
		return UpdatedPcount;
	}
	
	/**
	 * Click on "Close" Link
	 */
	public void clickClose() {
		try {
			linkHandler.selectLink(PropertiesRepository.getString("usmle.dashboard.link.close"));
		} catch (Exception e) {
			logger.error("Unable to click on Close Link", e);
		}
	}
	
	/**
	 * Click on "REVIEW MY ASSESSMENT TESTS" button
	 */
	public UserReports clickReviewAssessmentButton() {
		try {
			linkHandler.selectLink(PropertiesRepository.getString("usmle.dashboard.link.reviewassessmenttests"));
			setDriverWait(PropertiesRepository.getString("usmle.userreports.barchart"));
		} catch (Exception e) {
			logger.error("Unable to click on Review Assessment Test link", e);
		}
		return new UserReports(driver);
	}
}