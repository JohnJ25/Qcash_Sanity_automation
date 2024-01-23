package com.qcash.testbase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



import com.qcash.utilities.*;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.bytebuddy.jar.asm.commons.TryCatchBlockSorter;

import org.openqa.selenium.support.ui.Select;

public abstract class TestBase {

	//	public static Logger APP_LOGS;
	protected static Logger logger = LogManager.getLogger(TestBase.class);
	public static Properties CONFIG=null;
	public static Properties OR=null;
	public static Properties DATAFILE=null;
	public static WebDriver dr=null;
	public static Actions builder=null;
	public static EventFiringWebDriver driver=null;
	public static boolean isLoggedIn=false;
	public static Xls_Reader datatable=null;
	public static WebElement element;
	public static String en;
	public static WebDriverWait wait;
	public static String v;
	public static Capabilities cap; 

	/**
	 * Initializes the browser as specified in the config.properties file
	 * @throws Exception 
	 * @throws IOException 
	 */
	public static void  initialize() throws Exception{
		//	if(driver == null){
		CONFIG= new Properties();
		FileInputStream fn =new FileInputStream(".//src//test//resources//confi.properties");
		CONFIG.load(fn);
		
		DATAFILE=new Properties();
		File df =new File(".//src/main//resources//data.properties");
		//DATAFILE.load(df);
		
		
		OR= new Properties();
		fn =new FileInputStream(".//src/main//resources//or.properties");
		OR.load(fn);

		
		/*
		 *  Webdriver Manager Implementation
		 */
		
			if(CONFIG.getProperty("browser").equals("Firefox")){				
			//WebDriverManager.firefoxdriver().setup();	
			WebDriverManager.firefoxdriver().driverVersion("0.29.0").setup();
			
			DesiredCapabilities capabilities=DesiredCapabilities.firefox();
			capabilities.setCapability("marionette", true);
			dr = new FirefoxDriver(capabilities);
			logger.info("Launching Mozilla firefox");

		}else if(CONFIG.getProperty("browser").equals("Safari")){
			dr = new SafariDriver();

		}else if(CONFIG.getProperty("browser").equals("IE")){
			WebDriverManager.iedriver().setup();
			DesiredCapabilities caps = DesiredCapabilities.internetExplorer();    
			caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);    
			dr = new InternetExplorerDriver(caps);
			logger.info("Launching Internet Explorer");

		}
		else if(CONFIG.getProperty("browser").equals("Edge")){		
		WebDriverManager.edgedriver().setup();
		DesiredCapabilities caps = DesiredCapabilities.edge();    
		caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);    
		dr = new EdgeDriver(caps);
		logger.info("Launching Edge Browser");

	}else if(CONFIG.getProperty("browser").equals("Chrome")){
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("excludeSwitches",Arrays.asList("ignore-certificate-errors"));
			options.addArguments("--start-maximized");
			options.addArguments("--ignore-certificate-errors");
			options.addArguments("--disable-save-password-bubble");
			options.setHeadless(false);

			Map<String, Object> prefs = new HashMap<String, Object>();
		     prefs.put("credentials_enable_service", false);
		     prefs.put("profile.password_manager_enabled", false);
		     options.setExperimentalOption("prefs", prefs);
			
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			dr = new ChromeDriver(capabilities);
			logger.info("Launching Chrome Browser");
			
		}
		else if(CONFIG.getProperty("browser").equalsIgnoreCase("headless"))
		{
			dr = new HtmlUnitDriver();
			driver = new EventFiringWebDriver(dr);
		}

		if (!CONFIG.getProperty("browser").equalsIgnoreCase("headless")) {
			driver = new EventFiringWebDriver(dr);
			builder = new Actions(dr);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(140, TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, 20);
			version(v);
		}
		}

	/**
	 * Closes all the windows of the current browser instance 
	 */
	public static void closeBrowser()
	{
		driver.quit();
		logger.debug("Browser Quit");
	}
	
	
	/**
	 * Find and returns the element as soon as it is visible.
	 * @param by the locator of the element
	 * @param timeOut the time in seconds to be waited for
	 * @return the locator of the element
	 */
	public static By findDynamicElement(By by, int timeOut) {
		WebDriverWait wait1 = new WebDriverWait(driver, timeOut);
		wait1.until(ExpectedConditions.visibilityOfElementLocated(by));
		return by;		
	}

	/**
	 * Find and returns the element as soon as it is clickable.
	 * @param by the locator of the element
	 * @param timeOut the time in seconds to be waited for
	 * @return the locator of the element
	 */
	public static By findDynamicElement2(By by, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.elementToBeClickable(by));
		return by;		
	}
	
	public static String folder(String f) throws IOException{
		DateFormat df = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
		Date dateobj = new Date();
		String timeStamp = df.format(dateobj);
		CONFIG= new Properties();
		System.out.println("System Current Dir from testbase folder method is: "+System.getProperty("user.dir"));
		FileInputStream fn =new FileInputStream(".//src//test//resources//confi.properties");
		CONFIG.load(fn);
		String fIle=environment(en)+ "_"+timeStamp;
		File file = new File(System.getProperty("user.dir")+"//QCashBDD//screenshots//"+fIle);
		file.mkdir();
		
		return fIle;
	}
	
	/**
	 * Finds and returns the WebElement corresponding to the key passed.
	 * @param xpathKey the key of the object as specified in the Object Repository.
	 * @return The WebElement if found, null otherwise.
	 */
	public static WebElement getObject(String xpathKey) {
		try{
			JavascriptExecutor js = (JavascriptExecutor)driver;
			WebElement element= driver.findElement(By.xpath(OR.getProperty(xpathKey))) ;
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);",element, "color: Blue; border: 2px solid Purple;");
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);",element, "");
			logger.trace("Object found | "+xpathKey+" | "+element);
			if(element.isEnabled()==false)
				logger.debug("Element "+xpathKey+" is not enabled");
			return driver.findElement(By.xpath(OR.getProperty(xpathKey)));
		}catch(Throwable t){
			logger.error("Unable to find element "+xpathKey+" with xpath "+OR.getProperty(xpathKey));
			return null;
		}
	}
	
	/**
	 * Finds and returns the WebElement corresponding to the key passed.
	 * @param parent the parent element
	 * @param xpathKey the key of the descendant element
	 * @return The WebElement if found, null otherwise.
	 */
	public static WebElement getObjectNested(WebElement parent, String xpathKey) {
		try{
			JavascriptExecutor js = (JavascriptExecutor)driver;
			WebElement element= parent.findElement(By.xpath(OR.getProperty(xpathKey))) ;
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);",element, "color: Blue; border: 2px solid Purple;");
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);",element, "");
			logger.trace("Descendant Object found | "+xpathKey+" | "+element);
			return element;
		}catch(Throwable t){
			logger.debug("Unable to find descendant element "+xpathKey+" with xpath "+OR.getProperty(xpathKey));
			//System.err.println(t);
			return null;
		}
	}

	public static String version(String a){
		String b = "Browser Not Initialized";
		if(driver != null){
			cap = ((RemoteWebDriver) dr).getCapabilities();
			cap.getBrowserName().toLowerCase();
			cap.getPlatform().toString();
			v = cap.getVersion().toString();
		}else
			return b;
		return v;
	}
	public static String environment(String en){
		String envi = CONFIG.getProperty("Test_Website");
		System.out.println(envi);
		return envi;
	}
	public static WebElement LoginGetObject(String xpathKey) {
		try{
			return driver.findElement(By.xpath(OR.getProperty(xpathKey)));
		}catch(Throwable t){
			return null;
		}
	}
	
	/**
	 * Selects the value in a dropdown.
	 * @param xpathKey The key to the xpath of the element, as specified in the Object Repository.
	 * @param visibleText The visible text of the dropdown item to be selected. If empty no value would be selected.
	 * @return true if success, false otherwise
	 */
	public static boolean selectDropdown(String xpathKey, String visibleText)
	{
		boolean success = false;
		try{
		if(visibleText.length()==0)
			logger.debug("Parameter is empty. No changes made in dropdown "+xpathKey);
		else
		{
			WebElement ele = getObject(xpathKey);
			Select sel = new Select(ele);
			sel.selectByVisibleText(visibleText);
		}
		success = true;
		}
		catch(Exception e)
		{
			success = false;
			logger.error("Exception encountered while selecting "+visibleText+" in dropdown "+xpathKey+" : "+e.getMessage());
		}
		return success;
	}
	
	/**
	 * Selects the value in a dropdown WebElement.
	 * @param xpathKey The key to the xpath of the element, as specified in the Object Repository. 
	 * @param index The index of the item to be selected.
	 * @return true if success, false otherwise
	 */
	public static boolean selectDropdown(String xpathKey, int index)
	{
		boolean success = false;
		try{
		WebElement ele = getObject(xpathKey);
		org.openqa.selenium.support.ui.Select sel = new Select(ele);
		sel.selectByIndex(index);
		success = true;
		}
		
		catch(Exception e)
		{
			success = false;
			logger.error("Exception encountered while selecting index "+ index +" in dropdown "+xpathKey+" : "+e.getMessage());
		}
		return success;
	}
	
	/**
	 * Finds and returns all the elements matching the locator
	 * @param xpathKey The key to the xpath of the element, as specified in the Object Repository.  
	 * @return List of elements found
	 */
	public static List<WebElement> getObjects(String xpathKey)
	{
		List<WebElement> list = driver.findElements(By.xpath(OR.getProperty(xpathKey))); 
		return list;		
	}
	
	public static List <WebElement> getLinkObjects(String xpathKey)
	{
		List <WebElement>list = driver.findElements(By.xpath(OR.getProperty(xpathKey)));
		/*for (WebElement ele: list)
		{
			if((ele.getTagName())!="a")
			{
				list.remove(ele);
			}
			else{
				logger.info("Checked element is a link");
			}
		}*/
		
		return list;
	}
	
	/**
	 * Finds and returns all the descendant elements matching the locator
	 * @param parent the parent WebElement
	 * @param xpathKey the key of the descendant element as mentioned in Object Repository 
	 * @return the List of elements found
	 */
	public static List<WebElement> getObjectsNested(WebElement parent, String xpathKey)
	{
		List<WebElement> list = parent.findElements(By.xpath(OR.getProperty(xpathKey))); 
		return list;
	}
	
	/**
	 * Finds the element, clears the value and enters the input provided. Does this only if the field is enabled as well as the input is not empty.
	 * @param xpathkey The key to the xpath of the element, as specified in the Object Repository.  
	 * @param input The value to be entered in the element.
	 * @throws NullPointerException if the element is not found
	 */
	public static void sendInput(String xpathkey, String input) throws NullPointerException
	{
		if(input.length()>0)
		{
			WebElement el = getObject(xpathkey);
			el.click();
		if(el.isEnabled())
		{
			el.clear();
			el.sendKeys(input);
		}
			el = null;
		}
	}
	/**
	 * Finds the element and clicks it. Does this action only if the element is enabled.
	 * @param xpathkey The key to the xpath of the element, as specified in the Object Repository.
	 * @throws NullPointerException if the element is not found
	 */
	public static void click(String xpathkey) throws NullPointerException
	{
		if(getObject(xpathkey).isEnabled())
		{
			getObject(xpathkey).click();
		}
	}

	/**
	 * 
	 * @param monthYear month and year in MM/yyyy format
	 * @throws Exception
	 */
	
	public void chooseMonthYear(String monthYear) throws Exception
	{
		try
		{
			if(monthYear.length()>0)
			{
				String month = monthYear.split("/")[0];
				String year = monthYear.split("/")[1];
				logger.debug("Month="+month+", Year="+year);
		
				String currYear = getObject("calendar_year_currvalue").getText(); 
				while(!currYear.equalsIgnoreCase(year))
				{
					if(Integer.parseInt(currYear)>Integer.parseInt(year))
					{
						getObject("calendar_year_prev").click();
					}
					else if(Integer.parseInt(currYear)<Integer.parseInt(year))
					{
						getObject("calendar_year_next").click();
					}
					currYear = getObject("calendar_year_currvalue").getText();
					logger.trace("UpdatedYear="+currYear);
				}
		
				if(currYear.equalsIgnoreCase(year))
				{
					List<WebElement> months = getObjects("calendar_months");
					months.get(Integer.parseInt(month)-1).click();
				}
			}
		}
		catch(Exception e)
		{
			logger.debug(e.getMessage());
			throw new Exception("Month and Year "+monthYear+" Selection Not Successful");
		}
	}
	
	/**
	 * For Master client set up method below  static function - chooseDayMonthYear1 will be used
	 * @param monthYear month and year in MM/yyyy format
	 * @throws Exception
	 */
	public static void chooseMonthYear1(String monthYear) throws Exception
	{
		try
		{
			if(monthYear.length()>0)
			{
				String month = monthYear.split("/")[0];
				String year = monthYear.split("/")[1];
				logger.debug("Month="+month+", Year="+year);
		
				String currYear = getObject("calendar_year_currvalue").getText(); 
				while(!currYear.equalsIgnoreCase(year))
				{
					if(Integer.parseInt(currYear)>Integer.parseInt(year))
					{
						getObject("calendar_year_prev").click();
					}
					else if(Integer.parseInt(currYear)<Integer.parseInt(year))
					{
						getObject("calendar_year_next").click();
					}
					currYear = getObject("calendar_year_currvalue").getText();
					logger.trace("UpdatedYear="+currYear);
				}
		
				if(currYear.equalsIgnoreCase(year))
				{
					List<WebElement> months = getObjects("calendar_months");
					months.get(Integer.parseInt(month)-1).click();
				}
			}
		}
		catch(Exception e)
		{
			logger.debug(e.getMessage());
			throw new Exception("Month and Year "+monthYear+" Selection Not Successful");
		}
	}
	
	/**
	 * For DataSetUp script in com.fastar.commons package alone, this static method - chooseDayMonthYear1 will be used
	 * @param dayMonthYear date in MM/dd/yyyy format
	 * @throws Exception
	 */
	public static void chooseDayMonthYear1(String dayMonthYear) throws Exception
	{
		try{
		if(dayMonthYear.length()>0)
		{
		String month = dayMonthYear.split("/")[0];
		String day = dayMonthYear.split("/")[1];
		String year = dayMonthYear.split("/")[2];
		logger.debug("Month="+month+", Day="+day+", Year="+year);
		getObject("calendar_year_currvalue").click();
		String currYear = getObject("calendar_year_currvalue").getText(); 
		while(!currYear.equalsIgnoreCase(year))
		{
			if(Integer.parseInt(currYear)>Integer.parseInt(year))
			{
				getObject("calendar_year_prev").click();
			}
			else if(Integer.parseInt(currYear)<Integer.parseInt(year))
			{
				getObject("calendar_year_next").click();
			}
			currYear = getObject("calendar_year_currvalue").getText();
			logger.trace("UpdatedYear="+currYear);
		}
		
		if(currYear.equalsIgnoreCase(year))
		{
			logger.debug("Year selected");
			
			List<WebElement> months = getObjects("calendar_months");
			logger.trace("monthsSize="+months.size());
			logger.trace("monthClickOnItemPosition"+(Integer.parseInt(month)-1));
				months.get(Integer.parseInt(month)-1).click();
				logger.debug("Month selected");
				
			List<WebElement> days = getObjects("calendar_days");
			logger.trace("daysSize="+days.size());
			logger.trace("dayClickOnItemPosition"+(Integer.parseInt(day)-1));
				days.get(Integer.parseInt(day)-1).click();
				logger.debug("Day selected");
		}
		}
		}
		catch(Exception e)
		{
		logger.debug(e.getMessage());
		throw new Exception("Day Month Year "+dayMonthYear+" Selection Not Successful ");
		}
		
	}
	/**
	 * 
	 * @param dayMonthYear date in MM/dd/yyyy format
	 * @throws Exception
	 */
	public void chooseDayMonthYear(String dayMonthYear) throws Exception
	{
		try{
		if(dayMonthYear.length()>0)
		{
		String month = dayMonthYear.split("/")[0];
		String day = dayMonthYear.split("/")[1];
		String year = dayMonthYear.split("/")[2];
		logger.debug("Month="+month+", Day="+day+", Year="+year);
		getObject("calendar_year_currvalue").click();
		String currYear = getObject("calendar_year_currvalue").getText(); 
		while(!currYear.equalsIgnoreCase(year))
		{
			if(Integer.parseInt(currYear)>Integer.parseInt(year))
			{
				getObject("calendar_year_prev").click();
			}
			else if(Integer.parseInt(currYear)<Integer.parseInt(year))
			{
				getObject("calendar_year_next").click();
			}
			currYear = getObject("calendar_year_currvalue").getText();
			logger.trace("UpdatedYear="+currYear);
		}
		
		if(currYear.equalsIgnoreCase(year))
		{
			logger.debug("Year selected");
			
			List<WebElement> months = getObjects("calendar_months");
			logger.trace("monthsSize="+months.size());
			logger.trace("monthClickOnItemPosition"+(Integer.parseInt(month)-1));
				months.get(Integer.parseInt(month)-1).click();
				logger.debug("Month selected");
				
			List<WebElement> days = getObjects("calendar_days");
			logger.trace("daysSize="+days.size());
			logger.trace("dayClickOnItemPosition"+(Integer.parseInt(day)-1));
				days.get(Integer.parseInt(day)-1).click();
				logger.debug("Day selected");
		}
		}
		}
		catch(Exception e)
		{
		logger.debug(e.getMessage());
		throw new Exception("Day Month Year "+dayMonthYear+" Selection Not Successful ");
		}
		
	}
	
	/**
	 * Gets the browser logs.
	 * @return All the log entry messages
	 */
	public String getBrowserLogs()
	{
		String browserlogs = "";
		List<LogEntry> log = driver.manage().logs().get(LogType.BROWSER).getAll();
		for(LogEntry item: log)
		{
			browserlogs = browserlogs + item.getMessage() + " ";
		}
		return browserlogs;
	}
	
	
	public static void scrolldown() {

		try {

			// Casting WebDriver instance to JavascriptExecutor
			JavascriptExecutor js = (JavascriptExecutor) driver;

			// Scroll to the bottom of the page
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			Thread.sleep(5000);
		} catch (Exception e) {

			e.printStackTrace();

		}
	}

	
	public static void scrollIntoview() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	    }
	
}
