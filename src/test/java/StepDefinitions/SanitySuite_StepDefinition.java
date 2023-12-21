package StepDefinitions;

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

	

@Then("From the application dashboard page select the manage fi configuration menu link")
public void from_the_application_dashboard_page_select_the_manage_fi_configuration_menu_link() {
  
    try {
		ManageFIConfig.fIConfig();
		logger.info(Util.CUName + " - Manage fi configuration page is displayed");
    	
	} catch (Exception e) {
		Util.takeScreenShot("");
		logger.error(Util.CUName + " - Manage fi configuration Menu link is not clicked");
		Assert.assertTrue(false, "Manage fi configuration Menu link is not clicked");
	}
}

@When("Click on the loan application menu link")
public void click_on_the_loan_application_menu_link() {
	   try {
		   ManageFIConfig.fIconfigLoanApplication();
		} catch (Exception e) {
			// TODO: handle exception
		}
}

@When("Set the maintenance to active or inactive and save the page")
public void Set_the_maintenance_to_active_or_inactive_and_save_the_page() {
	   try {
		 //  ManageFIConfig.maintenance();
		   ManageFIConfig.maintenanceInactive();
		   
		} catch (Exception e) {
			// TODO: handle exception
		}
}

@Then("Nagivate back to the application dashboard page and select the QA Test suite page")
public void nagivate_back_to_the_application_dashboard_page_and_select_the_QA_Test_suite_page() {
	   try {
			
		   QATestSuitePageFunctions.WebTestPrimarySecondaryValidation();
		} catch (Exception e) {
			// TODO: handle exception
		}
}

@Then("Click on the web test primary and secondary and verify the manintenance page")
public void click_on_the_web_test_primary_and_secondary_and_verify_the_manintenance_page() {
	   try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
}
	
	
	
	
	
	
	
	
	
	
	
/*
 * @Given("From the application dashboard page select the system status menu link"
 * ) // StatusMenuNavigationCheck public void
 * from_the_application_dashboard_page_select_the_system_status_menu_link() {
 * try { NCRStatusPageFunctions.StatusMenu(); logger.info(Util.CUName +
 * " - Status Menu is displayed"); } catch (Exception e) {
 * Util.takeScreenShot("StatusMenuNavigationCheck"); logger.error(Util.CUName +
 * " - Status Menu link is not clicked"); Assert.assertTrue(false,
 * "Status Menu link is not clicked"); }
 * 
 * }
 * 
 * @When("Click on the refresh button and check the system status is stable") //
 * RefreshButtonClick public void
 * Click_on_the_refresh_button_and_check_the_system_status_is_stable() { try {
 * NCRStatusPageFunctions.PageRefresh();
 * 
 * } catch (Exception e) { e.printStackTrace();
 * Util.takeScreenShot("RefreshButtonClick"); logger.error(Util.CUName +
 * " - Issue with System status Refresh"); Assert.assertTrue(false,
 * "Issue with System status Refresh"); } }
 * 
 * @Then("validate the NCR certificate primary and secondary regions are stable"
 * ) // PrimaryandSecondaryRegionStatus public void
 * validate_the_NCR_certificate_primary_and_secondary_regions_are_stable() { try
 * { scrolldown(); //
 * NCRStatusPageFunctions.PrimaryandSecondaryRegionStatus("NcrTable", //
 * "PrimaryNcrStatus"); //
 * NCRStatusPageFunctions.PrimaryandSecondaryRegionStatus("AdminFooterLogo",
 * "Primary_SysStatus"); //
 * NCRStatusPageFunctions.PrimaryandSecondaryRegionNCRStatus("NcrTable",
 * "PrimaryNcrStatus"); //
 * TestSuitePageFunction.PrimaryandSecondaryRegionNCRStatus(v, en);
 * logger.info(Util.CUName +
 * " - Primary and Secondary region status are validated");
 * QATestSuitePageFunctions.ValidatevaluesinQATestsuitePageAfterPagerefresh();
 * QATestSuitePageFunctions.WebTestPrimarySecondaryValidation(); } catch
 * (Exception e) { Util.takeScreenShot("PrimaryandSecondaryRegionStatus");
 * logger.error(Util.CUName +
 * " - Primary and Secondary region status are  stable");
 * Assert.assertTrue(false, "Primary and Secondary region status are  stable");
 * } }
 */



}
