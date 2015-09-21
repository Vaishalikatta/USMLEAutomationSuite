package com.usmle.automation.pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ascendlearning.automation.ui.config.PropertiesRepository;
import com.ascendlearning.automation.ui.handlers.BaseHandler;
import com.ascendlearning.automation.ui.page.BasePage;

public class DashBoardPage extends BasePage {

	private Logger logger = LogManager.getLogger(this.getClass());
	boolean isDisplayed=false;

	BaseHandler baseHandler = new BaseHandler(driver);
	
	public DashBoardPage(WebDriver webDriver) {
		super(webDriver);
	}
	
	/*
	 * Verify the presence of Review My Performance section 
	 */
	public boolean isReviewMyPerformanceSectionDisplayed(){
		try
		{
			WebElement we = driver.findElement(By.cssSelector(PropertiesRepository.getString("usmle.dashboard.performance")));
			isDisplayed=baseHandler.isDisplayed(we);
		}
		catch(Exception e)
		{
			logger.error("Unable to find Review My Performance section", e);
		}
		return isDisplayed;
	}
	/*
	 * Verify the headers on dashboard page. It's a generic method to verify headers on dashboard page.
	 */
	public String verifyHeaders(String locator){
		String actualHeader = null;
		try
		{
			WebElement we = driver.findElement(By.cssSelector(PropertiesRepository.getString(locator)));
			actualHeader = we.getText();
		}
		catch(Exception e)
		{
			logger.error("Unable to validate header", e);
		}
		return actualHeader;
	}		
	/*
	 * Verify whether Assessment Count displayed is correct or not 
	 */
	public String verifyAssessmentCount(){
		String assessmentCount=null;
		try
		{
			WebElement we = driver.findElement(By.cssSelector(PropertiesRepository.getString("usmle.dashboard.assessment")));
			assessmentCount= we.getText();
		}
		catch(Exception e)
		{
			logger.error("Unable to validate Assessment value", e);
		}
		return assessmentCount;
	}	
	/*
	 * Verify whether Practise Count displayed is correct or not 
	 */
	public String verifyPracticeCount(){
		String practiceCount=null;
		try
		{
			WebElement we = driver.findElement(By.cssSelector(PropertiesRepository.getString("usmle.dashboard.practice")));
			practiceCount= we.getText();
		}
		catch(Exception e)
		{
			logger.error("Unable to validate Practice value", e);
		}
		return practiceCount;
	}	
	/*
	 * Verify whether Question Count displayed is correct or not 
	 */
	public String verifyQuestionCount(){
		String questionCount=null;
		try
		{
			WebElement we = driver.findElement(By.cssSelector(PropertiesRepository.getString("usmle.dashboard.questions")));
			questionCount= we.getText();
		}
		catch(Exception e)
		{
			logger.error("Unable to validate Question value", e);
		}
		return questionCount;
	}
	/*
	 * Verify the correct answer displayed in Last Test section
	 */
	public String verifyLastTestCorrectCount(){
		String correctCount=null;
		try
		{
			WebElement correct = driver.findElement(By.cssSelector(PropertiesRepository.getString("usmle.dashboard.lasttest.correct")));
			correctCount= correct.getText();
		}
		catch(Exception e)
		{
			logger.error("Unable to check correct answer value in Last test", e);
		}
		return correctCount;
	}		
	/*
	 * Verify the incorrect answer displayed in Last Test section
	 */
	public String verifyLastTestIncorrectCount(){
		String incorrectCount=null;
		try
		{
			WebElement incorrect = driver.findElement(By.cssSelector(PropertiesRepository.getString("usmle.dashboard.lasttest.incorrect")));
			incorrectCount= incorrect.getText();
		}
		catch(Exception e)
		{
			logger.error("Unable to check incorrect answer value in Last test", e);
		}
		return incorrectCount;
	}
	/*
	 * Verify the taken questions in QBank section
	 */
	public String verifyTakenQuestionsQBank(){
		String taken=null;
		try
		{
			WebElement takenQuestion = driver.findElement(By.cssSelector(PropertiesRepository.getString("usmle.dashboard.qbank.taken")));
			taken= takenQuestion.getText();
		}
		catch(Exception e)
		{
			logger.error("Unable to check taken questions value in QBank", e);
		}
		return taken;
	}
	/*
	 * Verify the remaining questions displayed in QBank section
	 */
	public String verifyRemainingQuestionsQBank(){
		String remaining=null;
		try
		{
			WebElement remainingQuestion = driver.findElement(By.cssSelector(PropertiesRepository.getString("usmle.dashboard.qbank.remaining")));
			remaining= remainingQuestion.getText();
		}
		catch(Exception e)
		{
			logger.error("Unable to check remaining question value inQBank", e);
		}
		return remaining;
	}	
}