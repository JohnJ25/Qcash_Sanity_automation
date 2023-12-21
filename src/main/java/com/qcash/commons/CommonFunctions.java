	package com.qcash.commons;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Calendar;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import com.qcash.testbase.TestBase;
import com.qcash.utilities.*;

public class CommonFunctions extends TestBase {
	static Logger logger = LogManager.getLogger(CommonFunctions.class);
	public static String downloadPath = System.getProperty("user.dir")+"\\Downloads";

	/**
	 * Logs in to the Decision Link
	 * @throws InterruptedException 
	 */
	public static void LaunchUrl() throws Exception
	{
		try {
			WebDriverWait waite = new WebDriverWait(driver,120);
			driver.get(CONFIG.getProperty("InteQ_URL"));
			logger.info("Clicked InteQ URL and landed in Login page");
		}
			catch(Exception e) {
				e.getMessage();
				Util.takeScreenShot("LaunchURL");	
				System.out.println("Screenshot taken for Launch URL Error");
			}
	}

	public static void appUserLogin(String username,String password) throws Exception
	
	{
		try{
			WebDriverWait waite = new WebDriverWait(driver,20);
			Util.waitForPageToLoad();
			waite.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("Login_Username"))));
			getObject("Login_Username").sendKeys(username);
			waite.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("Login_Password"))));
			getObject("Login_Password").sendKeys(password);
			getObject("Login_SigninButton").click();
			waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@alt = 'settings']")));
			logger.info("User Login Successful");
			Util.waitForSeconds(1);
		}catch(Exception e) {
			e.getMessage();
			Util.takeScreenShot("LoginError");	
			System.out.println("Screenshot taken for Login Error");
		}
		

	}

	public static void QCashLogin(String username, String password) throws IOException, InterruptedException {
		try {

		
		Util.waitForPageToLoad();
		WebDriverWait waite = new WebDriverWait(driver, 120);
		waite.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("Login_Username"))));
		getObject("Login_Username").sendKeys(username);	
		logger.info("Entered username");
		waite.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("Login_Password"))));
		getObject("Login_Password").sendKeys(password);
		logger.info("Entered password");
		waite.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("Login_SigninButton"))));
		getObject("Login_SigninButton").click();
		logger.info("Clicked Signin button");
		Util.waitForSeconds(2);
		if(Util.isElementDisplayed(By.xpath(OR.getProperty("QCash_LandingPage"))))
		{
		logger.info("User login is successful and landed on Home page");
		}
		else
		{
			Util.takeScreenShot("LoginValidation");
			logger.info("User login is not successful");
			Assert.assertTrue(false, " Issue in Login to Qash");
		}
		}
		catch(Exception e)
		{
			Util.takeScreenShot("LoginValidation");
			logger.info("User login is not successful");
		}
	/*	else if(Util.isElementDisplayed(By.xpath(OR.getProperty("QCash_LandingPage"))))
		{
			logger.info("User password has been expired");
		}*/
	}
	
	
	public static void QCashInternalLogin(String username, String password) throws IOException, InterruptedException {
		try {

		
		WebDriverWait waite = new WebDriverWait(driver, 120);
		waite.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("InternalLoginlink"))));
		getObject("InternalLoginlink").click();
		logger.info("Clicked Internal User Login link");
		Util.waitForSeconds(4);
		//waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("Microsoftlogin_email"))));
		if(Util.isElementPresent(By.xpath(OR.getProperty("Microsoftlogin_email"))))
		{
			waite.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("Microsoftlogin_email"))));
			getObject("Microsoftlogin_email").sendKeys(CONFIG.getProperty("MicrosoftEmail"));	
			logger.info("Entered username");
			waite.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("Microsoftlogin_Submitbtn"))));
			getObject("Microsoftlogin_Submitbtn").click();
			logger.info("Clicked Next Button");
			waite.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("Microsoftlogin_password"))));
			getObject("Microsoftlogin_password").sendKeys(CONFIG.getProperty("MicrosoftPassword"));
			logger.info("Entered password");
			waite.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("Microsoftlogin_Submitbtn"))));
			getObject("Microsoftlogin_Submitbtn").click();
			logger.info("Clicked SignIn Button");
			
			waite.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("Microsoftlogin_Submitbtn"))));
			getObject("Microsoftlogin_Submitbtn").click();
			logger.info("Clicked Yes Button");
		}
		else
		{
			
		logger.info("User login is successful and landed on Home page");
		}
		}
			
	catch(Exception e)
		{
			Util.takeScreenShot("LoginValidation");
			logger.info("User login is not successful");
		}}
	
	public static void UserLogout() throws Exception, InterruptedException, IOException{ 
		WebDriverWait waite = new WebDriverWait(driver,120);
		Util.waitForSeconds(3);
		waite.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("SuperUser_link"))));
		getObject("SuperUser_link").click();
		Util.waitForSeconds(3);
		waite.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("Logout"))));
		getObject("Logout").click();
		Util.waitForSeconds(2);
		Util.waitForPageToLoad();
		Assert.assertEquals(true, driver.getCurrentUrl().contains("/login"));
		waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("Login_Username"))));
		logger.info("Logout action successful, Login page displayed.");
	}
	
	
	
	public static String PreviousDateSelector(String dateString, String minusDays) throws ParseException {
		// Create a date formatter using your format string
		DateFormat QcashdateFormat = new SimpleDateFormat("MM/dd/yyyy");

		// Parse the given date string into a Date object.
		// Note: This can throw a ParseException.
		//LocalDateTime CurrentDate = LocalDateTime.now();
		Date myDate = QcashdateFormat.parse(dateString);

		// Use the Calendar class to subtract one day
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(myDate);
	//	logger.info("TEST YEAR :   " +Calendar.DAY_OF_YEAR);
		
		if(minusDays == "0")
		{
			calendar.add(Calendar.DAY_OF_YEAR, 0);
		}
				
		if(minusDays == "3")
		{
			calendar.add(Calendar.DAY_OF_YEAR, -3);
		}
		else if (minusDays == "6")
		{
			calendar.add(Calendar.DAY_OF_YEAR, -6);
		}

		// Use the date formatter to produce a formatted date string
		Date resultDate = calendar.getTime();
		String result = QcashdateFormat.format(resultDate);

		return result;
	}
	
	public static void verifyPage(String page) throws InterruptedException{
		
		try
		{boolean elementpresent;
		Util.waitForPageToLoad();
		elementpresent=Util.isElementPresent(By.xpath(OR.getProperty(page)));
		assertEquals(true,elementpresent);		
		logger.info(" "+page+"is displayed");	
		}
		catch(Exception e)
		{
			logger.info(" "+page+"is not displayed");	

		}
	}
		
	
	
	public static void closePopup() throws Exception {
		try {
			List<WebElement> closebtns = getObjects("cbc_create_client_closebutton");
			logger.trace("CloseButtons=" + closebtns.size());
			for (WebElement item : closebtns) {
				if (item.isDisplayed() && item.isEnabled()) {
					builder.click(item).build().perform();
					break;
				}
			}
			Util.waitForSeconds(2);
		} catch (Exception e) {
			throw new Exception("Error while closing pop-up. " + e.getMessage());
		}
	}
	
	public static void closeApplication(){
		driver.close();
	}

	public static void verifyElementPresent( ) throws InterruptedException{
	    if(driver.findElements(By.xpath(OR.getProperty("DL_Profile_ManageUsersLink"))).size() != 0){
	    	System.out.println("Element is Present");
	    	}else{
	    	System.out.println("Element is Absent");
	    	}	    
	}
	
	public static boolean isElementPresent(By element) {
		   try {
		       driver.findElement(element);
		       return true;
		   } catch (NoSuchElementException e) {
		       return false;
		   }
		}

	public static File getLatestFilefromDir(String dirPath){
	    File dir = new File(dirPath);
	    File[] files = dir.listFiles();
	    if (files == null || files.length == 0) {
	        return null;
	    }
	
	    File lastModifiedFile = files[0];
	    for (int i = 1; i < files.length; i++) {
	       if (lastModifiedFile.lastModified() < files[i].lastModified()) {
	           lastModifiedFile = files[i];
	       }
	    }
	    return lastModifiedFile;
	}
	
	public static boolean isFileDownloaded(String downloadPath, String fileName) {
		boolean flag = false;
	    File dir = new File(downloadPath);
	    File[] dir_contents = dir.listFiles();
	  	    
	    for (int i = 0; i < dir_contents.length; i++) {
	        if (dir_contents[i].getName().equals(fileName))
	            return flag=true;
	            }

	    return flag;
	}
	
	public static boolean isFileDownloaded_Ext(String dirPath, String ext){
		boolean flag=false;
	    File dir = new File(dirPath);
	    File[] files = dir.listFiles();
	    if (files == null || files.length == 0) {
	        flag = false;
	    }
	    
	    for (int i = 1; i < files.length; i++) {
	    	if(files[i].getName().contains(ext)) {
	    		flag=true;
	    	}
	    }
	    return flag;
	}
	
	/*public static void DeleteAFile(String filename) {
        //get current project path
      //  File file = new File(downloadFilepath + "\\" + filename);
        if (file.exists()) {
		    file.delete();
		    System.out.println("File deleted successfully");
		} else {
		    //delete a file
		    System.out.println("No File exists");
		}
    }
	
	public static void VerifyDownloadedFile() {	
	//    File getLatestFile = getLatestFilefromDir(downloadFilepath);
	    String fileName = getLatestFile.getName();
	    System.out.println(fileName);
	    Assert.assertTrue(fileName.equals("Account_ValueRealizedBrief.docx"), "Downloaded file name is not matching with expected file name");
	    DeleteAFile(fileName);    
	}*/
	
	public static void StaleElementHandleByID (String elementID){
		int count = 0;
		boolean clicked = false;
		while (count < 4 || !clicked){
		    try {
		       WebElement yourSlipperyElement= driver.findElement(By.id(elementID));
		       yourSlipperyElement.click(); 
		       clicked = true;
		     } catch (StaleElementReferenceException e){
		       e.toString();
		       System.out.println("Trying to recover from a stale element :" + e.getMessage());
		       count = count+1;
		     }     
		}
		
	}
	

	
}


