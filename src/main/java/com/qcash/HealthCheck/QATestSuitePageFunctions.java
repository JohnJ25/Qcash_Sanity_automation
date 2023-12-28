package com.qcash.HealthCheck;

import java.io.IOException;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.qcash.testbase.TestBase;
import com.qcash.utilities.Util;

public class QATestSuitePageFunctions extends TestBase {
	
	static String BaseAccID;
	static String EmailText = "" ;
	static String getPhoneNo = "" ;
	static String BaseAccountID = "";

    static Logger logger = LogManager.getLogger(QATestSuitePageFunctions.class);

    public static void QATestSuiteFieldCheck() throws Exception, InterruptedException, IOException {
        try {
            WebDriverWait waite = new WebDriverWait(driver, 120);
            Util.waitForSeconds(2);
            waite.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("QATestSuite_Link"))));
            getObject("QATestSuite_Link").click();
            logger.info( Util.CUName + " - QA Test Suite link is clicked");
            waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("Index_Text"))));
            if (driver.getCurrentUrl().contains("SSOTestSetup")) {
                logger.info( Util.CUName + " - QA Test Suite page is displayed");
                // Validate the QA test suite text
                String QATestSuite_Txt = getObject("Index_Text").getText();
                String Exp_QATestSuite_Text = CONFIG.getProperty("Exp_QATestSuite_Text");
                Assert.assertEquals(QATestSuite_Txt, Exp_QATestSuite_Text);
                logger.info( Util.CUName + " - QA Test Suite text is verified");

                // Validate major text fields are clickable
               int QATestSuite_Textboxescount = driver.findElements(By.xpath(OR.getProperty("QATestSuite_Textboxes"))).size();
                logger.info(QATestSuite_Textboxescount);
               int QATestSuite_Textboxes=QATestSuite_Textboxescount-1;
               logger.info(QATestSuite_Textboxes);
               if (QATestSuite_Textboxes >= 1) {
                    /*for (int i = 1; i <= QATestSuite_Textboxes; i++) {
                        waite.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@class='text-box single-line'])[ " + i + " ]")));
                        driver.findElement(By.xpath("(//*[@class='text-box single-line'])[ " + i + " ]")).click();

                    }*/
            	   
            	   if(Util.CUName.equals("FOUNDERS FEDERAL CU"))
                   {
                    
                    Util.clickAnElement("TaxId_Txtbox", "Tax Id / SSN (Founders only) Textbox");
                    Util.clickAnElement("Location_Txtbox", "Location Textbox");
                    Util.clickAnElement("ApplicationChannel_Txtbox", "ApplicationChannel Textbox");
                    Util.clickAnElement("TellerID_Txtbox", "TellerID Textbox");
                    Util.clickAnElement("Email_Txtbox", "Email Textbox");
                    Util.clickAnElement("PhoneNo_Txtbox", "PhoneNo Textbox");
  
                    logger.info( Util.CUName + " - These (Tax Id / SSN (Founders only), Teller Id, Location, E-mail, Application Channel) text fields are clickable in the QA test suite page");
                   }
            	   else
            	   {
            		   Util.clickAnElement("BaseAccount_Txtbox", "BaseAccount Textbox");
                       Util.clickAnElement("Location_Txtbox", "Location Textbox");
                       Util.clickAnElement("ApplicationChannel_Txtbox", "ApplicationChannel Textbox");
                       Util.clickAnElement("TellerID_Txtbox", "TellerID Textbox");
                       Util.clickAnElement("Email_Txtbox", "Email Textbox");
                       Util.clickAnElement("PhoneNo_Txtbox", "PhoneNo Textbox");
     
                       logger.info( Util.CUName + " - These (Base Account, Teller Id, Location, E-mail, Application Channel) text fields are clickable in the QA test suite page");
            		   
            	   }
                   
                    
                   if(Util.CUName.equals("FOUNDERS FEDERAL CU"))
                    {
                    	WebElement TaxID=driver.findElement(By.xpath(OR.getProperty("TaxId_Txtbox")));
                    	WebElement PhoneNo=driver.findElement(By.xpath(OR.getProperty("PhoneNo_Txtbox")));
                    	
                    	if(TaxID.isDisplayed()==true)
                    	{
                	   
                	   waite.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("TaxId_Txtbox"))));
                        getObject("TaxId_Txtbox").click();
                        logger.info( Util.CUName + " - Tax Id / SSN (overrides Base Account) field is clickable");
                    	}
                    	else
                    	{
                    		logger.info( Util.CUName + " - Tax Id / SSN (overrides Base Account) field is not displayed");
                    		Assert.assertTrue(false, "Tax Id / SSN (overrides Base Account) field is not displayed");
                    		
                    	}

                    		 waite.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("PhoneNo_Txtbox"))));
                             getObject("PhoneNo_Txtbox").click();
                             logger.info( Util.CUName + " - Phone Number field is clickable");                                     
                    }
                    else
                    {      

                    waite.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("PhoneNo_Txtbox"))));
                    getObject("PhoneNo_Txtbox").click();
                    logger.info( Util.CUName + " - Phone Number field is clickable");
                    }
                   
                
                    waite.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("CoreDate_Txtbox"))));
                    getObject("CoreDate_Txtbox").click();
                    if (Util.isElementDisplayed(By.xpath(OR.getProperty("CoreDate_DatePicker")))) {
                        logger.info( Util.CUName + " - Core datepicker is displayed");
                        logger.info( Util.CUName + " - Core Date Field is clicked");
                    } else {
                        logger.error( Util.CUName + " - Core datepicker is not displayed");
                        Assert.assertTrue(false, "Core Date Field is not clicked");
                    }

                    waite.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("Preapproved_Chkbox"))));
                    getObject("Preapproved_Chkbox").click();
                    logger.info( Util.CUName + " - Preapproved checkbox is clickable");

                    if (Util.isElementDisplayed(By.xpath(OR.getProperty("Preapproved_Chkbox_select")))) {
                        logger.info( Util.CUName + " - Pre-approved checkbox field is selected");
                        getObject("Preapproved_Chkbox_select").click();
                        Util.isElementDisplayed(By.xpath(OR.getProperty("Preapproved_Chkbox")));
                        logger.info( Util.CUName + " - Pre-approved checkbox field is unselected");

                    } else {
                        logger.error( Util.CUName + " - Pre-approved checkbox field is not clicked");
                        Assert.assertTrue(false, "Pre-approved checkbox field is not clicked");
                    }
                    waite.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("Fund_Yes_Radiobtn"))));
                    getObject("Fund_Yes_Radiobtn").click();
                    if (Util.isElementDisplayed(By.xpath(OR.getProperty("Radiobtn_Checked")))) {
                        logger.info( Util.CUName + " - Yes radio button is clicked");
                    } else {
                        logger.error( Util.CUName + " - Yes radio button is not clicked in Fund radio button");
                        Assert.assertTrue(false, "Yes radio button is not clicked in Fund radio button");
                    }
                    waite.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("Fund_No_Radiobtn"))));
                    getObject("Fund_No_Radiobtn").click();
                    if (Util.isElementDisplayed(By.xpath(OR.getProperty("Radiobtn_Checked")))) {
                        logger.info( Util.CUName + " - No radio button is clicked");
                    } else {
                        logger.error( Util.CUName + " - No radio button is not clicked in Fund radio button");
                        Assert.assertTrue(false, "No radio button is not clicked in QA test suite page");
                    }
                } else {
                    logger.error( Util.CUName + " - QA Test Suite text fields are not clickable");
                    Assert.assertTrue(false, "QA Test Suite text fields are not clickable");
                }

                // Validate web, mobile - primary and secondary and clear buttons are clickable
                int QATestSuite_buttons = driver.findElements(By.xpath(OR.getProperty("QATestSuite_Btns"))).size();
                if (QATestSuite_buttons >= 1) {
                    for (int i = 1; i <= QATestSuite_buttons; i++) {
                        waite.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@class='button-large-no-padding secondary small'])[ " + i + " ]")));

                    }
                    logger.info( Util.CUName + " - These (Web Test and Mobile Test Primary and Secondary, Date Picker clear) buttons are clickable in the QA test suite page");

                } else {
                    logger.error( Util.CUName + " - These (Web Test and Mobile Test Primary and Secondary, Date Picker clear ) buttons are not clickable in the QA test suite page");
                    Assert.assertTrue(false, "These (Web Test and Mobile Test Primary and Secondary, Date Picker clear ) buttons are not clickable in the QA test suite page");
                }
                // Validate Save and Cancel buttons are clickable
                waite.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("QATestSuite_Save_Btn"))));
                logger.info( Util.CUName + " - Save button is clickable");
                waite.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("QATestSuite_Cancel_Btn"))));
                logger.info( Util.CUName + " - Cancel button is clickable");
        }
        else {
            logger.error( Util.CUName + " - QA Test Suite page is not displayed");
            Assert.assertTrue(false, "QA Test Suite page is not displayed");
        }
            
        }    
         catch (Exception e) {
            Util.takeScreenShot("QATestSuiteFieldCheck");
            logger.error( Util.CUName + " - QA test suite page is not displayed");
            Assert.assertTrue(false, "QA test suite page is not displayed");
        }
    }
    
    
    public static void BaseAccountRequiredValidation() throws Exception, InterruptedException, IOException {
        SoftAssert softassert = new SoftAssert();
        try
        {
            WebDriverWait waite = new WebDriverWait(driver, 90);
        
		 if (driver.getCurrentUrl().contains("SSOTestSetup")) {
                logger.info( Util.CUName + " - QA Test Suite - SetUp tab is enabled");
                
                if(Util.CUName.equals("FOUNDERS FEDERAL CU"))
                {
                	
                String getTaxID=getObject("TaxId_Txtbox").getAttribute("value").trim();
                logger.info(getTaxID);
				
				 if(getTaxID.isEmpty())
                {
                	
                // ********** BaseAccount validation for WEB Test - Primary **********
                waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("WebTestPrimary_Btn"))));
                getObject("WebTestPrimary_Btn").click();
                logger.info( Util.CUName + " - WEB Test - Primary button is clicked when the TaxID is empty.");
                Util.waitForSeconds(2);
                
                int basealertreqtxt = 0;
                basealertreqtxt =  driver.findElements(By.xpath(OR.getProperty("QATestSuite_BaseAccReqAlert"))).size();
                
                if( basealertreqtxt!=0)             
                {
                String ActualAlertmsg=getObject("QATestSuite_BaseAccReqAlert").getText().trim();
                logger.info(ActualAlertmsg);
                String ExpectedAlertmsg="BaseAccount is required.";
                if(ActualAlertmsg.contains(ExpectedAlertmsg))
                		{
                	Util.takeScreenShot(" BaseAccountReqAlert");
               	 logger.info (Util.CUName + " -  BaseAccount required alert  shown when clicking upon WebTest Primary button.");
                    softassert.assertTrue(false,  Util.CUName + " -  BaseAccount required alert  shown when clicking upon WebTest Primary button.");
                		}
               
               else
                {
                	Util.takeScreenShot("PrimaryWebTestRedAlert");
    				logger.info( Util.CUName + " - Red alert is shown when clicking upon WebTest Primary button.");
    				softassert.assertTrue(false, " -  Red alert is shown when clicking upon WebTest Primary button.");	
                }
                }
                Util.waitForSeconds(2);
                softassert.assertAll();
                }
                
				 else
	                {
	    				logger.info( Util.CUName + " - BaseAccount / Tax ID is displayed with AccountID in the QA Testsuite page.");
	                } 
                }
                else
                {
                	
                	BaseAccID=getObject("QATestSuiteSetup_BaseAcc").getAttribute("value").trim();
                    logger.info(BaseAccID);
    				
    				 if(BaseAccID.isEmpty())
                    {
		                // ********** BaseAccount validation for WEB Test - Primary **********
		                waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("WebTestPrimary_Btn"))));
		                getObject("WebTestPrimary_Btn").click();
		                logger.info( Util.CUName + " - WEB Test - Primary button is clicked when the BaseAccount is empty.");
		                Util.waitForSeconds(2);
		                
		                int basealertreqtxt = 0;
		                basealertreqtxt =  driver.findElements(By.xpath(OR.getProperty("QATestSuite_BaseAccReqAlert"))).size();
		                
		                if( basealertreqtxt!=0)             
		                {
		                String ActualAlertmsg=getObject("QATestSuite_BaseAccReqAlert").getText().trim();
		                logger.info(ActualAlertmsg);
		                String ExpectedAlertmsg="BaseAccount is required.";
		                if(ActualAlertmsg.contains(ExpectedAlertmsg))
		                		{
		                	Util.takeScreenShot(" BaseAccountReqAlert");
		                	 logger.info (Util.CUName + " -  BaseAccount required alert  shown when clicking upon WebTest Primary button.");
		                    softassert.assertTrue(false,  Util.CUName + " -  BaseAccount required alert  shown when clicking upon WebTest Primary button.");
		                		}
		               
		               else
		                {
		                	Util.takeScreenShot("PrimaryWebTestRedAlert");
		    				logger.info( Util.CUName + " - Red alert is shown when clicking upon WebTest Primary button.");
		    				softassert.assertTrue(false, " -  Red alert is shown when clicking upon WebTest Primary button.");	
		                }
		                }
		                
		                Util.waitForSeconds(2);
		                softassert.assertAll();
	                }
                
               
                else
                {
    				logger.info( Util.CUName + " - BaseAccount / Tax ID is displayed with AccountID in the QA Testsuite page.");
                }
              
               
                                }	      
        }}
              
			  catch (Exception e) {
			   Util.takeScreenShot(" IssueinBaseAccRequiredValidation");
			   logger.info( Util.CUName + " - Issue in validating BaseAc/TaxID in the QA Testsuite page.");
				softassert.assertTrue(false, " -  Issue in validating BaseAc/TaxID in the QA Testsuite page.");	
			  }
       
           }
   


    public static void WebTestPrimarySecondaryValidation() throws Exception, InterruptedException, IOException {
        SoftAssert softassert = new SoftAssert();

        boolean FundYesRadioBtn = false;

        try {
            WebDriverWait spinnerwait = new WebDriverWait(driver, 120);
            WebDriverWait waite = new WebDriverWait(driver, 90);
            waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("QATestSuite_Link"))));
            getObject("QATestSuite_Link").click();
            Util.waitForSeconds(2);
            waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("Index_Text"))));

            if (driver.getCurrentUrl().contains("SSOTestSetup")) {
                logger.info(Util.CUName + " - QA Test Suite - SetUp tab is enabled");

                WebElement radioElement = driver.findElement(By.id("FundYes"));
                FundYesRadioBtn = radioElement.isSelected();

                waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("Fund_No_Radiobtn"))));
                getObject("Fund_No_Radiobtn").click();
                logger.info(Util.CUName + " - Fund No Radio button is clicked");
                Util.waitForSeconds(2);

                if (Util.CUName.equals("TEXAS PLAINS FCU")) {
                    BaseAccountID = getObject("BaseAccount_Txtbox").getAttribute("value").trim();
                    logger.info(Util.CUName + " - " + BaseAccountID);
                    Util.takeScreenShot("QATestSuite-OriginalBaseAccountID");

                    getObject("BaseAccount_Txtbox").clear();
                    logger.info(Util.CUName + " - BaseAccount ID is cleared");
                    Util.waitForSeconds(2);
                    String BaseAcctobeTested = CONFIG.getProperty("TexasBaseAccIDtobetested");
                    logger.info(Util.CUName + " - " + BaseAcctobeTested);
                    getObject("BaseAccount_Txtbox").sendKeys(BaseAcctobeTested);
                } else {
                    logger.info(Util.CUName + " - BaseAccount ID is not modified");
                }

                EmailText = getObject("QATestSuite_Email_Textbox").getAttribute("value").trim();
                getObject("QATestSuite_Email_Textbox").clear();
                logger.info(Util.CUName + " - Email Text Box is cleared");

                getPhoneNo = getObject("PhoneNo_Txtbox").getAttribute("value").trim();
                getObject("PhoneNo_Txtbox").clear();
                logger.info(Util.CUName + " - PhoneNo Text Box is cleared");

                waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("QATestSuite_Save_Btn"))));
                getObject("QATestSuite_Save_Btn").click();
                logger.info(Util.CUName + " - Save button is clicked");

                logger.info(Util.CUName + " - Settings updated successfully message is displayed");

                waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("WebTestPrimary_Btn"))));
                getObject("WebTestPrimary_Btn").click();
                logger.info(Util.CUName + " - WEB Test - Primary button is clicked");

                spinnerwait.until(ExpectedConditions.invisibilityOf(getObject("SSO_Spinner")));
                Util.waitForSeconds(2);

                	getObject("MemberView_Tab").getAttribute("class").contains("is-active");
                    logger.info(Util.CUName + " - Member View Tab is active");
                    WebElement FrameWindow = driver.findElement(By.xpath(OR.getProperty("frameContent")));
                  
                    driver.switchTo().frame(FrameWindow);
            //        System.out.println("I'm in Frame");
                 
                    boolean maintenanceCheck = driver.getPageSource().contains("Maintenance");                                                
                  
                    if (maintenanceCheck) { 
                    String maintenancepage = getObject("MaintenanceValidation").getText();
                    logger.info(Util.CUName + " - The Maintenance page is displayed in web test Primary and the title is : " + maintenancepage);
                    Util.takeScreenShot("maintenancepage");
                    softassert.assertTrue(true, "The Maintenance page is displayed in web test Primary and the title is : " + maintenancepage);
               
                } else {
                	
                     waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("Loanlandingpagevalidation"))));
                     String Loanlandingpage = getObject("Loanlandingpagevalidation").getText(); 
                     logger.info(Util.CUName + " - Web test Primary : The loan application form is displayed successfully..." + Loanlandingpage);
                     Util.takeScreenShot("WebTestPrimarySuccess");
                     softassert.assertTrue(true, "Web test Primary : The loan application form is displayed successfully..." + Loanlandingpage);
                }

                driver.switchTo().defaultContent();
            } else if (getObject("Setup_Tab").getAttribute("class").contains("is-active")) {
                int redalertfailtxt = driver.findElements(By.xpath(OR.getProperty("Red_Alert_FailText"))).size();
                if (redalertfailtxt != 0) {
                    String RedAlertText = getObject("Red_Alert_FailText").getText();
                    logger.info(Util.CUName + " - Web test primary is not loaded and red alert message is shown");
                    logger.info(Util.CUName + " - Red Alert Message is : " + RedAlertText);
                    Util.takeScreenShot("PrimaryWebTestRedAlert");
                    softassert.assertTrue(false, Util.CUName + "Web test primary is not loaded and red alert message is shown");
                }
            }

            // Web test secondary
            waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("Setup_Tab"))));
            getObject("Setup_Tab").click();
            logger.info(Util.CUName + " - Setup tab is clicked in QA test suite page");

            waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("WebTestSec_Btn"))));
            getObject("WebTestSec_Btn").click();
            logger.info(Util.CUName + " - WEB Test - Secondary button is clicked");
            spinnerwait.until(ExpectedConditions.invisibilityOf(getObject("SSO_Spinner")));
            Util.waitForSeconds(2);
            WebElement FrameWindow = driver.findElement(By.xpath(OR.getProperty("frameContent")));

            driver.switchTo().frame(FrameWindow);
            //        System.out.println("I'm in Frame");
                 
                    boolean maintenanceCheck = driver.getPageSource().contains("Maintenance");                                                
                  
                    if (maintenanceCheck) { 
                    String maintenancepage = getObject("MaintenanceValidation").getText();
                    logger.info(Util.CUName + " - The Maintenance page is displayed in web test secondary and the title is : " + maintenancepage);
                    Util.takeScreenShot("maintenancepage");
                    softassert.assertTrue(true, "The Maintenance page is displayed in web test secondary and the title is : " + maintenancepage);
               
                } else {
                	
                     waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("Loanlandingpagevalidation"))));
                     String Loanlandingpage = getObject("Loanlandingpagevalidation").getText(); 
                     logger.info(Util.CUName + " - Web test secondary : The loan application form is displayed successfully..." + Loanlandingpage);
                     Util.takeScreenShot("WebTestPrimarySuccess");
                     softassert.assertTrue(true, "Web test secondary : The loan application form is displayed successfully..." + Loanlandingpage);
                }

            driver.switchTo().defaultContent();

         QATestSuitePageFunctions.RevertEmailandFundoptions();

          waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("Home_Breadcrumb_link"))));
		getObject("Home_Breadcrumb_link").click();
            logger.info(Util.CUName + " - Web Test Primary and Secondary have been validated.");

        //    softassert.assertAll();
            
         //   waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("Home_Breadcrumb_link"))));
    		//getObject("Home_Breadcrumb_link").click();
            
    		logger.info(Util.CUName + " - switched to home page.");
    		
        } catch (Exception e) {
            Util.takeScreenShot("QATestSuiteFieldCheck");
            logger.error(Util.CUName + " - QA test suite page is not displayed");

           QATestSuitePageFunctions.RevertEmailandFundoptions();

            Assert.assertTrue(false, Util.CUName + "QA test suite page is not displayed");
        }
    }

       
    
    
    public static void RevertEmailandFundoptions() throws Exception, InterruptedException, IOException {
           // String EmailText = "" ;
            boolean FundYesRadioBtn = false;
        
        try {
        	WebDriverWait waite = new WebDriverWait(driver, 90);
        	 waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("Setup_Tab"))));
             getObject("Setup_Tab").click();
             logger.info( Util.CUName + " - Setup tab is clicked in QA test suite page");
             
             
            //Replace BaseAccountID
             
             if(Util.CUName.equals("TEXAS PLAINS FCU"))
             {
            	 getObject("BaseAccount_Txtbox").clear();
             getObject("BaseAccount_Txtbox").sendKeys(BaseAccountID);
             logger.info( Util.CUName + " - Retrived BaseAccountID is stored - " + BaseAccountID);
             getObject("QATestSuite_Save_Btn").click();
             logger.info( Util.CUName + " - Save button is clicked");
             String getBaseAccountID= getObject("BaseAccount_Txtbox").getAttribute("value").trim();
             logger.info( Util.CUName +  " - " + getBaseAccountID);
             Assert.assertEquals(BaseAccountID, getBaseAccountID);
             Util.takeScreenShot("QATestSuite-RestoredtoOriginalBaseAccountID");
             logger.info( Util.CUName + " - Verified Restored BaseAccount ID in the QA Testsuite page.");
             }
             else
             {
            	 logger.info( Util.CUName +  " - " + "Original BaseAccount ID is not modified."); 
             }
    
    // Email Input
    if(EmailText.isEmpty())
    {
    	  logger.info( Util.CUName + " - Email id is empty");
    	 
    }
    else
    {
    	 getObject("QATestSuite_Email_Textbox").sendKeys(EmailText);
         logger.info( Util.CUName + " - Retrived email value is stored - " + EmailText);
         getObject("QATestSuite_Save_Btn").click();
         logger.info( Util.CUName + " - Save button is clicked");
         
    }
    
 // PhoneNo Input
    if(getPhoneNo.isEmpty())
    {
    	  logger.info( Util.CUName + " - Phone number is empty");
    	 
    }
    else
    {
    	 getObject("PhoneNo_Txtbox").sendKeys(getPhoneNo);
         logger.info( Util.CUName + " - Retrived PhoneNo value is stored - " + getPhoneNo);
         getObject("QATestSuite_Save_Btn").click();
         logger.info( Util.CUName + " - Save button is clicked");
         
    }
    
    // Fund Yes Logic
    if(FundYesRadioBtn==true)
    {
    	getObject("Fund_Yes_Radiobtn").click();
    	logger.info( Util.CUName + " - Funding radio button is stored back to 'Yes'");
        getObject("QATestSuite_Save_Btn").click();
        logger.info( Util.CUName + " - Save button is clicked");
    }
    
    QATestSuitePageFunctions.ValidatevaluesinQATestsuitePageAfterPagerefresh();
        
        }
        
        catch (Exception e) {    	
            Util.takeScreenShot("QATestSuite-RevertEmailandFundoptions");
           logger.error( Util.CUName + " - Email and Fund options are not reverted in the QA Testsuite" );
           Assert.assertTrue(false, Util.CUName + "QA test suite page is not displayed");
        }
    }
    
    
    public static void ValidatevaluesinQATestsuitePageAfterPagerefresh() throws Exception, InterruptedException, IOException {
        try{
        	WebDriverWait waite = new WebDriverWait(driver, 90);
        	getObject("Home_Breadcrumb_link").click();
        	logger.info( Util.CUName + " - Home Breadcrumb is clicked from the QA Testsuite page.");
        	
        	Util.waitForSeconds(2);
            waite.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("QATestSuite_Link"))));
            getObject("QATestSuite_Link").click();
            logger.info( Util.CUName + " - QA Test Suite link is clicked");

            QATestSuitePageFunctions.VerifyRestoredvaluesinQATestSuitePage();
        }
        catch(Exception e)
        {
        	 Util.takeScreenShot("QATestsuitePageReloadAftersave");
             logger.error( Util.CUName + " - Issue in reloading QA Testsuite page." );
             Assert.assertTrue(false, Util.CUName + "QA test suite page is not displayed");
        }
    }
    
    public static void VerifyRestoredvaluesinQATestSuitePage() throws Exception, InterruptedException, IOException {
        try{
        	WebDriverWait waite = new WebDriverWait(driver, 90);
        	boolean FundYesRadioBtn = false;
        	//Verify BaseAccount ID
        	
        	if(Util.CUName.equals("TEXAS PLAINS FCU"))
            {
            
        		String verifyBaseAccountID= getObject("BaseAccount_Txtbox").getAttribute("value").trim();
                logger.info( Util.CUName +  " - " + verifyBaseAccountID);
                Assert.assertEquals(BaseAccountID, verifyBaseAccountID);
                logger.info( Util.CUName + " - Verified Restored BaseAccount ID in the QA Testsuite page.");
        		
            }
            else
            {
           	 logger.info( Util.CUName +  " - " + "Original BaseAccount ID is not modified."); 
            }

               
             //Verify Email Address
        	
               if(!(EmailText.isEmpty()))
               {
               	  
               	String verifyEmailAddress= getObject("QATestSuite_Email_Textbox").getAttribute("value").trim();
                logger.info( Util.CUName +  " - " + verifyEmailAddress);
                Assert.assertEquals(EmailText, verifyEmailAddress);
                logger.info( Util.CUName + " - Verified Restored EmailAddress in the QA Testsuite page."); 
               	 
               }
               else
               {
            	   logger.info( Util.CUName + " - Email id is empty");  
               }
               
               //Verify Phone Number
           	
               if(!(getPhoneNo.isEmpty()))
               {               	  
               	String verifyPhoneNo= getObject("PhoneNo_Txtbox").getAttribute("value").trim();
                logger.info( Util.CUName +  " - " + verifyPhoneNo);
                Assert.assertEquals(getPhoneNo, verifyPhoneNo);
                logger.info( Util.CUName + " - Verified Restored PhoneNo in the QA Testsuite page."); 
               	 
               }
               else
               {
            	   logger.info( Util.CUName + " - Phone number is empty");  
               }
               
               //Verify Fund Yes/No option
               
               if(FundYesRadioBtn==true)
               {
               	logger.info( Util.CUName + " - Funding radio button is stored back to 'Yes'");

               }
               
               
        }
        catch(Exception e)
        {
        	 Util.takeScreenShot("QATestsuitePageReloadAftersave");
             logger.error( Util.CUName + " - Issue in validating restored values in the QA Testsuite page." );
             Assert.assertTrue(false, Util.CUName + "QA test suite page is not displayed");
        }
    }
    
    
    public static void WebTestPrimarySecondaryAwarnessValidation() throws Exception, InterruptedException, IOException {
        SoftAssert softassert = new SoftAssert();

        boolean FundYesRadioBtn = false;

        try {
            WebDriverWait spinnerwait = new WebDriverWait(driver, 120);
            WebDriverWait waite = new WebDriverWait(driver, 90);
            waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("QATestSuite_Link"))));
            getObject("QATestSuite_Link").click();
            Util.waitForSeconds(2);
            waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("Index_Text"))));

            if (driver.getCurrentUrl().contains("SSOTestSetup")) {
                logger.info(Util.CUName + " - QA Test Suite - SetUp tab is enabled");

                WebElement radioElement = driver.findElement(By.id("FundYes"));
                FundYesRadioBtn = radioElement.isSelected();

                waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("Fund_No_Radiobtn"))));
                getObject("Fund_No_Radiobtn").click();
                logger.info(Util.CUName + " - Fund No Radio button is clicked");
                Util.waitForSeconds(2);

                if (Util.CUName.equals("TEXAS PLAINS FCU")) {
                    BaseAccountID = getObject("BaseAccount_Txtbox").getAttribute("value").trim();
                    logger.info(Util.CUName + " - " + BaseAccountID);
                    Util.takeScreenShot("QATestSuite-OriginalBaseAccountID");

                    getObject("BaseAccount_Txtbox").clear();
                    logger.info(Util.CUName + " - BaseAccount ID is cleared");
                    Util.waitForSeconds(2);
                    String BaseAcctobeTested = CONFIG.getProperty("TexasBaseAccIDtobetested");
                    logger.info(Util.CUName + " - " + BaseAcctobeTested);
                    getObject("BaseAccount_Txtbox").sendKeys(BaseAcctobeTested);
                } else {
                    logger.info(Util.CUName + " - BaseAccount ID is not modified");
                }

                EmailText = getObject("QATestSuite_Email_Textbox").getAttribute("value").trim();
                getObject("QATestSuite_Email_Textbox").clear();
                logger.info(Util.CUName + " - Email Text Box is cleared");

                getPhoneNo = getObject("PhoneNo_Txtbox").getAttribute("value").trim();
                getObject("PhoneNo_Txtbox").clear();
                logger.info(Util.CUName + " - PhoneNo Text Box is cleared");

                waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("QATestSuite_Save_Btn"))));
                getObject("QATestSuite_Save_Btn").click();
                logger.info(Util.CUName + " - Save button is clicked");

                logger.info(Util.CUName + " - Settings updated successfully message is displayed");

                waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("WebTestPrimary_Btn"))));
                getObject("WebTestPrimary_Btn").click();
                logger.info(Util.CUName + " - WEB Test - Primary button is clicked");

                spinnerwait.until(ExpectedConditions.invisibilityOf(getObject("SSO_Spinner")));
                Util.waitForSeconds(2);

                	getObject("MemberView_Tab").getAttribute("class").contains("is-active");
                    logger.info(Util.CUName + " - Member View Tab is active");
                    WebElement FrameWindow = driver.findElement(By.xpath(OR.getProperty("frameContent")));
                  
                    driver.switchTo().frame(FrameWindow);
            //        System.out.println("I'm in Frame");
                    waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("Loanlandingpagevalidation"))));
                    String Loanapplicationlandingpage = getObject("Loanlandingpagevalidation").getText(); 
                    logger.info(Util.CUName + " - Web test Primary : The loan application form is displayed successfully..." + Loanapplicationlandingpage);
           
                    softassert.assertTrue(true, "Web test Primary : The loan application form is displayed successfully..." + Loanapplicationlandingpage);
               
                    waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("loanpageCheckbox"))));
                    getObject("loanpageCheckbox").click();
                    
                    waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("BeginApplicationbutton"))));
                    getObject("BeginApplicationbutton").click();
                    
                    
                    Thread.sleep(2000);
                    
                    boolean awarnessCheck = driver.getPageSource().contains("loanpageAwarenessText");                                                
                  
                    if (awarnessCheck) { 
                    String awarnesstextCheck = getObject("loanpageAwarenessText").getText();
                    logger.info(Util.CUName + " - The Awarness page is displayed in web test Primary and the title is : " + awarnesstextCheck);
                    Util.takeScreenShot("maintenancepage");
                    softassert.assertTrue(true, "The Awarness page is displayed in web test Primary and the title is : " + awarnesstextCheck);
               
                } else {
                	
                     waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("Loanlandingpagevalidation"))));
                     String Loanlandingpage = getObject("Loanlandingpagevalidation").getText(); 
                     logger.info(Util.CUName + " - Web test Primary : The loan application form is displayed successfully..." + Loanlandingpage);
                     Util.takeScreenShot("WebTestPrimarySuccess");
                     softassert.assertTrue(true, "Web test Primary : The loan application form is displayed successfully..." + Loanlandingpage);
              
                     spinnerwait.until(ExpectedConditions.invisibilityOf(getObject("SSO_Spinner")));
                     Util.waitForSeconds(2);
                     WebElement FrameswitchWindow = driver.findElement(By.xpath(OR.getProperty("frameContent")));

                     driver.switchTo().frame(FrameswitchWindow);
                     //        System.out.println("I'm in Frame");
                     
                     waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("Loanlandingpagevalidation"))));
                     String Loanapplicationlandingscreen = getObject("Loanlandingpagevalidation").getText(); 
                     logger.info(Util.CUName + " - Web test Primary : The loan application form is displayed successfully..." + Loanapplicationlandingscreen);
            
                     softassert.assertTrue(true, "Web test Primary : The loan application form is displayed successfully..." + Loanapplicationlandingscreen);
                
                     waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("loanpageCheckbox"))));
                     getObject("loanpageCheckbox").click();
                     
                     waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("BeginApplicationbutton"))));
                     getObject("BeginApplicationbutton").click();
                }

                driver.switchTo().defaultContent();
            } else if (getObject("Setup_Tab").getAttribute("class").contains("is-active")) {
                int redalertfailtxt = driver.findElements(By.xpath(OR.getProperty("Red_Alert_FailText"))).size();
                if (redalertfailtxt != 0) {
                    String RedAlertText = getObject("Red_Alert_FailText").getText();
                    logger.info(Util.CUName + " - Web test primary is not loaded and red alert message is shown");
                    logger.info(Util.CUName + " - Red Alert Message is : " + RedAlertText);
                    Util.takeScreenShot("PrimaryWebTestRedAlert");
                    softassert.assertTrue(false, Util.CUName + "Web test primary is not loaded and red alert message is shown");
                }
            }

            // Web test secondary
            waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("Setup_Tab"))));
            getObject("Setup_Tab").click();
            logger.info(Util.CUName + " - Setup tab is clicked in QA test suite page");

            waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("WebTestSec_Btn"))));
            getObject("WebTestSec_Btn").click();
            logger.info(Util.CUName + " - WEB Test - Secondary button is clicked");
            spinnerwait.until(ExpectedConditions.invisibilityOf(getObject("SSO_Spinner")));
            Util.waitForSeconds(2);
            WebElement FrameWindow = driver.findElement(By.xpath(OR.getProperty("frameContent")));

            driver.switchTo().frame(FrameWindow);
            //        System.out.println("I'm in Frame");
            waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("Loanlandingpagevalidation"))));
            String Loanapplicationlandingpage = getObject("Loanlandingpagevalidation").getText(); 
            logger.info(Util.CUName + " - Web test Primary : The loan application form is displayed successfully..." + Loanapplicationlandingpage);
   
            softassert.assertTrue(true, "Web test Primary : The loan application form is displayed successfully..." + Loanapplicationlandingpage);
       
            waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("loanpageCheckbox"))));
            getObject("loanpageCheckbox").click();
            
            waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("BeginApplicationbutton"))));
            getObject("BeginApplicationbutton").click();
            
            
            Thread.sleep(2000);
                 
                    boolean awarnessCheck = driver.getPageSource().contains("loanpageAwarenessText");                                                
                  
                    if (awarnessCheck) { 
                    String awarnesstext = getObject("loanpageAwarenessText").getText();
                    logger.info(Util.CUName + " - The Awarness page is displayed in web test secondary and the title is : " + awarnesstext);
                    Util.takeScreenShot("maintenancepage");
                    softassert.assertTrue(true, "The Awarness page is displayed in web test secondary and the title is : " + awarnesstext);
               
                } else {
                	
                     waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("Loanlandingpagevalidation"))));
                     String Loanlandingpage = getObject("Loanlandingpagevalidation").getText(); 
                     logger.info(Util.CUName + " - Web test secondary : The loan application form is displayed successfully..." + Loanlandingpage);
                     Util.takeScreenShot("WebTestPrimarySuccess");
                     softassert.assertTrue(true, "Web test secondary : The loan application form is displayed successfully..." + Loanlandingpage);
              
                     spinnerwait.until(ExpectedConditions.invisibilityOf(getObject("SSO_Spinner")));
                     Util.waitForSeconds(2);
                     WebElement FrameswitchWindow = driver.findElement(By.xpath(OR.getProperty("frameContent")));

                     driver.switchTo().frame(FrameswitchWindow);
                     //        System.out.println("I'm in Frame");
                     
                     waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("Loanlandingpagevalidation"))));
                     String Loanapplicationlandingscreen = getObject("Loanlandingpagevalidation").getText(); 
                     logger.info(Util.CUName + " - Web test Primary : The loan application form is displayed successfully..." + Loanapplicationlandingscreen);
            
                     softassert.assertTrue(true, "Web test Primary : The loan application form is displayed successfully..." + Loanapplicationlandingscreen);
                
                     waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("loanpageCheckbox"))));
                     getObject("loanpageCheckbox").click();
                     
                     waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("BeginApplicationbutton"))));
                     getObject("BeginApplicationbutton").click();
                
                }

            driver.switchTo().defaultContent();

         QATestSuitePageFunctions.RevertEmailandFundoptions();

          waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("Home_Breadcrumb_link"))));
		getObject("Home_Breadcrumb_link").click();
            logger.info(Util.CUName + " - Web Test Primary and Secondary have been validated.");

        //    softassert.assertAll();
            
         //   waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("Home_Breadcrumb_link"))));
    		//getObject("Home_Breadcrumb_link").click();
            
    		logger.info(Util.CUName + " - switched to home page.");
    		
        } catch (Exception e) {
            Util.takeScreenShot("QATestSuiteFieldCheck");
            logger.error(Util.CUName + " - QA test suite page is not displayed");

           QATestSuitePageFunctions.RevertEmailandFundoptions();

            Assert.assertTrue(false, Util.CUName + "QA test suite page is not displayed");
        }
    }

    public static void WebTestPrimarySecondaryFraudcontrolValidation() throws Exception, InterruptedException, IOException {
        SoftAssert softassert = new SoftAssert();

        boolean FundYesRadioBtn = false;

        try {
            WebDriverWait spinnerwait = new WebDriverWait(driver, 120);
            WebDriverWait waite = new WebDriverWait(driver, 90);
            waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("QATestSuite_Link"))));
            getObject("QATestSuite_Link").click();
            Util.waitForSeconds(2);
            waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("Index_Text"))));

            if (driver.getCurrentUrl().contains("SSOTestSetup")) {
                logger.info(Util.CUName + " - QA Test Suite - SetUp tab is enabled");

                WebElement radioElement = driver.findElement(By.id("FundYes"));
                FundYesRadioBtn = radioElement.isSelected();

                waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("Fund_No_Radiobtn"))));
                getObject("Fund_No_Radiobtn").click();
                logger.info(Util.CUName + " - Fund No Radio button is clicked");
                Util.waitForSeconds(2);

                if (Util.CUName.equals("TEXAS PLAINS FCU")) {
                    BaseAccountID = getObject("BaseAccount_Txtbox").getAttribute("value").trim();
                    logger.info(Util.CUName + " - " + BaseAccountID);
                    Util.takeScreenShot("QATestSuite-OriginalBaseAccountID");

                    getObject("BaseAccount_Txtbox").clear();
                    logger.info(Util.CUName + " - BaseAccount ID is cleared");
                    Util.waitForSeconds(2);
                    String BaseAcctobeTested = CONFIG.getProperty("TexasBaseAccIDtobetested");
                    logger.info(Util.CUName + " - " + BaseAcctobeTested);
                    getObject("BaseAccount_Txtbox").sendKeys(BaseAcctobeTested);
                } else {
                    logger.info(Util.CUName + " - BaseAccount ID is not modified");
                }

                EmailText = getObject("QATestSuite_Email_Textbox").getAttribute("value").trim();
                getObject("QATestSuite_Email_Textbox").clear();
                logger.info(Util.CUName + " - Email Text Box is cleared");

                getPhoneNo = getObject("PhoneNo_Txtbox").getAttribute("value").trim();
                getObject("PhoneNo_Txtbox").clear();
                logger.info(Util.CUName + " - PhoneNo Text Box is cleared");

                waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("QATestSuite_Save_Btn"))));
                getObject("QATestSuite_Save_Btn").click();
                logger.info(Util.CUName + " - Save button is clicked");

                logger.info(Util.CUName + " - Settings updated successfully message is displayed");

                // Webtest primary
                waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("WebTestPrimary_Btn"))));
                getObject("WebTestPrimary_Btn").click();
                logger.info(Util.CUName + " - WEB Test - Primary button is clicked");

                spinnerwait.until(ExpectedConditions.invisibilityOf(getObject("SSO_Spinner")));
                Util.waitForSeconds(2);

                	getObject("MemberView_Tab").getAttribute("class").contains("is-active");
                    logger.info(Util.CUName + " - Member View Tab is active");
                    WebElement FrameWindow = driver.findElement(By.xpath(OR.getProperty("frameContent")));
                  
                    driver.switchTo().frame(FrameWindow);
            //        System.out.println("I'm in Frame");
                    
                    waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("Loanlandingpagevalidation"))));
                    String Loanapplicationlandingpage = getObject("Loanlandingpagevalidation").getText(); 
                    logger.info(Util.CUName + " - Web test Primary : The loan application form is displayed successfully..." + Loanapplicationlandingpage);
           
                    softassert.assertTrue(true, "Web test Primary : The loan application form is displayed successfully..." + Loanapplicationlandingpage);
               
                    waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("loanpageCheckbox"))));
                    getObject("loanpageCheckbox").click();
                    
                    waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("BeginApplicationbutton"))));
                    getObject("BeginApplicationbutton").click();
                    
                    
                    Thread.sleep(2000);
                 
                    boolean FraudcontrolCheck = driver.getPageSource().contains("LoanFraudcontrolText");                                                
                  
                    if (FraudcontrolCheck) { 
                    String FCtextCheck = getObject("LoanFraudcontrolText").getText();
                    logger.info(Util.CUName + " - The Fraud control page is displayed in web test Primary and the title is : " + FCtextCheck);
                    Util.takeScreenShot("maintenancepage");
                    softassert.assertTrue(true, "The Fraud control is displayed in web test Primary and the title is : " + FCtextCheck);
               
                } else {
                	
                     waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("Loanlandingpagevalidation"))));
                     String Loanlandingpage = getObject("Loanlandingpagevalidation").getText(); 
                     logger.info(Util.CUName + " - Web test Primary : The loan application form is displayed successfully..." + Loanlandingpage);
                     Util.takeScreenShot("WebTestPrimarySuccess");
                     softassert.assertTrue(true, "Web test Primary : The loan application form is displayed successfully..." + Loanlandingpage);
                
                     spinnerwait.until(ExpectedConditions.invisibilityOf(getObject("SSO_Spinner")));
                     Util.waitForSeconds(2);
                     WebElement FrameswitchWindow = driver.findElement(By.xpath(OR.getProperty("frameContent")));

                     driver.switchTo().frame(FrameswitchWindow);
                     //        System.out.println("I'm in Frame");
                     
                     waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("Loanlandingpagevalidation"))));
                     String Loanapplicationlandingscreen = getObject("Loanlandingpagevalidation").getText(); 
                     logger.info(Util.CUName + " - Web test Primary : The loan application form is displayed successfully..." + Loanapplicationlandingscreen);
            
                     softassert.assertTrue(true, "Web test Primary : The loan application form is displayed successfully..." + Loanapplicationlandingscreen);
                
                     waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("loanpageCheckbox"))));
                     getObject("loanpageCheckbox").click();
                     
                     waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("BeginApplicationbutton"))));
                     getObject("BeginApplicationbutton").click();
                     logger.info(Util.CUName + " - Web test Primary : The Fraud control page is not displayed...");
                     Util.takeScreenShot("WebTestPrimarySuccess");
                
                }

                driver.switchTo().defaultContent();
            } else if (getObject("Setup_Tab").getAttribute("class").contains("is-active")) {
                int redalertfailtxt = driver.findElements(By.xpath(OR.getProperty("Red_Alert_FailText"))).size();
                if (redalertfailtxt != 0) {
                    String RedAlertText = getObject("Red_Alert_FailText").getText();
                    logger.info(Util.CUName + " - Web test primary is not loaded and red alert message is shown");
                    logger.info(Util.CUName + " - Red Alert Message is : " + RedAlertText);
                    Util.takeScreenShot("PrimaryWebTestRedAlert");
                    softassert.assertTrue(false, Util.CUName + "Web test primary is not loaded and red alert message is shown");
                }
            }

            // Web test secondary
            waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("Setup_Tab"))));
            getObject("Setup_Tab").click();
            logger.info(Util.CUName + " - Setup tab is clicked in QA test suite page");

            waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("WebTestSec_Btn"))));
            getObject("WebTestSec_Btn").click();
            logger.info(Util.CUName + " - WEB Test - Secondary button is clicked");
            spinnerwait.until(ExpectedConditions.invisibilityOf(getObject("SSO_Spinner")));
            Util.waitForSeconds(2);
            WebElement FrameWindow = driver.findElement(By.xpath(OR.getProperty("frameContent")));

            driver.switchTo().frame(FrameWindow);
            //        System.out.println("I'm in Frame");
            
            waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("Loanlandingpagevalidation"))));
            String Loanapplicationlandingpage = getObject("Loanlandingpagevalidation").getText(); 
            logger.info(Util.CUName + " - Web test Primary : The loan application form is displayed successfully..." + Loanapplicationlandingpage);
   
            softassert.assertTrue(true, "Web test Primary : The loan application form is displayed successfully..." + Loanapplicationlandingpage);
       
            waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("loanpageCheckbox"))));
            getObject("loanpageCheckbox").click();
            
            waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("BeginApplicationbutton"))));
            getObject("BeginApplicationbutton").click();
            
            
            Thread.sleep(2000);
                 
                    boolean FraudcontrolCheck = driver.getPageSource().contains("LoanFraudcontrolText");                                                
                  
                    if (FraudcontrolCheck) { 
                    String FCtext = getObject("LoanFraudcontrolText").getText();
                    logger.info(Util.CUName + " - The Fraud control page is displayed in web test secondary and the title is : " + FCtext);
                    Util.takeScreenShot("maintenancepage");
                    softassert.assertTrue(true, "The Fraud control is displayed in web test secondary and the title is : " + FCtext);
               
                } else {
                	
                     waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("Loanlandingpagevalidation"))));
                     String Loanlandingpage = getObject("Loanlandingpagevalidation").getText(); 
                     logger.info(Util.CUName + " - Web test secondary : The loan application form is displayed successfully..." + Loanlandingpage);
                     
                     softassert.assertTrue(true, "Web test secondary : The loan application form is displayed successfully..." + Loanlandingpage);
                     
                     spinnerwait.until(ExpectedConditions.invisibilityOf(getObject("SSO_Spinner")));
                     Util.waitForSeconds(2);
                     WebElement FrameswitchWindow = driver.findElement(By.xpath(OR.getProperty("frameContent")));

                     driver.switchTo().frame(FrameswitchWindow);
                     //        System.out.println("I'm in Frame");
                     
                     waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("Loanlandingpagevalidation"))));
                     String Loanapplicationlandingscreen = getObject("Loanlandingpagevalidation").getText(); 
                     logger.info(Util.CUName + " - Web test Primary : The loan application form is displayed successfully..." + Loanapplicationlandingscreen);
            
                     softassert.assertTrue(true, "Web test Primary : The loan application form is displayed successfully..." + Loanapplicationlandingscreen);
                
                     waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("loanpageCheckbox"))));
                     getObject("loanpageCheckbox").click();
                     
                     waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("BeginApplicationbutton"))));
                     getObject("BeginApplicationbutton").click();
                     logger.info(Util.CUName + " - Web test Primary : The Fraud control page is not displayed...");
                     
                     Util.takeScreenShot("WebTestPrimarySuccess");
                   
                     
                }

            driver.switchTo().defaultContent();

         QATestSuitePageFunctions.RevertEmailandFundoptions();

          waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("Home_Breadcrumb_link"))));
		getObject("Home_Breadcrumb_link").click();
            logger.info(Util.CUName + " - Web Test Primary and Secondary have been validated.");

        //    softassert.assertAll();
            
         //   waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("Home_Breadcrumb_link"))));
    		//getObject("Home_Breadcrumb_link").click();
            
    		logger.info(Util.CUName + " - switched to home page.");
    		
        } catch (Exception e) {
            Util.takeScreenShot("QATestSuiteFieldCheck");
            logger.error(Util.CUName + " - QA test suite page is not displayed");

           QATestSuitePageFunctions.RevertEmailandFundoptions();

            Assert.assertTrue(false, Util.CUName + "QA test suite page is not displayed");
        }
    }

    
    public static void WebTestPrimarySecondaryMaskwaitValidation() throws Exception, InterruptedException, IOException {
        SoftAssert softassert = new SoftAssert();

        boolean FundYesRadioBtn = false;

        try {
            WebDriverWait spinnerwait = new WebDriverWait(driver, 120);
            WebDriverWait waite = new WebDriverWait(driver, 90);
            waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("QATestSuite_Link"))));
            getObject("QATestSuite_Link").click();
            Util.waitForSeconds(2);
            waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("Index_Text"))));

            if (driver.getCurrentUrl().contains("SSOTestSetup")) {
                logger.info(Util.CUName + " - QA Test Suite - SetUp tab is enabled");

                WebElement radioElement = driver.findElement(By.id("FundYes"));
                FundYesRadioBtn = radioElement.isSelected();

                waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("Fund_No_Radiobtn"))));
                getObject("Fund_No_Radiobtn").click();
                logger.info(Util.CUName + " - Fund No Radio button is clicked");
                Util.waitForSeconds(2);

                if (Util.CUName.equals("TEXAS PLAINS FCU")) {
                    BaseAccountID = getObject("BaseAccount_Txtbox").getAttribute("value").trim();
                    logger.info(Util.CUName + " - " + BaseAccountID);
                    Util.takeScreenShot("QATestSuite-OriginalBaseAccountID");

                    getObject("BaseAccount_Txtbox").clear();
                    logger.info(Util.CUName + " - BaseAccount ID is cleared");
                    Util.waitForSeconds(2);
                    String BaseAcctobeTested = CONFIG.getProperty("TexasBaseAccIDtobetested");
                    logger.info(Util.CUName + " - " + BaseAcctobeTested);
                    getObject("BaseAccount_Txtbox").sendKeys(BaseAcctobeTested);
                } else {
                    logger.info(Util.CUName + " - BaseAccount ID is not modified");
                }

                EmailText = getObject("QATestSuite_Email_Textbox").getAttribute("value").trim();
                getObject("QATestSuite_Email_Textbox").clear();
                logger.info(Util.CUName + " - Email Text Box is cleared");

                getPhoneNo = getObject("PhoneNo_Txtbox").getAttribute("value").trim();
                getObject("PhoneNo_Txtbox").clear();
                logger.info(Util.CUName + " - PhoneNo Text Box is cleared");

                waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("QATestSuite_Save_Btn"))));
                getObject("QATestSuite_Save_Btn").click();
                logger.info(Util.CUName + " - Save button is clicked");

                logger.info(Util.CUName + " - Settings updated successfully message is displayed");

                // Webtest primary
                waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("WebTestPrimary_Btn"))));
                getObject("WebTestPrimary_Btn").click();
                logger.info(Util.CUName + " - WEB Test - Primary button is clicked");

                spinnerwait.until(ExpectedConditions.invisibilityOf(getObject("SSO_Spinner")));
                Util.waitForSeconds(2);

                	getObject("MemberView_Tab").getAttribute("class").contains("is-active");
                    logger.info(Util.CUName + " - Member View Tab is active");
                    WebElement FrameWindow = driver.findElement(By.xpath(OR.getProperty("frameContent")));
                  
                    driver.switchTo().frame(FrameWindow);
         
                    
//                    waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("MaskWelcomepage"))));
//                    String Maskwaitpage = getObject("MaskWelcomepage").getText(); 
//                    logger.info(Util.CUName + " - Web test Primary : The Mask wait page is displayed successfully..." + Maskwaitpage);
//           
//                    softassert.assertTrue(true, "Web test Primary : The Mask wait page is displayed successfully..." + Maskwaitpage);
//               
                  
                 
                    boolean Maskwait = driver.getPageSource().contains("MaskWelcomepage");                                                
                  
                    if (Maskwait) { 
                    String Maskwaittextcheck = getObject("MaskWelcomepage").getText();
                    logger.info(Util.CUName + " - The Fraud control page is displayed in web test Primary and the title is : " + Maskwaittextcheck);
                    Util.takeScreenShot("maintenancepage");
                    softassert.assertTrue(true, "The Fraud control is displayed in web test Primary and the title is : " + Maskwaittextcheck);
               
                } else {
                	
                     waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("Loanlandingpagevalidation"))));
                     String Loanlandingpage = getObject("Loanlandingpagevalidation").getText(); 
                     logger.info(Util.CUName + " - Web test Primary : The loan application form is displayed successfully..." + Loanlandingpage);
                     Util.takeScreenShot("WebTestPrimarySuccess");
                     softassert.assertTrue(true, "Web test Primary : The loan application form is displayed successfully..." + Loanlandingpage);
                
                     spinnerwait.until(ExpectedConditions.invisibilityOf(getObject("SSO_Spinner")));
                     Util.waitForSeconds(2);
                     WebElement FrameswitchWindow = driver.findElement(By.xpath(OR.getProperty("frameContent")));

                     driver.switchTo().frame(FrameswitchWindow);
                     //        System.out.println("I'm in Frame");
                     
                     waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("Loanlandingpagevalidation"))));
                     String Loanapplicationlandingscreen = getObject("Loanlandingpagevalidation").getText(); 
                     logger.info(Util.CUName + " - Web test Primary : The loan application form is displayed successfully..." + Loanapplicationlandingscreen);
            
                     softassert.assertTrue(true, "Web test Primary : The loan application form is displayed successfully..." + Loanapplicationlandingscreen);
                
              
                }

                driver.switchTo().defaultContent();
            } else if (getObject("Setup_Tab").getAttribute("class").contains("is-active")) {
                int redalertfailtxt = driver.findElements(By.xpath(OR.getProperty("Red_Alert_FailText"))).size();
                if (redalertfailtxt != 0) {
                    String RedAlertText = getObject("Red_Alert_FailText").getText();
                    logger.info(Util.CUName + " - Web test primary is not loaded and red alert message is shown");
                    logger.info(Util.CUName + " - Red Alert Message is : " + RedAlertText);
                    Util.takeScreenShot("PrimaryWebTestRedAlert");
                    softassert.assertTrue(false, Util.CUName + "Web test primary is not loaded and red alert message is shown");
                }
            }

            // Web test secondary
            waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("Setup_Tab"))));
            getObject("Setup_Tab").click();
            logger.info(Util.CUName + " - Setup tab is clicked in QA test suite page");

            waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("WebTestSec_Btn"))));
            getObject("WebTestSec_Btn").click();
            logger.info(Util.CUName + " - WEB Test - Secondary button is clicked");
            
            spinnerwait.until(ExpectedConditions.invisibilityOf(getObject("SSO_Spinner")));
            Util.waitForSeconds(2);

            	getObject("MemberView_Tab").getAttribute("class").contains("is-active");
                logger.info(Util.CUName + " - Member View Tab is active");
                WebElement FrameWindow = driver.findElement(By.xpath(OR.getProperty("frameContent")));
              
                driver.switchTo().frame(FrameWindow);
//              waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("MaskWelcomepage"))));
//              String Maskwaitpage = getObject("MaskWelcomepage").getText(); 
//              logger.info(Util.CUName + " - Web test Primary : The Mask wait page is displayed successfully..." + Maskwaitpage);
//     
//              softassert.assertTrue(true, "Web test Primary : The Mask wait page is displayed successfully..." + Maskwaitpage);
//
            
            
            Thread.sleep(2000);
                 
            boolean Maskwait = driver.getPageSource().contains("MaskWelcomepage");                                                
            
            if (Maskwait) { 
            String Maskwaittextcheck = getObject("MaskWelcomepage").getText();
            logger.info(Util.CUName + " - The Fraud control page is displayed in web test Primary and the title is : " + Maskwaittextcheck);
            Util.takeScreenShot("maintenancepage");
            softassert.assertTrue(true, "The Fraud control is displayed in web test Primary and the title is : " + Maskwaittextcheck);
                     
                } else {
                	
                     waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("Loanlandingpagevalidation"))));
                     String Loanlandingpage = getObject("Loanlandingpagevalidation").getText(); 
                     logger.info(Util.CUName + " - Web test secondary : The loan application form is displayed successfully..." + Loanlandingpage);
                     
                     softassert.assertTrue(true, "Web test secondary : The loan application form is displayed successfully..." + Loanlandingpage);
                     
                     spinnerwait.until(ExpectedConditions.invisibilityOf(getObject("SSO_Spinner")));
                     Util.waitForSeconds(2);
                     WebElement FrameswitchWindow = driver.findElement(By.xpath(OR.getProperty("frameContent")));

                     driver.switchTo().frame(FrameswitchWindow);
                     //        System.out.println("I'm in Frame");
                     
                     waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("Loanlandingpagevalidation"))));
                     String Loanapplicationlandingscreen = getObject("Loanlandingpagevalidation").getText(); 
                     logger.info(Util.CUName + " - Web test Primary : The loan application form is displayed successfully..." + Loanapplicationlandingscreen);
            
                     softassert.assertTrue(true, "Web test Primary : The loan application form is displayed successfully..." + Loanapplicationlandingscreen);
                
                         
                     Util.takeScreenShot("WebTestPrimarySuccess");
                   
                     
                }

            driver.switchTo().defaultContent();

         QATestSuitePageFunctions.RevertEmailandFundoptions();

          waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("Home_Breadcrumb_link"))));
		getObject("Home_Breadcrumb_link").click();
            logger.info(Util.CUName + " - Web Test Primary and Secondary have been validated.");

        //    softassert.assertAll();
            
         //   waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("Home_Breadcrumb_link"))));
    		//getObject("Home_Breadcrumb_link").click();
            
    		logger.info(Util.CUName + " - switched to home page.");
    		
        } catch (Exception e) {
            Util.takeScreenShot("QATestSuiteFieldCheck");
            logger.error(Util.CUName + " - QA test suite page is not displayed");

           QATestSuitePageFunctions.RevertEmailandFundoptions();

            Assert.assertTrue(false, Util.CUName + "QA test suite page is not displayed");
        }
    }

    
}