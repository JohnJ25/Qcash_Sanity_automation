package StepDefinitions;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.qcash.HealthCheck.QATestSuitePageFunctions;
import com.qcash.HealthCheck.ManageFIConfig;
import com.qcash.commons.CommonFunctions;
import com.qcash.testbase.TestBase;
import com.qcash.utilities.Util;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SanitySuite_StepDefinition extends TestBase {
	static Logger APP_LOGS = LogManager.getLogger(SanitySuite_StepDefinition.class);
	static Logger logger = LogManager.getLogger(SanitySuite_StepDefinition.class);
	SoftAssert softassert = new SoftAssert();

	@Given("Launch the FI {string} site {string} urls")
	public void Launch_the_FI_site_urls(String sitename, String url) {
		try {
			driver.get(url);
			Util.CUName = sitename.toUpperCase();
			logger.info(Util.CUName + " - Clicked Qcash URL and landed in Login page");
			Util.waitForPageToLoad();
			logger.info(Util.CUName + " - Application URL is launched ");
		} catch (Exception e) {
			Util.takeScreenShot("UrlLaunchValidation");
			logger.error(Util.CUName + " - Application URL is not launched");
			Assert.assertTrue(false, "Application URL is not launched");
		}
	}

	@When("Enter the valid user credentials {string} and {string} to access the application") // LoginValidation
	public void enter_the_valid_user_credentials_and_to_access_the_application(String username, String password) {
		try {
			Util.waitForPageToLoad();
			/*
			 * getObject("Login_Username").sendKeys(username);
			 * getObject("Login_Password").sendKeys(password);
			 * getObject("Login_SigninButton").click();
			 */

			CommonFunctions.QCashInternalLogin(username, password);

			// CommonFunctions.QCashLogin(username, password);

		} catch (Exception e) {
			Util.takeScreenShot("LoginValidation");
			logger.error(Util.CUName + " - Login is not success");
			Assert.assertTrue(false, "Login is not success");
		}
	}


	
@Then("Select the Maintenance Active and complete the loan application flow")
public void select_the_Maintenance_Active_and_complete_the_loan_application_flow() throws InterruptedException, IOException, Exception {
	
	try {
		
		ManageFIConfig.fIConfig();
		logger.info(Util.CUName + " - Manage fi configuration page is displayed");
		ManageFIConfig.fIconfigLoanApplication();
		logger.info(Util.CUName + " - Manage fi configuration Loan application page is displayed");
		ManageFIConfig.maintenance();
		logger.info(Util.CUName + " - The Maintenance active is selected");
		QATestSuitePageFunctions.WebTestPrimarySecondaryValidation();
		logger.info(Util.CUName + " - The Maintenance is Active so the Maintenance page is validated");
		
		
	} catch (Exception e) {
		Util.takeScreenShot("");
		logger.error(Util.CUName + " - The Maintenance Page is not displayed");
		Assert.assertTrue(false, "The Maintenance Page is not displayed");
	}
		
	
	
}

@Then("Select the Maintenance InActive and complete the loan application flow")
public void select_the_Maintenance_InActive_and_complete_the_loan_application_flow() throws InterruptedException, IOException, Exception {
  
	try{
	   ManageFIConfig.fIConfig();
	   logger.info(Util.CUName + " - Manage fi configuration page is displayed");
	   ManageFIConfig.fIconfigLoanApplication();
	   logger.info(Util.CUName + " - Manage fi configuration Loan application page is displayed");
	   ManageFIConfig.maintenanceInactivetest();
	   logger.info(Util.CUName + " - The Maintenance Inactive is selected");
	   QATestSuitePageFunctions.WebTestPrimarySecondaryValidation();
	   logger.info(Util.CUName + " - The Maintenance is Inactive so the loan application page validated");
	
} catch (Exception e) {
	Util.takeScreenShot("");
	logger.error(Util.CUName + " - The Maintenance Inactive setting is not working");
	Assert.assertTrue(false, "The Maintenance Inactive setting is not working");
}
}
	
@Then("Select the Awareness page Active and complete the loan application flow")
public void select_the_Awareness_page_Active_and_complete_the_loan_application_flow() {
  try {
	  ManageFIConfig.fIConfig();
	   logger.info(Util.CUName + " - Manage fi configuration page is displayed");
	   ManageFIConfig.fIconfigLoanApplication();
	   logger.info(Util.CUName + " - Manage fi configuration Loan application page is displayed");
	   ManageFIConfig.awarenessActive();
	   logger.info(Util.CUName + " - The awarness setting is selected");
	   QATestSuitePageFunctions.WebTestPrimarySecondaryAwarnessValidation();
	   logger.info(Util.CUName + " - The awarness setting  is active so the Awarness page is validated");
} catch (Exception e) {
	Util.takeScreenShot("");
	logger.error(Util.CUName + " - The awarness page active setting is not working");
	Assert.assertTrue(false, "The awarness page active setting is not working");
}
  
}

@Then("Select the Awareness page InActive and complete the loan application flow")
public void select_the_Awareness_page_InActive_and_complete_the_loan_application_flow() {
  try {
	  ManageFIConfig.fIConfig();
	   logger.info(Util.CUName + " - Manage fi configuration page is displayed");
	   ManageFIConfig.fIconfigLoanApplication();
	   logger.info(Util.CUName + " - Manage fi configuration Loan application page is displayed");
	   ManageFIConfig.awarenessINActive();
	   logger.info(Util.CUName + " - The awarness setting is not selected");
	   QATestSuitePageFunctions.WebTestPrimarySecondaryAwarnessValidation();
	   logger.info(Util.CUName + " - The awarness setting is inactive so the loan application page validated");
} catch (Exception e) {
	Util.takeScreenShot("");
	logger.error(Util.CUName + " - The awarness page active setting is working");
	Assert.assertTrue(false, "The awarness page active setting is working");
}
}


@Then("Select the Fraud Control page and Activate the FraudControl setting and verify the loan application flow")
public void select_the_Fraud_Control_page_and_Activate_the_FraudControl_setting_and_verify_the_loan_application_flow() {
   
	try {
		  ManageFIConfig.fIConfig();
		  ManageFIConfig.fIconfigLoanApplication();
		  ManageFIConfig.fraudControlActive();
		  QATestSuitePageFunctions.WebTestPrimarySecondaryFraudcontrolValidation();
		   
	} catch (Exception e) {
		Util.takeScreenShot("");
		logger.error(Util.CUName + " - The Fraud control page active setting is working");
		Assert.assertTrue(false, "The Fraud control page active setting is working");
	}
}

@Then("Select the Fraud Control page and InActivate the FraudControl setting and verify the loan application flow")
public void select_the_Fraud_Control_page_and_InActivate_the_FraudControl_setting_and_verify_the_loan_application_flow() {
   try {
		  ManageFIConfig.fIConfig();
		  ManageFIConfig.fIconfigLoanApplication();
		  ManageFIConfig.fraudControlInActive();
		  QATestSuitePageFunctions.WebTestPrimarySecondaryFraudcontrolValidation();
} catch (Exception e) {
	Util.takeScreenShot("");
	logger.error(Util.CUName + " - The Fraud control page Inactive setting is working");
	Assert.assertTrue(false, "The Fraud control page Inactive setting is working");
}
}


@Then("Select the Mask Initiate Wait Time Active and complete the loan application flow")
public void select_the_Mask_Initiate_Wait_Time_Active_and_complete_the_loan_application_flow() throws InterruptedException, IOException, Exception {
	   try {
			  ManageFIConfig.fIConfig();
			  ManageFIConfig.fIconfigLoanApplication();
			  ManageFIConfig.maskWaitActive();
			  QATestSuitePageFunctions.WebTestPrimarySecondaryMaskwaitValidation();
	} catch (Exception e) {
		Util.takeScreenShot("");
		logger.error(Util.CUName + " - The mask initiate wait page active setting is working");
		Assert.assertTrue(false, "The mask initiate wait page active setting is working");
	}
}

@Then("Select the Mask Initiate Wait Time InActive and complete the loan application flow")
public void select_the_Mask_Initiate_Wait_Time_InActive_and_complete_the_loan_application_flow() {
	   try {
			  ManageFIConfig.fIConfig();
			  ManageFIConfig.fIconfigLoanApplication();
			  ManageFIConfig.maskWaitInActive();
			  QATestSuitePageFunctions.WebTestPrimarySecondaryMaskwaitValidation();
	} catch (Exception e) {
		Util.takeScreenShot("");
		logger.error(Util.CUName + " - The mask initiate wait page active setting is working");
		Assert.assertTrue(false, "The mask initiate wait page Inactive setting is working");
	}
}


@Then("Select Active on the Deny for Max Open QCash Loans Across Products setting and launch the loan application flow")
public void select_Active_on_the_Deny_for_Max_Open_QCash_Loans_Across_Products_setting_and_launch_the_loan_application_flow() {
	   try {
			  ManageFIConfig.fIConfig();
			  ManageFIConfig.fIconfigLoanApplication();
			  ManageFIConfig.MaximumOpenloanActive();
			  QATestSuitePageFunctions.WebTestPrimarySecondaryAANValidation();
	} catch (Exception e) {
		Util.takeScreenShot("");
		logger.error(Util.CUName + " - The AAN active setting is working");
		Assert.assertTrue(false, "The AAN active setting is working");
	}
}

@Then("Select InActive on the Deny for Max Open QCash Loans Across Products setting and launch the loan application flow")
public void select_InActive_on_the_Deny_for_Max_Open_QCash_Loans_Across_Products_setting_and_launch_the_loan_application_flow() {
	   try {
			  ManageFIConfig.fIConfig();
			  ManageFIConfig.fIconfigLoanApplication();
			  ManageFIConfig.MaximumOpenloanInActive();
			  QATestSuitePageFunctions.WebTestPrimarySecondaryAANValidation();
	} catch (Exception e) {
		Util.takeScreenShot("");
		logger.error(Util.CUName + " - The AAN Inactive setting is working");
		Assert.assertTrue(false, "The AAN Inactive setting is working");
	}

}
}

