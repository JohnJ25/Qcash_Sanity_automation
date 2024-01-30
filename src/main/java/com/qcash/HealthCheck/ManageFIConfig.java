package com.qcash.HealthCheck;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.SendKeysAction;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.github.dockerjava.api.model.Driver;
import com.qcash.testbase.TestBase;
import com.qcash.utilities.Util;

public class ManageFIConfig extends TestBase {
	
	
	public static String feebasedtext = "" ;
	public static String interstbasedtext ="";
	public static String FraudControlresend ="";
	public static String FraudControlSubmit ="";
	public static String FarudControlCodevalidity ="";	
	public static String Maxopenloanvalue ="";
	public static String DeniedLoansThreshold ="";
	public static String NumberofDenied ="";
	

	static Logger logger = LogManager.getLogger(ManageFIConfig.class);
	
	public static void manageFiConfig() throws Exception, InterruptedException, IOException {
		try {
			WebDriverWait waite = new WebDriverWait(driver, 120);
			Util.waitForSeconds(5);
			waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("ManageFIConfig_Menu_Link"))));
			if (driver.getCurrentUrl().contains("Administration")) {
				logger.info(Util.CUName + " - Home page menu list is displayed");
				waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("ManageFIConfig_Menu_Link"))));
				getObject("ManageFIConfig_Menu_Link").click();
							
			   	waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("PageTitle"))));
            	String pagevalidation = driver.findElement(By.xpath(OR.getProperty("PageTitle"))).getText();
            	logger.info(Util.CUName + "- Manage Fi Configuration page displayed and the title is : " + pagevalidation);                
          
			} else {
				logger.error(Util.CUName + " - Home page menu list is not displayed");
				Assert.assertTrue(false, "Home page menu list is not displayed");
			}
		} catch (Exception e) {
			Util.takeScreenShot("ManageFIConfig_Menu_Link");
			logger.error(Util.CUName + " - Manage Fi Configuration page is not displayed");
			Assert.assertTrue(false, "Manage Fi Configuration page is not displayed");
		}
	}
	
	
	
	public static void fIconfigLoanApplication() throws Exception, InterruptedException, IOException {
		try {
			WebDriverWait waite = new WebDriverWait(driver, 120);
			Util.waitForSeconds(5);
			waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("LoanApp_Menu_Link"))));
			if (driver.getCurrentUrl().contains("FinancialInstitution")) {
				logger.info(Util.CUName + " - FI config General tab is displayed");
				waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("LoanApp_Menu_Link"))));
				getObject("LoanApp_Menu_Link").click();
				logger.info(Util.CUName + " - The Loan Application setting is clicked from the ManageFiConfiguration page");
				Util.waitForPageToLoad();
				logger.info(Util.CUName + " - The General Loan Application page is displayed");
			} else {
				logger.error(Util.CUName + " - General Loan Application page is not displayed");
				Assert.assertTrue(false, "General Loan Application page is not displayed");
			}
		} catch (Exception e) {
			Util.takeScreenShot("LoanApp_Menu_Link");
			logger.error(Util.CUName + " - Manage Fi Configuration page is not displayed");
			Assert.assertTrue(false, "Manage Fi Configuration page is not displayed");
		}
	}
	
	
	public static void maintenanceActive() throws Exception, InterruptedException, IOException {
		
		
		try {
			WebDriverWait waite = new WebDriverWait(driver, 120);
			Util.waitForSeconds(5);
			waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("GeneralLink"))));
			getObject("GeneralLink").click();
			logger.info(Util.CUName + " - The General page is displayed");

			waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("MaintenanceActive"))));
			WebElement active = waite.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("MaintenanceActive"))));
			active.click();
			logger.info(Util.CUName + " - The maintenance Active radio button is clicked");
			scrolldown();

			waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("GeneralPageSave"))));
			getObject("GeneralPageSave").click();
			logger.info(Util.CUName + " - The General Loan Application page is saved");

			waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("LoanSuccessMessage"))));
			Boolean display = getObject("LoanSuccessMessage").isDisplayed();
			Assert.assertTrue(true, "Validated success message");

			waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("Home_Breadcrumb_link"))));
			getObject("Home_Breadcrumb_link").click();
			logger.info(Util.CUName + " - Naviagted back to dashboard page");
				
		} catch (Exception e) {
			Util.takeScreenShot("LoanApp_Menu_Link");
			logger.error(Util.CUName + " - Manage Fi Configuration page is not displayed");
			Assert.assertTrue(false, "Manage Fi Configuration page is not displayed");
		}
	}
	

	
	public static void maintenanceInactive() throws Exception, InterruptedException, IOException {
		
		
		try {
			WebDriverWait waite = new WebDriverWait(driver, 120);
			Util.waitForSeconds(5);
			waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("GeneralLink"))));
			getObject("GeneralLink").click();
			logger.info(Util.CUName + " - The General page is displayed");

			WebElement inactive = waite.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("MaintenanceInActive"))));
			inactive.click();
			logger.info(Util.CUName + " - The maintenance InActive radio button is clicked");
			scrolldown();
			waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("GeneralPageSave"))));
			getObject("GeneralPageSave").click();
			logger.info(Util.CUName + " - The General Loan Application page is saved");

			waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("LoanSuccessMessage"))));
			Boolean display = getObject("LoanSuccessMessage").isDisplayed();
			Assert.assertTrue(true, "Validated success message");

			waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("Home_Breadcrumb_link"))));
			getObject("Home_Breadcrumb_link").click();
			logger.info(Util.CUName + " - Naviagted back to dashboard page");
				
		} catch (Exception e) {
			Util.takeScreenShot("LoanApp_Menu_Link");
			logger.error(Util.CUName + " - Manage Fi Configuration page is not displayed");
			Assert.assertTrue(false, "Manage Fi Configuration page is not displayed");
		}
	}
	
	
public static void awarenessActive() throws Exception, InterruptedException, IOException {
		
		
		try {
			WebDriverWait waite = new WebDriverWait(driver, 120);
			Util.waitForSeconds(5);
			waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("Financialcoaching"))));
			getObject("Financialcoaching").click();
			logger.info(Util.CUName + " - The Financial coaching page is displayed");

		        WebElement active = waite.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("AwarenesspageActive"))));
		        active.click();
		        logger.info(Util.CUName + " - The Awareness Active radio button is clicked"); 
		        
				
		     // The fee based text box value copied and cleared the fee based text box
			    WebElement feebasedbox = driver.findElement(By.xpath(OR.getProperty("FeeBasedThresholdtext")));
			    feebasedtext = getObject("FeeBasedThresholdtext").getAttribute("value").trim();
	                getObject("FeeBasedThresholdtext").clear();
	                logger.info(Util.CUName + " - The Awarness page fee based text box is cleared");
	                feebasedbox.sendKeys("0");
	                
	          // The interest based text box value copied and cleared the fee based text box
			       WebElement interestbasedbox = driver.findElement(By.xpath(OR.getProperty("InterestBasedThresholdtext")));
	               interstbasedtext = getObject("InterestBasedThresholdtext").getAttribute("value").trim();
	               getObject("InterestBasedThresholdtext").clear();
	               logger.info(Util.CUName + " - The Awerness page interest based test box is cleared");
	               interestbasedbox.sendKeys("0");
					waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("AwarnessPageSave"))));
					getObject("GeneralPageSave").click();
					logger.info(Util.CUName + " - The Awerness page is saved");
					
					waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("LoanSuccessMessage"))));
					Boolean display = getObject("LoanSuccessMessage").isDisplayed();
					Assert.assertTrue(true, "Validated sucess message");
					
					waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("Home_Breadcrumb_link"))));
					getObject("Home_Breadcrumb_link").click();
					logger.info(Util.CUName + " - Naviagted back to dashboard page");
		
				
		} catch (Exception e) {
			Util.takeScreenShot("LoanApp_Menu_Link");
			logger.error(Util.CUName + " - Manage Fi Configuration page is not displayed");
			Assert.assertTrue(false, "Manage Fi Configuration page is not displayed");
		}
	}
	
	


public static void awarenessINActive() throws Exception, InterruptedException, IOException {
	
	
	try {
		WebDriverWait waite = new WebDriverWait(driver, 120);
		Util.waitForSeconds(5);
		waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("Financialcoaching"))));
		getObject("Financialcoaching").click();
		logger.info(Util.CUName + " - The Financial coaching page is displayed");
		
	        WebElement inactive = waite.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("AwarenesspageInActive"))));		
	        inactive.click();
	        logger.info(Util.CUName + " - The Awareness InActive radio button is clicked"); 
				
			
	     // The feebased text box value cleared and restored with the original value
				  WebElement feebasedbox = driver.findElement(By.xpath(OR.getProperty("FeeBasedThresholdtext")));
				  getObject("FeeBasedThresholdtext").clear();
			      logger.info(Util.CUName + " - The Awarness fee based text box is cleared");
			      feebasedbox.sendKeys(feebasedtext);
	              logger.info(Util.CUName + " - The Awarness fee based text box value is restored");
	               
	              // The feebased text box value cleared and restored with the original value      
				    WebElement interestbasedbox = driver.findElement(By.xpath(OR.getProperty("InterestBasedThresholdtext")));
				    getObject("InterestBasedThresholdtext").clear();
				    logger.info(Util.CUName + " - The Awarness interest based text box is cleared");
				    interestbasedbox.sendKeys(interstbasedtext);
	               logger.info(Util.CUName + " - The Awarness interest based Test Box value is restored");
	               
				
				waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("AwarnessPageSave"))));
				getObject("AwarnessPageSave").click();
				logger.info(Util.CUName + " - The Awerness page is saved");
				
				waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("LoanSuccessMessage"))));
				Boolean display = getObject("LoanSuccessMessage").isDisplayed();
				Assert.assertTrue(true, "Validated sucess message");
				
				
				waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("Home_Breadcrumb_link"))));
				getObject("Home_Breadcrumb_link").click();
		
				logger.info(Util.CUName + " - Naviagted back to dashboard page");
			
			
	} catch (Exception e) {
		Util.takeScreenShot("LoanApp_Menu_Link");
		logger.error(Util.CUName + " - Manage Fi Configuration page is not displayed");
		Assert.assertTrue(false, "Manage Fi Configuration page is not displayed");
	}
}



public static void fraudControlActive() throws Exception, InterruptedException, IOException {
	
	
	try {
		WebDriverWait waite = new WebDriverWait(driver, 120);
		Util.waitForSeconds(5);
		waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("FraudControl"))));
		getObject("FraudControl").click();
		logger.info(Util.CUName + " - The Fraud control page is displayed");

	        WebElement active = waite.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("FraudControlActive"))));				
	        active.click();
			logger.info(Util.CUName + " - The Fraud Control Active radio button is clicked"); 
			
		 // The Resend text box value copied and cleared the Resend text box
		    WebElement FCresend = driver.findElement(By.xpath(OR.getProperty("FCresendThreshold")));		    
		    FraudControlresend = getObject("FCresendThreshold").getAttribute("value").trim();
                getObject("FCresendThreshold").clear();
                logger.info(Util.CUName + " - The Fraud Control Resend text box value is cleared");
                FCresend.sendKeys("2");
                logger.info(Util.CUName + " - The Fraud Control Resend text box value has been entered");
                Util.waitForSeconds(5);        
         // The Submit text box value copied and cleared the Submit text box
			    WebElement FCsubmit = driver.findElement(By.xpath(OR.getProperty("FCsubmitThreshold")));
			    
               FraudControlSubmit = getObject("FCsubmitThreshold").getAttribute("value").trim();
               getObject("FCsubmitThreshold").clear();
               logger.info(Util.CUName + " - The Fraud Control Submit text box value is cleared");
               FCsubmit.sendKeys("2");
               logger.info(Util.CUName + " - The Fraud Control Submit text box value has been entered");
     
         // The CodeValidity text box value copied and cleared the CodeValidity text box     
               WebElement FCcodevalidity = driver.findElement(By.xpath(OR.getProperty("FCcodevalidity")));
			    
               FarudControlCodevalidity = getObject("FCcodevalidity").getAttribute("value").trim();
               getObject("FCcodevalidity").clear();
               logger.info(Util.CUName + " - The Fraud Control CodeValidity text box value is cleared");
               FCcodevalidity.sendKeys("5");
               logger.info(Util.CUName + " - The Fraud Control CodeValidity text box value has been entered");
               			
				waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("FCsave"))));
				getObject("FCsave").click();
				logger.info(Util.CUName + " - The Fraud control page is saved");
				
				waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("LoanSuccessMessage"))));
				Boolean display = getObject("LoanSuccessMessage").isDisplayed();
				Assert.assertTrue(true, "Validated sucess message");
				
				
				waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("Home_Breadcrumb_link"))));
				getObject("Home_Breadcrumb_link").click();
				logger.info(Util.CUName + " - Naviagted back to dashboard page");
	
			
	} catch (Exception e) {
		Util.takeScreenShot("LoanApp_Menu_Link");
		logger.error(Util.CUName + " - Manage Fi Configuration page is not displayed");
		Assert.assertTrue(false, "Manage Fi Configuration page is not displayed");
	}
}


public static void fraudControlInActive() throws Exception, InterruptedException, IOException {
	
	
	try {
		WebDriverWait waite = new WebDriverWait(driver, 120);
		Util.waitForSeconds(5);
		waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("FraudControl"))));
		getObject("FraudControl").click();
		logger.info(Util.CUName + " - The Fraud control page is displayed");

        WebElement Inactive = waite.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("FraudContolInactive"))));				
        Inactive.click();
		logger.info(Util.CUName + " - The Fraud Control InActive radio button is clicked"); 
				
		// The Resend text box value cleared and restored with the original value
		    WebElement FCresend = driver.findElement(By.xpath(OR.getProperty("FCresendThreshold")));
                getObject("FCresendThreshold").clear();
                logger.info(Util.CUName + " - The Fraud control Resend text box is cleared");
                FCresend.sendKeys(FraudControlresend);
                logger.info(Util.CUName + " - The Fraud control Resend text box value is restored");
                
         // The Submit text box value cleared and restored with the original value
             
			    WebElement FCsubmit = driver.findElement(By.xpath(OR.getProperty("FCsubmitThreshold")));
               getObject("FCsubmitThreshold").clear();
               logger.info(Util.CUName + " - The Submit threshold text box is cleared");
               FCsubmit.sendKeys(FraudControlSubmit);
               logger.info(Util.CUName + " -The Submit threshold text box value is restored");
               
            // The CodeValidity text box value cleared and restored with the original value
               
               WebElement FCcodevalidity = driver.findElement(By.xpath(OR.getProperty("FCcodevalidity")));
               getObject("FCcodevalidity").clear();
               logger.info(Util.CUName + " - The Code Validity threshold text box is cleared");
               FCcodevalidity.sendKeys(FarudControlCodevalidity);             
               logger.info(Util.CUName + " - The Code Validity threshold text box value is restored");
				scrolldown();
				waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("FCsave"))));
				getObject("FCsave").click();
				logger.info(Util.CUName + " - The Fraud control page is saved");
				
				waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("LoanSuccessMessage"))));
				Boolean display = getObject("LoanSuccessMessage").isDisplayed();
				Assert.assertTrue(true, "Validated sucess message");

				waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("Home_Breadcrumb_link"))));
				getObject("Home_Breadcrumb_link").click();	
				logger.info(Util.CUName + " - Naviagted back to dashboard page");
	
			
	} catch (Exception e) {
		Util.takeScreenShot("LoanApp_Menu_Link");
		logger.error(Util.CUName + " - Manage Fi Configuration page is not displayed");
		Assert.assertTrue(false, "Manage Fi Configuration page is not displayed");
	}
}


public static void maskWaitActive() {
	
	try {
		WebDriverWait waite = new WebDriverWait(driver, 120);
		Util.waitForSeconds(5);
		waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("GeneralLink"))));
		getObject("GeneralLink").click();
		logger.info(Util.CUName + " - The Loan Application General page is displayed");

	        WebElement active = waite.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("MaskActive"))));
	        active.click();
	        logger.info(Util.CUName + " - The Mask Wait Active radio button is clicked"); 
						
				scrolldown();
				waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("GeneralPageSave"))));
				getObject("GeneralPageSave").click();
				logger.info(Util.CUName + " - The Loan application General page is saved");
				
				waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("LoanSuccessMessage"))));
				Boolean display = getObject("LoanSuccessMessage").isDisplayed();
				Assert.assertTrue(true, "Validated sucess message");
				
				waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("Home_Breadcrumb_link"))));
				getObject("Home_Breadcrumb_link").click();
				logger.info(Util.CUName + " - Naviagted back to dashboard page");
			
	} catch (Exception e) {
		Util.takeScreenShot("LoanApp_Menu_Link");
		logger.error(Util.CUName + " - Manage Fi Configuration page is not displayed");
		Assert.assertTrue(false, "Manage Fi Configuration page is not displayed");
	}


}


public static void maskWaitInActive() {
	
	try {
		WebDriverWait waite = new WebDriverWait(driver, 120);
		Util.waitForSeconds(5);
		waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("GeneralLink"))));
		getObject("GeneralLink").click();
		logger.info(Util.CUName + " - The Loan Application General page is displayed");

	        WebElement inactive = waite.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("MaskKInActive"))));
	        inactive.click();
	        logger.info(Util.CUName + " - The Mask Wait InActive radio button is clicked"); 
				
				scrolldown();
				waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("GeneralPageSave"))));
				getObject("GeneralPageSave").click();
				logger.info(Util.CUName + " - The Loan application General page is saved");
				
				waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("LoanSuccessMessage"))));
				Boolean display = getObject("LoanSuccessMessage").isDisplayed();
				Assert.assertTrue(true, "Validated sucess message");
				
				waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("Home_Breadcrumb_link"))));
				getObject("Home_Breadcrumb_link").click();
				logger.info(Util.CUName + " - Naviagted back to dashboard page");
						
			
	} catch (Exception e) {
		Util.takeScreenShot("LoanApp_Menu_Link");
		logger.error(Util.CUName + " - Manage Fi Configuration page is not displayed");
		Assert.assertTrue(false, "Manage Fi Configuration page is not displayed");
	}

}



public static void MaximumOpenloanActive() {
	
	try {
		WebDriverWait waite = new WebDriverWait(driver, 120);
		Util.waitForSeconds(5);
		waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("GeneralLink"))));
		getObject("GeneralLink").click();
		logger.info(Util.CUName + " - The Loan Application General page is displayed");

	        WebElement active = waite.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("Maxopenloan"))));
	        active.click();
	        logger.info(Util.CUName + " - The Maximum open loan Active radio button is clicked"); 
				
				waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("Maxopentextbox"))));
				WebElement MaxloanTextbox = driver.findElement(By.xpath(OR.getProperty("Maxopentextbox")));
				
				// The Maxopenloan text box value copied and cleared the Maxopenloan text box
	               Maxopenloanvalue = getObject("Maxopentextbox").getAttribute("value").trim();
	               getObject("Maxopentextbox").clear();
	               logger.info(Util.CUName + " -The Maxopenloan text box is cleared");
	               MaxloanTextbox.sendKeys("0");
	               logger.info(Util.CUName + " - The Max Open QCash Loans Across Products value set as '0' ");
				
				scrolldown();
				waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("GeneralPageSave"))));
				getObject("GeneralPageSave").click();
				logger.info(Util.CUName + " - The Loan application General page is saved");
				
				waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("LoanSuccessMessage"))));
				Boolean display = getObject("LoanSuccessMessage").isDisplayed();
				Assert.assertTrue(true, "Validated sucess message");
				
				waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("Home_Breadcrumb_link"))));
				getObject("Home_Breadcrumb_link").click();
		
				logger.info(Util.CUName + " - Naviagted back to dashboard page");	
			
	} catch (Exception e) {
		Util.takeScreenShot("LoanApp_Menu_Link");
		logger.error(Util.CUName + " - Manage Fi Configuration page is not displayed");
		Assert.assertTrue(false, "Manage Fi Configuration page is not displayed");
	}


}



public static void MaximumOpenloanInActive() {
	
	try {
		WebDriverWait waite = new WebDriverWait(driver, 120);
		Util.waitForSeconds(5);
		waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("GeneralLink"))));
		getObject("GeneralLink").click();
		logger.info(Util.CUName + " - The Loan Application General page is displayed");
	
				waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("Maxopentextbox"))));
				WebElement MaxloanTextbox = driver.findElement(By.xpath(OR.getProperty("Maxopentextbox")));
				getObject("Maxopentextbox").clear();
	               logger.info(Util.CUName + " - The Max Open QCash Loans Across Products text box is cleared");
	               MaxloanTextbox.sendKeys(Maxopenloanvalue);
                   logger.info(Util.CUName + " - The Max Open QCash Loans Across Products text box is restored");
				
                WebElement Inactive = waite.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("Maxopenloan"))));
       	        Inactive.click();
       	        logger.info(Util.CUName + " - The Maximum open loan InActive radio button is clicked"); 
       				
				scrolldown();
				waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("GeneralPageSave"))));
				getObject("GeneralPageSave").click();
				logger.info(Util.CUName + " - The Loan application General page is saved");
				
				waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("LoanSuccessMessage"))));
				Boolean display = getObject("LoanSuccessMessage").isDisplayed();
				Assert.assertTrue(true, "Validated sucess message");
				
				
				waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("Home_Breadcrumb_link"))));
				getObject("Home_Breadcrumb_link").click();
		
				logger.info(Util.CUName + " - Naviagted back to dashboard page");
		
						
			
	} catch (Exception e) {
		Util.takeScreenShot("LoanApp_Menu_Link");
		logger.error(Util.CUName + " - Manage Fi Configuration page is not displayed");
		Assert.assertTrue(false, "Manage Fi Configuration page is not displayed");
	}

}


public static void LoanExclusionActive() {
	
	try {
		WebDriverWait waite = new WebDriverWait(driver, 120);
		Util.waitForSeconds(5);
		waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("LoanExclusion"))));
		getObject("LoanExclusion").click();
		logger.info(Util.CUName + " - The LoanExclusion page is displayed");

	        WebElement active = waite.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("DeniedExclusionActive"))));
	        active.click();
	        logger.info(Util.CUName + " - The LoanExclusion Active radio button is clicked"); 
	        
			
	     // The NumberofDeniedLoanstext box value copied and cleared
		    WebElement feebasedbox = driver.findElement(By.xpath(OR.getProperty("NumberofDeniedLoanstext")));
		    NumberofDenied = getObject("NumberofDeniedLoanstext").getAttribute("value").trim();
                getObject("NumberofDeniedLoanstext").clear();
                logger.info(Util.CUName + " - The Denial exclusion page NumberofDeniedLoans text box is cleared");
                feebasedbox.sendKeys("0");
                
          // The DeniedLoansThresholdtext box value copied and cleared
		       WebElement interestbasedbox = driver.findElement(By.xpath(OR.getProperty("DeniedLoansThresholdtext")));
		       DeniedLoansThreshold = getObject("DeniedLoansThresholdtext").getAttribute("value").trim();
               getObject("DeniedLoansThresholdtext").clear();
               logger.info(Util.CUName + " - The Denial exclusion page DeniedLoansThreshold text box is cleared");
               interestbasedbox.sendKeys("0");
				waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("DeniedExclusionSave"))));
				getObject("DeniedExclusionSave").click();
				logger.info(Util.CUName + " - The Denial exclusion page is saved");
				
				waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("LoanSuccessMessage"))));
				Boolean display = getObject("LoanSuccessMessage").isDisplayed();
				Assert.assertTrue(true, "Validated sucess message");
				
				waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("Home_Breadcrumb_link"))));
				getObject("Home_Breadcrumb_link").click();
				logger.info(Util.CUName + " - Naviagted back to dashboard page");
	
			
	} catch (Exception e) {
		Util.takeScreenShot("LoanApp_Menu_Link");
		logger.error(Util.CUName + " - Manage Fi Configuration page is not displayed");
		Assert.assertTrue(false, "Manage Fi Configuration page is not displayed");
	}
}


public static void LoanExclusionInActive() throws Exception, InterruptedException, IOException {
	
	
	try {
		WebDriverWait waite = new WebDriverWait(driver, 120);
		Util.waitForSeconds(5);
		waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("LoanExclusion"))));
		getObject("LoanExclusion").click();
		logger.info(Util.CUName + " - The LoanExclusion page is displayed");

	        WebElement active = waite.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("DeniedExclusionInActive"))));
	        active.click();
	        logger.info(Util.CUName + " - The LoanExclusion InActive radio button is clicked"); 
	        
			
	     // The NumberofDeniedLoanstext box value copied and cleared
		    WebElement feebasedbox = driver.findElement(By.xpath(OR.getProperty("NumberofDeniedLoanstext")));
		    DeniedLoansThreshold = getObject("NumberofDeniedLoanstext").getAttribute("value").trim();
                getObject("NumberofDeniedLoanstext").clear();
                logger.info(Util.CUName + " - The DeniedExclusion page NumberofDenied Loans text box is cleared");
                feebasedbox.sendKeys("0");
                
          // The DeniedLoansThresholdtext box value copied and cleared
		       WebElement interestbasedbox = driver.findElement(By.xpath(OR.getProperty("DeniedLoansThresholdtext")));
		       NumberofDenied = getObject("DeniedLoansThresholdtext").getAttribute("value").trim();
               getObject("DeniedLoansThresholdtext").clear();
               logger.info(Util.CUName + " - The LoanExclusion page DeniedLoansThreshold text box is cleared");
               interestbasedbox.sendKeys("0");
				waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("DeniedExclusionSave"))));
				getObject("DeniedExclusionSave").click();
				logger.info(Util.CUName + " - The DeniedExclusion page is saved");
				
				waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("LoanSuccessMessage"))));
				Boolean display = getObject("LoanSuccessMessage").isDisplayed();
				Assert.assertTrue(true, "Validated sucess message");
				
				waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("Home_Breadcrumb_link"))));
				getObject("Home_Breadcrumb_link").click();
				logger.info(Util.CUName + " - Naviagted back to dashboard page");
	
			
	} catch (Exception e) {
		Util.takeScreenShot("LoanApp_Menu_Link");
		logger.error(Util.CUName + " - Manage Fi Configuration page is not displayed");
		Assert.assertTrue(false, "Manage Fi Configuration page is not displayed");
	}
}


}
