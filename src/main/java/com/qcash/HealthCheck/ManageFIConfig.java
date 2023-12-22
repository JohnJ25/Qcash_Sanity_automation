package com.qcash.HealthCheck;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.qcash.testbase.TestBase;
import com.qcash.utilities.Util;

public class ManageFIConfig extends TestBase {

	static Logger logger = LogManager.getLogger(ManageFIConfig.class);
	
	public static void fIConfig() throws Exception, InterruptedException, IOException {
		try {
			WebDriverWait waite = new WebDriverWait(driver, 120);
			Util.waitForSeconds(5);
			waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("ManageFIConfig_Menu_Link"))));
			if (driver.getCurrentUrl().contains("Administration")) {
				logger.info(Util.CUName + " - Home page menu list is displayed");
				waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("ManageFIConfig_Menu_Link"))));
				getObject("ManageFIConfig_Menu_Link").click();
				logger.info(Util.CUName + " - Manage Fi Configuration option is clicked");
				Util.waitForPageToLoad();
				logger.info(Util.CUName + " - Manage Fi Configuration page is displayed");
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
				logger.info(Util.CUName + " - Fi Configuration Loan Application option is clicked");
				Util.waitForPageToLoad();
				logger.info(Util.CUName + " - General Loan Application page is displayed");
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
	
	
	public static void maintenance() throws Exception, InterruptedException, IOException {
		
		
		try {
			WebDriverWait waite = new WebDriverWait(driver, 120);
			Util.waitForSeconds(5);
			waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("MaintenanceActive"))));

//				WebElement active  = driver.findElement(By.xpath(OR.getProperty("MaintenanceActive")));
//				WebElement inactive  = driver.findElement(By.xpath(OR.getProperty("MaintenanceInActive")));
				
			
//				Boolean radio = inactive.equals(active);
				
				 // Wait for the radio button to be clickable (ensuring it's interactable)
//		        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		        WebElement inactive = waite.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("MaintenanceInActive"))));
		        WebElement active = waite.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("MaintenanceActive"))));
//
//		        // Check if the radio button is displayed and enabled
//		        if (inactive.isDisplayed() && inactive.isEnabled()) {
//		            // Check if the radio button is selected
//		            if (inactive.isSelected()) {
//		                System.out.println("Radio Button InaActive is already selected");
//		                active.click();	
//		                
//		            
//		            } 
//		            else {
//		                System.out.println("Radio Button InaActive is not selected");
//		                // Code to select the radio button if needed
//		                // radioButton1.click();
//		            }
//		        } else {
//		            System.out.println("Radio Button InActive is not displayed or not enabled");
//		        }
//		        
//				
//				if (!inactive.isSelected()) {
//					
//					System.out.println("button selected");
					
					active.click();
					
					logger.info(Util.CUName + " - Active Radio option is clicked"); // 
					logger.info(Util.CUName + " - General Loan Application page is displayed");
					
					scrolldown();
					waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("GeneralPageSave"))));
					getObject("GeneralPageSave").click();
					logger.info(Util.CUName + " - General Loan Application page is saved");
					
					waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("LoanSuccessMessage"))));
					Boolean display = getObject("LoanSuccessMessage").isDisplayed();
					Assert.assertTrue(true, "Validated sucess message");
					
					// the check box is checked or not - need verification
					
					waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("Home_Breadcrumb_link"))));
					getObject("Home_Breadcrumb_link").click();
			
					
				//	Inctive.isSelected();
					
//					waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("MaintenanceActive"))));
//					logger.info(Util.CUName + " - Active Radio option is clicked"); // 
//					scrolldown();
//					waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("GeneralPageSave"))));
//					getObject("GeneralPageSave").click();
//					logger.info(Util.CUName + " - General Loan Application page is saved");
//					
//					waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("LoanSuccessMessage"))));
//					Boolean displayy = getObject("LoanSuccessMessage").isDisplayed();
//					Assert.assertTrue(true, "Validated sucess message");
					
					// the check box is checked or not - need verification
					
//					waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("Home_Breadcrumb_link"))));
//					getObject("Home_Breadcrumb_link").click();
		//		}
				
				
				
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
			waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("maintain"))));
		
//				waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("maintain"))));
//				String txt = getObject("MaintenanceActive").getText();
//				
			Thread.sleep(2000);
				WebElement listItem = driver.findElement((By.xpath(OR.getProperty("MaintenanceActive"))));

				// Check if the list item is active based on a specific class or attribute
				if (listItem.getAttribute("class").contains("custom radio")) {
				    listItem.click();
				}

				    else
				    {
				    	
				    	
				    }
				logger.info(Util.CUName + " - Radio option is clicked"); // 
				logger.info(Util.CUName + " - General Loan Application page is displayed");
				
				scrolldown();
				waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("GeneralPageSave"))));
				getObject("GeneralPageSave").click();
				logger.info(Util.CUName + " - General Loan Application page is saved");
				
//				waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("LoanSuccessMessage"))));
//				Boolean display = getObject("LoanSuccessMessage").isDisplayed();
//				Assert.assertTrue(true, "Validated sucess message");
//				
				// the check box is checked or not - need verification
				
				waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("Home_Breadcrumb_link"))));
				getObject("Home_Breadcrumb_link").click();
			
				
				
				
		} catch (Exception e) {
			Util.takeScreenShot("LoanApp_Menu_Link");
			logger.error(Util.CUName + " - Manage Fi Configuration page is not displayed");
			Assert.assertTrue(false, "Manage Fi Configuration page is not displayed");
		}
	}
	
	
	public static void maintenanceInactivetest() throws Exception, InterruptedException, IOException {
		
		
		try {
			WebDriverWait waite = new WebDriverWait(driver, 120);
			Util.waitForSeconds(5);
			waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("MaintenanceActive"))));

		        WebElement inactive = waite.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("MaintenanceInActive"))));
					
		        inactive.click();
					
					logger.info(Util.CUName + " - Active Radio option is clicked"); // 
					logger.info(Util.CUName + " - General Loan Application page is displayed");
					
					scrolldown();
					waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("GeneralPageSave"))));
					getObject("GeneralPageSave").click();
					logger.info(Util.CUName + " - General Loan Application page is saved");
					
					waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("LoanSuccessMessage"))));
					Boolean display = getObject("LoanSuccessMessage").isDisplayed();
					Assert.assertTrue(true, "Validated sucess message");
					
					
					waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("Home_Breadcrumb_link"))));
					getObject("Home_Breadcrumb_link").click();
			
					logger.info(Util.CUName + " - Dashboard page is displayed");

					// the check box is checked or not - need verification
					
//					waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("Home_Breadcrumb_link"))));
//					getObject("Home_Breadcrumb_link").click();
		//		}
				
				
				
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

		        WebElement inactive = waite.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("AwarenesspageActive"))));
					
		        inactive.click();
					
					logger.info(Util.CUName + " - Active Radio option is clicked"); // 
					logger.info(Util.CUName + " - General Loan Application page is displayed");
					
					scrolldown();
					waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("AwarnessPageSave"))));
					getObject("GeneralPageSave").click();
					logger.info(Util.CUName + " - Awerness page is saved");
					
					waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("LoanSuccessMessage"))));
					Boolean display = getObject("LoanSuccessMessage").isDisplayed();
					Assert.assertTrue(true, "Validated sucess message");
					
					
					waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("Home_Breadcrumb_link"))));
					getObject("Home_Breadcrumb_link").click();
			
					logger.info(Util.CUName + " - Dashboard page is displayed");

					// the check box is checked or not - need verification
					
//					waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("Home_Breadcrumb_link"))));
//					getObject("Home_Breadcrumb_link").click();
		//		}
				
				
				
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

	        WebElement inactive = waite.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("AwarenesspageInActive"))));
				
	        inactive.click();
				
				logger.info(Util.CUName + " - Active Radio option is clicked"); // 
				logger.info(Util.CUName + " - General Loan Application page is displayed");
				
				scrolldown();
				waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("AwarnessPageSave"))));
				getObject("GeneralPageSave").click();
				logger.info(Util.CUName + " - Awerness page is saved");
				
				waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("LoanSuccessMessage"))));
				Boolean display = getObject("LoanSuccessMessage").isDisplayed();
				Assert.assertTrue(true, "Validated sucess message");
				
				
				waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("Home_Breadcrumb_link"))));
				getObject("Home_Breadcrumb_link").click();
		
				logger.info(Util.CUName + " - Dashboard page is displayed");

				// the check box is checked or not - need verification
				
//				waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("Home_Breadcrumb_link"))));
//				getObject("Home_Breadcrumb_link").click();
	//		}
			
			
			
	} catch (Exception e) {
		Util.takeScreenShot("LoanApp_Menu_Link");
		logger.error(Util.CUName + " - Manage Fi Configuration page is not displayed");
		Assert.assertTrue(false, "Manage Fi Configuration page is not displayed");
	}
}




	
}
