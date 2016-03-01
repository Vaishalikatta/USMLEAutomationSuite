package com.usmle.automation.tests;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.ascendlearning.automation.ui.assertions.VerificationHandler;
import com.ascendlearning.automation.ui.test.BaseTest;
import com.usmle.automation.pages.CreateNewPracticeTest;
import com.usmle.automation.pages.DashBoardPage;
import com.usmle.automation.pages.DataFlushPage;
import com.usmle.automation.pages.HomePage;
import com.usmle.automation.pages.LogonPage;
import com.usmle.automation.pages.SolutionPage;
import com.usmle.automation.pages.UserReports;

import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Title;
import ru.yandex.qatools.allure.model.SeverityLevel;

public class TestModePracticeTest extends BaseTest {	
	
	Logger logger = LogManager.getLogger(this.getClass());

	HomePage homePage = null;
	DataFlushPage dataFlushPage = null;
	DashBoardPage dashBoardPage = null;
	LogonPage logonPage = null;
	CreateNewPracticeTest createNewPracticeTest = null;
	UserReports userReports = null;
	SolutionPage solutionpage = null;
	
	/**
	 * Total no of test cases covered - 16 Test Scenario - Verify End to End
	 * flow for combination : Test settings -Test Mode, Timer ON & Appearance -
	 * USMLE Benchmark Style
	 * [UB-4,UB-20,UB-29,UB-59,UB-61,UB-86,UB-68,UB-70,UB-74,UB-75,UB-178,UB-261
	 * ,UB-262,
	 * UB-79,UB-80,UB-180,UB-181,UB-310,UB-343,UB-356,UB-71,UB-270,UB-275,UB-62,
	 * UB-469, UB-470,UB-52,UB-54,UB-64,UB-92] 1.Validate user is able to login
	 * to USMLE application 2.Validate user is able to navigate to Dashboard
	 * from Home page 3. Select Test Appearance : Test Mode, Timer ON.
	 * 4.Validate User is able to create practice test with below details: - All
	 * Subjects - Checked (Add 10 questions) - No Systems selected. 5.Validate
	 * Question timer and count down timer is displayed. 5.Validate calculator
	 * functionality 6.Validate Notes save/close functionality 7.Validate Lab
	 * Values functionality 8.Validate Next.Previous button functionality on
	 * Practice test page. 9.Validate Answer selection functionality on practice
	 * test page. 10.Validate that rationales are not displayed. 11.Validate
	 * navigation to last question via navigation bar 12.Validate Submit button
	 * functionality on last question on practice test page. 13.Validate
	 * Congratulation Page - REVIEW MY PRACTICE TEST Button functionality
	 * 14.Validate Test frame and Item frame functionality 15.Validate Solution
	 * page is displayed after clicking on first item in Item frame. 16.Validate
	 * Next/Previous arrow buttons on solution page. 17.Validate
	 * "Back To Reports" button on solution page. 18.Validate "close" link
	 * functionality on solution page
	 */
	@Title("PracticeTest - Test Mode, Timer ON  - USMLE Benchmark Style")
	@Description("Validate Notes,claculator,Lab values,Timer ON,Pause/End button functionalities in Standard view,Test mode")
	@Severity(SeverityLevel.CRITICAL)
	@Test(groups = { "smoke" })
	public void verifyEndToEndFlow9() throws Exception {

		String questions = "10";

		LogonPage logonPage = new LogonPage(driver);
		/** Login to USMLE application and Pass the environment info */
		homePage = logonPage.loginToUsmleApplicationPage("Staging");
		/** Verify whether the login is successful */
		VerificationHandler.verifyTrue(logonPage.verifyLogin(),
				"Error has occured when trying to login to the application");
		/** Navigate to USMLEST01 tab */
		homePage.clickUSMLEST01();
		/** Navigating to Dashboard page */
		dashBoardPage = homePage.openUSMLEDashboard();
		/** Clicking on Create New Practice Test link */
		createNewPracticeTest = dashBoardPage.openCreateNewPracticeTest();

		/** Selecting Test Settings as Test Mode */
		createNewPracticeTest
				.selectTestSettings("usmle.practicetest.radiobutton.testmode");
		/**
		 * Verify whether Timer section has been displayed after selecting Test
		 * mode
		 */
		boolean isTimerDisplayed = createNewPracticeTest
				.isTimerDisplayed("usmle.practicetest.section.timer");
		VerificationHandler.verifyTrue(isTimerDisplayed,
				"Timer is not displayed on Create Practice Test popup");
		/** Selecting the timer as ON */
		createNewPracticeTest
				.selectTimer("usmle.practicetest.radiobutton.timeron");

		/** Selecting Appearance as USMLE Benchmark Style */
		createNewPracticeTest
				.selectAppearance("usmle.practicetest.radiobutton.benchmark");
		/** Selecting checkbox All subjects */
		createNewPracticeTest
				.selectCheckbox("usmle.practicetest.checkbox.allsubjects");
		/** Enter no of questions and clicking on Add button */
		createNewPracticeTest.addQuestions(
				"usmle.practicetest.textbox.allsubjects", questions,
				"usmle.practicetest.button.allsubjects.add",
				"usmle.practicetest.button.allsubjects.add.waitfor");
		/**
		 * Verify whether correct number of questions are getting added to
		 * Create Test button
		 */
		String actualValue = createNewPracticeTest.verifyQuestionsAdded(
				"usmle.practicetest.button.createtest.questions");
		VerificationHandler.verifyEquals(actualValue, questions);
		/**
		 * Click Create Test Button and verify whether USMLE Benchmark style
		 * Practice test is getting opened
		 */
		String dateTime  = createNewPracticeTest.clickCreateTestButton();
		VerificationHandler.verifyTrue(
				createNewPracticeTest.isUsmleBenchmarkPracticeTestDisplayed());

		/**
		 * Verify that count of all the questions added from subject are present
		 * in the test page
		 */
		VerificationHandler.verifyTrue(
				createNewPracticeTest.isAllquestionsPresent(questions),
				"All questions are not displayed");

		/** Verify the presence of Question Timer */
		isTimerDisplayed = createNewPracticeTest
				.isTimerDisplayed("usmle.practicetest.section.questiontimer");
		VerificationHandler.verifyTrue(isTimerDisplayed,
				"Question timer is not displayed");

		/** Verify the presence of countDown Timer */
		isTimerDisplayed = createNewPracticeTest
				.isTimerDisplayed("usmle.practicetest.section.countdowntimer");
		VerificationHandler.verifyTrue(isTimerDisplayed,
				"Count down timer is not displayed");

		/** Validate Calculator Functionality */
				/** Click on Calculator button and Open the calculator */
		createNewPracticeTest.clickCalculator();

		/**
		 * Validate addition operation of 1 and 7 using "=" key on calculator
		 */
		String actualAddition = createNewPracticeTest.validateAddition("one",
				"seven");
		VerificationHandler.verifyEquals(actualAddition, String.valueOf(1 + 7));

		/** Clear the textarea of calculator and reset it to 0 */
		String resetValue = createNewPracticeTest.ClickCbutton();
		VerificationHandler.verifyEquals(resetValue, "0");

		/**
		 * Validate subtraction of 34 from 56 using "Enter key" on calculator
		 */
		String actualSubtraction = createNewPracticeTest
				.validateSubtraction("five", "six", "three", "four");
		VerificationHandler.verifyEquals(actualSubtraction,
				String.valueOf(56 - 34));

		/** Validate multiplication of 9 and 0.2 using equals key */
		resetValue = createNewPracticeTest.ClickCbutton();
		VerificationHandler.verifyEquals(resetValue, "0");
		String actualMulti = createNewPracticeTest
				.validateMultiplication("nine", "zero", "decimal", "two");
		VerificationHandler.verifyEquals(actualMulti, String.valueOf(9 * 0.2));

		createNewPracticeTest.selectAnyAnswer();
		/** Verify that Feedback is NOT displayed on the spot in Test Mode */
		VerificationHandler.verifyTrue(
				createNewPracticeTest.verifyFeedbackNotDisplayed(),
				"Feedback is displayed in Test Mode");

		/** Click Next Button */
		createNewPracticeTest.clickNextButton();

		/** Verify the presence of Question Timer */
		isTimerDisplayed = createNewPracticeTest
				.isTimerDisplayed("usmle.practicetest.section.questiontimer");
		VerificationHandler.verifyTrue(isTimerDisplayed,
				"Question timer is not displayed");

		/** Verify the presence of countDown Timer */
		isTimerDisplayed = createNewPracticeTest
				.isTimerDisplayed("usmle.practicetest.section.countdowntimer");
		VerificationHandler.verifyTrue(isTimerDisplayed,
				"Count down timer is not displayed");

		/** Validate division of 800 by 2 using Enter key on calculator */
		resetValue = createNewPracticeTest.ClickCbutton();
		VerificationHandler.verifyEquals(resetValue, "0");
		String actualdiv = createNewPracticeTest.validateDivision("eight",
				"zero", "zero", "two");
		VerificationHandler.verifyEquals(actualdiv, "400");

		/** Validate square root operation of 4 on calculator */
		resetValue = createNewPracticeTest.ClickCbutton();
		VerificationHandler.verifyEquals(resetValue, "0");
		String actualsqrt = createNewPracticeTest.validateSqrt("four");
		VerificationHandler.verifyEquals(actualsqrt, "2");

		/**
		 * Close the calculator using close Icon and verify that calculator is
		 * closed
		 */
		VerificationHandler.verifyFalse(
				createNewPracticeTest.clickCloseIcon("calculator"),
				"Unable to close the calculator using close icon");

		createNewPracticeTest.selectAnyAnswer();
		/** Verify that Feedback is NOT displayed on the spot in Test Mode */
		VerificationHandler.verifyTrue(
				createNewPracticeTest.verifyFeedbackNotDisplayed(),
				"Feedback is displayed in Test Mode");
		createNewPracticeTest.clickNextButton();

		/** Validate Notes Functionality */

		/** Click on Notes button and Open the notes */
		String notesno = createNewPracticeTest.clickNotes();
		/** Validate that notes opened for the same question */
		VerificationHandler.verifyEquals(notesno,
				createNewPracticeTest.getCurrentPageno());
		/** Enter some text in notes and click on Save and Close button */
		createNewPracticeTest.clicksaveAndcloseNotes(
				"usmle.practicetest.benchmark.button.notes.save");
		/** Verify that notes icon is displayed next to the current question */
		VerificationHandler.verifyTrue(
				createNewPracticeTest.isNotesicondisplayed(),
				"Notes Icon is not displayed");
		/** Verify that notes pop-up is closed */
		VerificationHandler.verifyFalse(createNewPracticeTest.isNotesclosed());
		/**
		 * Open the notes, validate that notes opened for the same question and
		 * verify that text is still the same which was saved
		 */
		notesno = createNewPracticeTest.clickNotes();
		VerificationHandler.verifyEquals(notesno,
				createNewPracticeTest.getCurrentPageno());
		/**
		 * Close the notes using close icon and Verify that notes pop-up is
		 * closed
		 */
		VerificationHandler.verifyFalse(
				createNewPracticeTest.clickCloseIcon("notes"),
				"Unable to close notes using close icon");

		/** Verify the presence of Question Timer */
		isTimerDisplayed = createNewPracticeTest
				.isTimerDisplayed("usmle.practicetest.section.questiontimer");
		VerificationHandler.verifyTrue(isTimerDisplayed,
				"Question timer is not displayed");

		/** Verify the presence of countDown Timer */
		isTimerDisplayed = createNewPracticeTest
				.isTimerDisplayed("usmle.practicetest.section.countdowntimer");
		VerificationHandler.verifyTrue(isTimerDisplayed,
				"Count down timer is not displayed");

		createNewPracticeTest.selectAnyAnswer();
		/** Verify that Feedback is NOT displayed on the spot in Test Mode */
		VerificationHandler.verifyTrue(
				createNewPracticeTest.verifyFeedbackNotDisplayed(),
				"Feedback is displayed in Test Mode");
		createNewPracticeTest.clickNextButton();

		/**
		 * Click on Lab Values button and verify that Lab Values is getting
		 * opened
		 */
		createNewPracticeTest.clickLabValues();
		/** Click on SI Reference Intervals checkbox */
		createNewPracticeTest.clickSIRef();
		createNewPracticeTest.getLabValuesContent();

		/**
		 * Verify that SI Reference Intervals column is displayed in all tabs
		 */
		VerificationHandler.verifyTrue(createNewPracticeTest.isSIRefDisplayed(
				"usmle.practicetest.tab.Labvalues.blood",
				"usmle.practicetest.column.Labvalues.blood.siref"));
		VerificationHandler.verifyTrue(createNewPracticeTest.isSIRefDisplayed(
				"usmle.practicetest.tab.Labvalues.hematologic",
				"usmle.practicetest.column.Labvalues.hematologic.siref"));
		VerificationHandler.verifyTrue(createNewPracticeTest.isSIRefDisplayed(
				"usmle.practicetest.tab.Labvalues.cerebrospinal",
				"usmle.practicetest.column.Labvalues.cerebrospinal.siref"));
		VerificationHandler.verifyTrue(createNewPracticeTest.isSIRefDisplayed(
				"usmle.practicetest.tab.Labvalues.sweat",
				"usmle.practicetest.column.Labvalues.sweat.siref"));

		/*
		 * boolean isSirefDisplayed =
		 * createNewPracticeTest.isTextPresent(siref);
		 * VerificationHandler.verifyTrue(isSirefDisplayed,
		 * "SI Ref column is not displayed");
		 */

		createNewPracticeTest.selectAnyAnswer();
		/** Verify that Feedback is NOT displayed on the spot in Test Mode */
		VerificationHandler.verifyTrue(
				createNewPracticeTest.verifyFeedbackNotDisplayed(),
				"Feedback is displayed in Test Mode");
		
		String currentno = createNewPracticeTest.getCurrentPageno();
		/** Click Next Button */
		createNewPracticeTest.clickNextButton();
		/** Verify whether next question has been displayed or not */
		VerificationHandler.verifyTrue(createNewPracticeTest.isNextQuestionDisplayed(currentno),
				"Next question is not displayed");


		/** Enter some text in the text field and click on search button */
		createNewPracticeTest.enterText("blood");
		createNewPracticeTest.clickSearchButton(
				"usmle.practicetest.benchmark.icon.Labvalues.search");

		/**
		 * Validate that the text is present in Lab values content of all tabs
		 * and count the number of occurrences of text in Lab Values content
		 */
		boolean isTextPresent = createNewPracticeTest.isTextPresent("blood");
		VerificationHandler.verifyTrue(isTextPresent, 
				"Test is not present in Lab values");
		VerificationHandler.verifyTrue(
					createNewPracticeTest.isTextHighlighted("blood"),
					"Searched text is not highlighted in Lab Values");

		createNewPracticeTest.selectAnyAnswer();
		/** Verify that Feedback is NOT displayed on the spot in Test Mode */
		VerificationHandler.verifyTrue(
				createNewPracticeTest.verifyFeedbackNotDisplayed(),
				"Feedback is displayed in Test Mode");

		/**
		 * Click on Lab Values button again and verify that Lab Values is
		 * getting closed
		 */
		createNewPracticeTest.clickLabValues();

		/** Move to next question via side navigation */
		createNewPracticeTest.clickSideNavigation(Integer.parseInt(questions));
		/** Verify that expected question is displayed via side navigation */
		VerificationHandler.verifyTrue(createNewPracticeTest.verifySideNavigation(Integer.parseInt(questions)),
				"Expected question is not displayed through side navigation");
		
		/** Verify whether submit button is displayed at the last question */
		VerificationHandler.verifyTrue(
				createNewPracticeTest.isSubmitButtonDisplayed(),
				"Submit button is not displayed on the last question");
		/**
		 * Click on submit button and verify whether confirm Submit test pop up
		 * is displayed
		 */
		createNewPracticeTest.clickLastSubmitButton();
		VerificationHandler.verifyTrue(
				createNewPracticeTest.isConfirmSubmitPopupDisplayed(),
				"ConfirmSubmitPopup is not Displayed");
		/**
		 * Click No Button and verify whether Practice Test page is displayed
		 */
		createNewPracticeTest.clickResumeTestButton();
		VerificationHandler.verifyTrue(
				createNewPracticeTest.isUsmleBenchmarkPracticeTestDisplayed(),
				"Resume button is not working");
		/**
		 * Click on submit button and verify whether confirm Submit test pop up
		 * is displayed
		 */
		createNewPracticeTest.clickLastSubmitButton();
		VerificationHandler.verifyTrue(
				createNewPracticeTest.isConfirmSubmitPopupDisplayed(),
				"ConfirmSubmitPopup is not Displayed");
		/**
		 * Click YES Button and verify whether congratulation page is displayed
		 */
		createNewPracticeTest.clickConfirmSubmitButton();
		VerificationHandler.verifyTrue(
				createNewPracticeTest.isCongratulationPageDisplayed(),
				"Congratulations page is not displayed");
		/**
		 * Verify whether button "REVIEW MY PRACTICE TEST" is displayed on
		 * Congratulation Page
		 */
		VerificationHandler.verifyTrue(
				createNewPracticeTest.isReviewMyPracticeTestButtonDisplayed());
		/**
		 * Verify whether button "RETURN TO DASHBOARD" is displayed on
		 * Congratulation Page
		 */
		VerificationHandler.verifyTrue(
				createNewPracticeTest.isReturnToDashboardButtonDisplayed());
		/**
		 * Click on "Review My Practice Tests" button and validate the opening
		 * of User Reports page
		 */
		userReports = createNewPracticeTest.clickReviewMyPracticeTestButton();

		/**
		 * Click on Latest test submitted in Test Frame and verify the count of
		 * questions in Item Frame
		 */
		userReports.clickLatestTest(dateTime);
		VerificationHandler.verifyEquals(userReports.verifyItemFramequestions(),
				questions, "After frame does not contain all questions");

		/**
		 * Click on the first Item and verify that solution page is displayed
		 */
		solutionpage = userReports.selectAnyItem();
		VerificationHandler.verifyTrue(solutionpage.isSolutionPageDisplayed(),
				"Solution page is not displayed");

		/**
		 * Verify that count of all the questions added from subject are present
		 * in the test page
		 */
		VerificationHandler.verifyTrue(
				createNewPracticeTest.isAllquestionsPresent(questions),
				"All questions are not displayed on solution page");

		/** Click on Next arrow to navigate to next question */
		solutionpage.clickNextArrow();	
		/**
		 * Verify whether notes icon is still present on the third question
		 */
		VerificationHandler.verifyTrue(
				createNewPracticeTest.isNotesicondisplayed(),
				"Notes Icon is not displayed");
		solutionpage.clickNextArrow();		
		
		/** Click on Previous arrow to navigate to previous question */
		solutionpage.clickPreviousArrow();

		/**
		 * Click on "BACH TO REPORTS" button and verify that user reports page
		 * is displayed
		 */
		solutionpage.clickBacktoReports();
		VerificationHandler.verifyTrue(userReports.isUserReportsPageDisplayed(),
				"User Reports Page is not displayed");

		/**
		 * Click on "Close" link and and verify logon Page is displayed
		 */
		/*
		 * userReports.clickClose();
		 * VerificationHandler.verifyTrue(logonPage.isLogOnPageDisplayed(),
		 * "Logon Page is not displayed");
		 */

	}
	
	/**
	 * Test Scenario - Verify USMLE Dashboard data for below combination: Test
	 * settings - Test Mode & Appearance - Standard USMLE Style [
	 * UB-4,UB-20,UB-29,UB-59,UB-61,UB-86,UB-68,UB-70,UB-74,UB-75,
	 * UB-178,UB-261,UB-262,UB-79,UB-80,UB-180,UB-181,UB-310,UB-343,
	 * UB-356,UB-71,UB-270,UB-275,UB-62,UB-469,UB-470,UB-120,UB-110] Test cases
	 * covered: 1.Validate user is able to login to USMLE application (UB-4)
	 * 2.Validate user is able to navigate to Dashboard from Home page (UB-20)
	 * 3.Validate User is able to create practice test with below details: -
	 * Test Mode - Timer ON All Subjects - Checked (Add 8 questions) - 1 Systems
	 * selected. (UB-29,UB-49) 4.Validate that Practice test is created in
	 * Standard USMLE style. 5.Validate user is able to use Lab Values
	 * functionality.  6.Validate that user is able to navigate to next
	 * question. (UB-140) 7.Validate user is able to use Notes functionality.
	 * (UB-124, UB-126) 8.Validate user is able to use Lab values. (UB-138)
	 * 9.Validate user is able Resume the test using Pause button.(UB-164) 10.
	 * Click on Next button without selecting any answer (UB-145) 11.Validate
	 * user is able Submit the test using Pause button.(UB-165) 12.Verify 
	 * "REVIEW MY PRACTICE TEST" button functionality on Congratulations page
	 * 13.Verify number of added questions on user reports page in Item frame
	 * after the test  14.Verify navigation to solution page  15. Verify 
	 * Previous/Next arrow functionality on solution page 16. Verify "Back to 
	 * Reports" button functionality  17. Verify "Close" Link functionality on 
	 * user reports page
	 * 
	 */
	@Title("PracticeTest - Test Mode/Timer-ON - Standard USMLE Style")
	@Description("Validate Notes,claculator,Lab values,Timer ON,Pause/End button functionalities in Standard view,Test mode")
	@Severity(SeverityLevel.CRITICAL)
	@Test(groups = { "smoke" })
	public void verifyEndToEndFlow10() throws Exception {

		String questions = "8";

		/** Login to USMLE application and Pass the environment info */
		LogonPage logonPage = new LogonPage(driver);
		homePage = logonPage.loginToUsmleApplicationPage("Staging");
		VerificationHandler.verifyTrue(logonPage.verifyLogin(),
				"Error has occured when trying to login to the application");
		/** Navigate to USMLEST01 tab */
		homePage.clickUSMLEST01();
		/** Navigating to Dashboard page */
		dashBoardPage = homePage.openUSMLEDashboard();
		VerificationHandler
				.verifyTrue(dashBoardPage.isDashboardPageDisplayed());

		/** Clicking on Create New Practice Test link */
		createNewPracticeTest = dashBoardPage.openCreateNewPracticeTest();

		/** Selecting Test Settings as Test Mode */
		createNewPracticeTest
				.selectTestSettings("usmle.practicetest.radiobutton.testmode");
		/**
		 * Verify whether Timer section has been displayed after selecting Test
		 * mode
		 */
		boolean isTimerDisplayed = createNewPracticeTest
				.isTimerDisplayed("usmle.practicetest.section.timer");
		VerificationHandler.verifyTrue(isTimerDisplayed,
				"Timer is not displayed on Create Practice Test popup");
		/** Selecting the timer as ON */
		createNewPracticeTest
				.selectTimer("usmle.practicetest.radiobutton.timeron");

		/** Selecting Appearance as Standard USMLE Style */
		createNewPracticeTest
				.selectAppearance("usmle.practicetest.radiobutton.standard");
		/** Selecting All Subjects */
		createNewPracticeTest
				.selectCheckbox("usmle.practicetest.checkbox.allsubjects");
		/** Enter no of questions and clicking on Add button */
		createNewPracticeTest.addQuestions(
				"usmle.practicetest.textbox.allsubjects", questions,
				"usmle.practicetest.button.allsubjects.add",
				"usmle.practicetest.button.createtest");

		/**
		 * Verify whether correct number of questions are getting added to
		 * Create Test button
		 */
		String actualValue = createNewPracticeTest.verifyQuestionsAdded(
				"usmle.practicetest.button.createtest.questions");
		VerificationHandler.verifyEquals(actualValue, questions);

		/** Select cardiovascular from system */
		createNewPracticeTest
				.selectCheckbox("usmle.practicetest.checkbox.cardiovascular");

		/**
		 * Click Create Test Button and verify whether Standard USMLE Practice
		 * test is getting opened
		 */
		String dateTime = createNewPracticeTest.clickCreateTestButton();
		
		VerificationHandler.verifyTrue(
				createNewPracticeTest.isStandardUsmlePracticeTestDisplayed());

		/**
		 * Verify that count of all the questions added from subject are present
		 * in the test page
		 */
		VerificationHandler.verifyTrue(
				createNewPracticeTest.isAllquestionsPresent(questions),
				"All questions are not displayed");

		/** Verify the presence of Block Timer */
		isTimerDisplayed = createNewPracticeTest
				.isTimerDisplayed("usmle.practicetest.section.blocktimer");
		VerificationHandler.verifyTrue(isTimerDisplayed,
				"Block timer is not displayed");

		createNewPracticeTest.selectAnyAnswer();
		/** Verify that Feedback is NOT displayed on the spot in Test Mode */
		VerificationHandler.verifyTrue(
				createNewPracticeTest.verifyFeedbackNotDisplayed(),
				"Feedback is displayed in Test Mode");

		/**
		 * Click on Lab Values button and verify that Lab Values is getting
		 * opened
		 */
		createNewPracticeTest.clickLabValues();
		VerificationHandler.verifyTrue(
				createNewPracticeTest.isLabValuesDisplayed(),
				"Lab Values is not displayed ");
		/** Click on SI Reference Intervals checkbox */
		createNewPracticeTest.clickSIRef();
		createNewPracticeTest.getLabValuesContent();

		/**
		 * Verify that SI Reference Intervals column is displayed in all tabs
		 */
		VerificationHandler.verifyTrue(createNewPracticeTest.isSIRefDisplayed(
				"usmle.practicetest.tab.Labvalues.blood",
				"usmle.practicetest.column.Labvalues.blood.siref"));
		VerificationHandler.verifyTrue(createNewPracticeTest.isSIRefDisplayed(
				"usmle.practicetest.tab.Labvalues.hematologic",
				"usmle.practicetest.column.Labvalues.hematologic.siref"));
		VerificationHandler.verifyTrue(createNewPracticeTest.isSIRefDisplayed(
				"usmle.practicetest.tab.Labvalues.cerebrospinal",
				"usmle.practicetest.column.Labvalues.cerebrospinal.siref"));
		VerificationHandler.verifyTrue(createNewPracticeTest.isSIRefDisplayed(
				"usmle.practicetest.tab.Labvalues.sweat",
				"usmle.practicetest.column.Labvalues.sweat.siref"));

		/*
		 * boolean isSirefDisplayed =
		 * createNewPracticeTest.isTextPresent(siref);
		 * VerificationHandler.verifyTrue(isSirefDisplayed,
		 * "SI Ref column is not displayed");
		 */
		
		String currentno = createNewPracticeTest.getCurrentPageno();
		/** Click Next Button */
		createNewPracticeTest.clickNextButton();
		/** Verify whether next question is displayed or not */
		VerificationHandler.verifyTrue(createNewPracticeTest.isNextQuestionDisplayed(currentno),
				"Next question is not displayed");
		
		/** Verify the presence of Block Timer */
		isTimerDisplayed = createNewPracticeTest
				.isTimerDisplayed("usmle.practicetest.section.blocktimer");
		VerificationHandler.verifyTrue(isTimerDisplayed,
				"Block timer is not displayed");

		createNewPracticeTest.selectAnyAnswer();
		/** Verify that Feedback is NOT displayed on the spot in Test Mode */
		VerificationHandler.verifyTrue(
				createNewPracticeTest.verifyFeedbackNotDisplayed(),
				"Feedback is displayed in Test Mode");

		/** Enter some text in the text field and click on search button */
		createNewPracticeTest.enterText("serum");
		createNewPracticeTest.clickSearchButton(
				"usmle.practicetest.button.Labvalues.search");

		/**
		 * Validate that the text is present in Lab values content of all tabs
		 * and count the number of occurrences of text in Lab Values content
		 */
		boolean isTextPresent = createNewPracticeTest.isTextPresent("serum");
		VerificationHandler.verifyTrue(isTextPresent, 
				"Test is not present in Lab values");
		VerificationHandler.verifyTrue(
					createNewPracticeTest.isTextHighlighted("serum"),
					"Searched text is not highlighted in Lab Values");

		/** Click Next Button */
		createNewPracticeTest.clickNextButton();
		/** Verify the presence of Block Timer */
		isTimerDisplayed = createNewPracticeTest
				.isTimerDisplayed("usmle.practicetest.section.blocktimer");
		VerificationHandler.verifyTrue(isTimerDisplayed,
				"Block timer is not displayed");

		createNewPracticeTest.selectAnyAnswer();
		/** Verify that Feedback is NOT displayed on the spot in Test Mode */
		VerificationHandler.verifyTrue(
				createNewPracticeTest.verifyFeedbackNotDisplayed(),
				"Feedback is displayed in Test Mode");

		/**
		 * Click on Lab Values button again and verify that Lab Values is
		 * getting closed
		 */
		createNewPracticeTest.clickLabValues();
		VerificationHandler.verifyFalse(
				createNewPracticeTest.isLabValuesDisplayed(),
				"Lab values is not closed");

		/**
		 * Click on Calculator button again and verify that Calculator is
		 * getting opened
		 */
		createNewPracticeTest.clickCalculator();
		VerificationHandler.verifyTrue(
				createNewPracticeTest.isCalculatorDisplayed(),
				"Calculator is not displayed");

		currentno = createNewPracticeTest.getCurrentPageno();
		/** Click Next Button */
		createNewPracticeTest.clickNextButton();
		/** Verify whether next question has been displayed or not */
		VerificationHandler.verifyTrue(createNewPracticeTest.isNextQuestionDisplayed(currentno),
				"Next question is not displayed");
		
		/** Verify the presence of Block Timer */
		isTimerDisplayed = createNewPracticeTest
				.isTimerDisplayed("usmle.practicetest.section.blocktimer");
		VerificationHandler.verifyTrue(isTimerDisplayed,
				"Block timer is not displayed");

		createNewPracticeTest.selectAnyAnswer();
		/** Verify that Feedback is NOT displayed on the spot in Test Mode */
		VerificationHandler.verifyTrue(
				createNewPracticeTest.verifyFeedbackNotDisplayed(),
				"Feedback is displayed in Test Mode");

		/**
		 * Validate addition operation of 1 and 8 using "=" key on calculator
		 */
		String actualAddition = createNewPracticeTest.validateAddition("one",
				"eight");
		VerificationHandler.verifyEquals(actualAddition, String.valueOf(1 + 8));

		/**
		 * Click on Calculator button again and verify that Calculator is
		 * getting closed
		 */
		createNewPracticeTest.clickCalculator();
		VerificationHandler.verifyFalse(
				createNewPracticeTest.isCalculatorDisplayed(),
				"Calculator is not closed");

		/**
		 * Click on notes button again and verify that notes is getting opened
		 */
		String notesno = createNewPracticeTest.clickNotes();
		VerificationHandler.verifyTrue(createNewPracticeTest.isNotesDisplayed(),
				"notes is not displayed");
		/** Validate that notes opened for the same question */
		VerificationHandler.verifyEquals(notesno,
				createNewPracticeTest.getCurrentPageno());
		/** Enter some text in notes and click on Save and Close button */
		createNewPracticeTest
				.clicksaveAndcloseNotes("usmle.practicetest.button.notes.save");
		/** Verify that notes icon is displayed next to the current question */
		VerificationHandler.verifyTrue(
				createNewPracticeTest.isNotesicondisplayed(),
				"Notes Icon is not displayed");
		notesno = createNewPracticeTest.clickNotes();
		VerificationHandler.verifyEquals(notesno,
				createNewPracticeTest.getCurrentPageno());

		/** Click Next Button */
		createNewPracticeTest.clickNextButton();
		/** Verify the presence of Block Timer */
		isTimerDisplayed = createNewPracticeTest
				.isTimerDisplayed("usmle.practicetest.section.blocktimer");
		VerificationHandler.verifyTrue(isTimerDisplayed,
				"Block timer is not displayed");

		createNewPracticeTest.selectAnyAnswer();
		/** Verify that Feedback is NOT displayed on the spot in Test Mode */
		VerificationHandler.verifyTrue(
				createNewPracticeTest.verifyFeedbackNotDisplayed(),
				"Feedback is displayed in Test Mode");

		/**
		 * Click on notes button again and verify that notes is getting closed
		 */
		createNewPracticeTest.clickNotes();
		VerificationHandler.verifyFalse(
				createNewPracticeTest.isNotesDisplayed(),
				"Notes is not closed");

		/**
		 * Click Pause Button and verify whether confirm Submit/Resume test pop
		 * up is displayed
		 */
		createNewPracticeTest.clickPauseButton();
		VerificationHandler.verifyTrue(
				createNewPracticeTest.isConfirmSubmitResumePopupDisplayed(),
				"Confirm Submit/Resume Test Popup is not displayed");

		/** Click Submit Button and verify congratulations page is displayed */
		createNewPracticeTest.clickSubmitButton();
		VerificationHandler.verifyTrue(
				createNewPracticeTest.isCongratulationPageDisplayed(),
				"Congratulations Page is not displayed");

		/**
		 * Verify whether button "REVIEW MY PRACTICE TEST" is displayed on
		 * Congratulation Page
		 */
		VerificationHandler.verifyTrue(
				createNewPracticeTest.isReviewMyPracticeTestButtonDisplayed());
		/**
		 * Verify whether button "RETURN TO DASHBOARD" is displayed on
		 * Congratulation Page
		 */
		VerificationHandler.verifyTrue(
				createNewPracticeTest.isReturnToDashboardButtonDisplayed());
		/**
		 * Click on "Review My Practice Tests" button and validate the opening
		 * of User Reports page
		 */
		userReports = createNewPracticeTest.clickReviewMyPracticeTestButton();

		/**
		 * Click on Latest test submitted in Test Frame and verify the count of
		 * questions in Item Frame
		 */
		userReports.clickLatestTest(dateTime);
		VerificationHandler.verifyEquals(userReports.verifyItemFramequestions(),
				questions, "Item frame does not contain all questions");

		/**
		 * Click on the first Item and verify that solution page is displayed
		 */
		solutionpage = userReports.selectAnyItem();
		VerificationHandler.verifyTrue(solutionpage.isSolutionPageDisplayed(),
				"Solution page is not displayed");

		/**
		 * Verify that count of all the questions added from subject are present
		 * in the test page
		 */
		VerificationHandler.verifyTrue(
				createNewPracticeTest.isAllquestionsPresent(questions),
				"All questions are not displayed on solution page");

		/** Click on Next arrow to navigate to next question */
		solutionpage.clickNextButton();
		solutionpage.clickNextButton();

		/** Click on Previous arrow to navigate to previous question */
		solutionpage.clickPreviousButton();

		/**
		 * Click on "BACH TO REPORTS" button and verify that user reports page
		 * is displayed
		 */
		solutionpage.clickBacktoReports();
		VerificationHandler.verifyTrue(userReports.isUserReportsPageDisplayed(),
				"User Reports Page is not displayed");

		/**
		 * Click on "Close" link and and verify logon Page is displayed
		 */
		/*
		 * userReports.clickClose();
		 * VerificationHandler.verifyTrue(logonPage.isLogOnPageDisplayed(),
		 * "Logon Page is not displayed");
		 */

	}


}
