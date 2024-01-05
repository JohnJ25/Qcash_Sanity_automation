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
			CommonFunctions.QCashInternalLogin(username, password);

		} catch (Exception e) {
			Util.takeScreenShot("LoginValidation");
			logger.error(Util.CUName + " - Login is not success");
			Assert.assertTrue(false, "Login is not success");
		}
	}


	
@Then("Validate the loan application workflow when the Maintenance page is set to Active")
public void Validate_the_loan_application_workflow_when_the_Maintenance_page_is_set_to_Active() throws InterruptedException, IOException, Exception {
	
	try {
		
		ManageFIConfig.manageFiConfig();
		ManageFIConfig.fIconfigLoanApplication();
		ManageFIConfig.maintenanceActive();
		QATestSuitePageFunctions.WebTestPrimarySecondaryMaintenanceValidation();
		
		
	} catch (Exception e) {
		Util.takeScreenShot("");
		logger.error(Util.CUName + " - The Maintenance Page Active setting is not working");
		Assert.assertTrue(false, "The Maintenance Page Active setting is not working");
	}
		
	
	
}

@Then("Validate the loan application workflow when the Maintenance page is set to InActive")
public void Validate_the_loan_application_workflow_when_the_Maintenance_page_is_set_to_InActive() throws InterruptedException, IOException, Exception {
  
	try{
	   ManageFIConfig.manageFiConfig();
	   ManageFIConfig.fIconfigLoanApplication();
	   ManageFIConfig.maintenanceInactive();
	   QATestSuitePageFunctions.WebTestPrimarySecondaryMaintenanceValidation();
	
} catch (Exception e) {
	Util.takeScreenShot("");
	logger.error(Util.CUName + " - The Maintenance Page Inactive setting is not working");
	Assert.assertTrue(false, "The Maintenance page Inactive setting is not working");
}
}
	
@Then("Set the Awareness page to Activate and verify the loan application flow")
public void Set_the_Awareness_page_to_Activate_and_verify_the_loan_application_flow() {
  try {
	  ManageFIConfig.manageFiConfig();
	   ManageFIConfig.fIconfigLoanApplication();
	   ManageFIConfig.awarenessActive();
	   QATestSuitePageFunctions.WebTestPrimarySecondaryAwarnessValidation();
} catch (Exception e) {
	Util.takeScreenShot("");
	logger.error(Util.CUName + " - The Awarness Page Active setting is not working");
	Assert.assertTrue(false, "The Awarness Page Active setting is not working");
}
  
}

@Then("Set the Awareness page to InActivate and verify the loan application flow")
public void Set_the_Awareness_page_to_InActivate_and_verify_the_loan_application_flow() {
  try {
	  ManageFIConfig.manageFiConfig();
	   ManageFIConfig.fIconfigLoanApplication();
	   ManageFIConfig.awarenessINActive();
	   QATestSuitePageFunctions.WebTestPrimarySecondaryAwarnessValidation();
} catch (Exception e) {
	Util.takeScreenShot("");
	logger.error(Util.CUName + " - The Awarness Page Active setting is working");
	Assert.assertTrue(false, "The Awarness Page Active setting is working");
}
}


@Then("Set the Fraud Control to Activate and verify the loan application flow")
public void Set_the_Fraud_Control_to_Activate_and_verify_the_loan_application_flow() {
   
	try {
		  ManageFIConfig.manageFiConfig();
		  ManageFIConfig.fIconfigLoanApplication();
		  ManageFIConfig.fraudControlActive();
		  QATestSuitePageFunctions.WebTestPrimarySecondaryFraudcontrolValidation();
		   
	} catch (Exception e) {
		Util.takeScreenShot("");
		logger.error(Util.CUName + " - The Fraud control page active setting is working");
		Assert.assertTrue(false, "The Fraud control page active setting is working");
	}
}

@Then("Set the Fraud Control to InActivate and verify the loan application flow")
public void Set_the_Fraud_Control_to_InActivate_and_verify_the_loan_application_flow() {
   try {
		  ManageFIConfig.manageFiConfig();
		  ManageFIConfig.fIconfigLoanApplication();
		  ManageFIConfig.fraudControlInActive();
		  QATestSuitePageFunctions.WebTestPrimarySecondaryFraudcontrolValidation();
} catch (Exception e) {
	Util.takeScreenShot("");
	logger.error(Util.CUName + " - The Fraud control page Inactive setting is working");
	Assert.assertTrue(false, "The Fraud control page Inactive setting is working");
}
}


@Then("Set the Mask Initiate Wait Time to Active and verify the loan application flow")
public void Set_the_Mask_Initiate_Wait_Time_to_Active_and_verify_the_loan_application_flow() throws InterruptedException, IOException, Exception {
	   try {
			  ManageFIConfig.manageFiConfig();
			  ManageFIConfig.fIconfigLoanApplication();
			  ManageFIConfig.maskWaitActive();
			  QATestSuitePageFunctions.WebTestPrimarySecondaryMaskwaitValidation();
	} catch (Exception e) {
		Util.takeScreenShot("");
		logger.error(Util.CUName + " - The mask initiate wait page active setting is working");
		Assert.assertTrue(false, "The mask initiate wait page active setting is working");
	}
}

@Then("Set the Mask Initiate Wait Time to InActive and verify the loan application flow")
public void Set_the_Mask_Initiate_Wait_Time_to_InActive_and_verify_the_loan_application_flow() {
	   try {
			  ManageFIConfig.manageFiConfig();
			  ManageFIConfig.fIconfigLoanApplication();
			  ManageFIConfig.maskWaitInActive();
			  QATestSuitePageFunctions.WebTestPrimarySecondaryMaskwaitValidation();
	} catch (Exception e) {
		Util.takeScreenShot("");
		logger.error(Util.CUName + " - The mask initiate wait page active setting is working");
		Assert.assertTrue(false, "The mask initiate wait page Inactive setting is working");
	}
}


@Then("Set the Deny for Max Open Loans Across Products to Active and verify the loan application flow")
public void Set_the_Deny_for_Max_Open_Loans_Across_Products_to_Active_and_verify_the_loan_application_flow() {
	   try {
			  ManageFIConfig.manageFiConfig();
			  ManageFIConfig.fIconfigLoanApplication();
			  ManageFIConfig.MaximumOpenloanActive();
			  QATestSuitePageFunctions.WebTestPrimarySecondaryAANValidation();
	} catch (Exception e) {
		Util.takeScreenShot("");
		logger.error(Util.CUName + " - The AAN active setting is working");
		Assert.assertTrue(false, "The AAN active setting is working");
	}
}

@Then("Set the Deny for Max Open Loans Across Products to InActive and verify the loan application flow")
public void Set_the_Deny_for_Max_Open_Loans_Across_Products_to_InActive_and_verify_the_loan_application_flow() {
	   try {
			  ManageFIConfig.manageFiConfig();
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

