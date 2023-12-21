package com.qcash.utilities;



import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.imageio.ImageIO;
import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;
import com.qcash.testbase.TestBase;
import com.qcash.utilities.Util;
public class Util extends TestBase{
	static Logger logger = LogManager.getLogger(Util.class);
    static Logger APP_LOGS = LogManager.getLogger(Util.class);
	//public static Properties exconf = getProperties();
    public static String filename = "qcashdata.xlsx";
	//public static String screenshotfilename = System.getProperty("user.dir")+"//src//main//resources//"+exconf.getProperty("Screenshot_FileName");
	//public static String screenshotfilename = exconf.getProperty("Screenshot_FileName");
    public static int currentTestCaseID = 2;
	public static ArrayList<String> resultSet;
	public static String url;
	public static String path;
	public static String fileValue;
	public static String CUName;
	private static JavascriptExecutor js;
	static String pageLoadStatus = null;
	public static WebDriverWait waite;
	public static String ClientName1="Client" + Util.GenerateRandomNumber(5);
	public static String ClientCode1=Util.GenerateRandomNumber(5);
	public static String ServerName1;
	public static String DatabaseName1;
	public static String GoogleAnalyticsCode1;
	public String S1;
	public String S2;
	// Scrolls up based on coordinates provided
	public static void scrollUp(int value){
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("scroll(0, "+value+");");
	}

	public static String GenerateRandomNumber(int charLength) {
		return String.valueOf(charLength < 1 ? 0 : new Random()
		.nextInt((9 * (int) Math.pow(10, charLength - 1)) - 1)
		+ (int) Math.pow(10, charLength - 1));
	}
	
	public static void quitBrowser() throws Exception{
		try{
			Util.waitForSeconds(2);
			driver.quit();
			//logger.info("Browser successfully closed and chromedriver exe has been killed.");

		}catch(Exception e){
			e.printStackTrace();
		}
	}

	
		public static boolean numberRangeCheck(int num, int min, int max) throws Exception {
			return num > min && num < max;

			}

	//Hover and click over a webelement

	public static void hoverOverAnElementAndClickUsingActions(WebElement element){
		Actions act = new Actions(driver);
		FluentWait<WebDriver> wait= new FluentWait<WebDriver>(driver);
		wait = new FluentWait<WebDriver>(driver);
		wait.pollingEvery(800, TimeUnit.MILLISECONDS);
		wait.withTimeout(30, TimeUnit.SECONDS);
		//wait.ignoring(NoSuchElementException.class);
		//wait.ignoring(StaleElementReferenceException.class);
		//wait.until(ExpectedConditions.visibilityOf(element));	
		act.click(element).build().perform();
	}

	//Scrolls to page bottom

	public static void scrollToBottom()
	{
		try{
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	// Scrolls up based on coordinates provided
	public static void scrollDown(int value){
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("scroll("+value+",0);");
	}

	//Scrolls to particular element to focus it
	public static void scrollIntoView(WebElement ele){
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].scrollIntoView();", ele);
		logger.trace("Scrolled into view"+ele.getAttribute("xpath"));
		Util.waitForSeconds(1);
	}

	public static boolean isNotClickable(WebElement webe, By xpath){
		try {
			WebDriverWait w = new WebDriverWait(driver,2);
			w.until(ExpectedConditions.elementToBeClickable(webe));
			String webeText = webe.getText();
			if(!webeText.isEmpty()){
				webe.click();
				w.until(ExpectedConditions.presenceOfElementLocated(xpath));
				String afterClickwebeText = webe.getText();
				if(afterClickwebeText.equals(webeText)){
					logger.info("Element is not Clickable = " + webeText+" | "+true);
					return true;
				}else{
					logger.info("Element is Clickable = " + webeText+" | "+false);
					return false;
				}
			}else{
				logger.info("Element is not Clickable and is Empty " + webeText+" | "+true);
				return true;
			}
		} catch(Exception e){
			logger.info("Element is Clickable "+" | "+false);
			return false;
		}
	}
	
	public static boolean isClickable(By ele) 
    {
        try{
            WebDriverWait wait = new WebDriverWait(driver, 2);
            wait.until(ExpectedConditions.elementToBeClickable(ele));
            String eletext = driver.findElement(ele).getText();
            logger.info(eletext + " Element is clickable");
            return true;
        }
        catch (Exception e){
        	 logger.info("Element is not clickable");
            return false;
        }
    }

	/**
	 * Finds if an element is displayed/enabled on the page or not. Waits for 800 milliseconds and tries three times before returning a result. 
	 * @param by Locator of the WebElement
	 * @return true if displayed, false otherwise
	 * @throws InterruptedException
	 */
	public static boolean isElementDisplayed(By by) throws InterruptedException {
		boolean result = false;
		int attempts = 1;
		while(attempts < 4) {
			try {
				Thread.sleep(800);
				if(driver.findElement(by).isDisplayed()){
					result = true;
					logger.trace("Element Displayed on attempt "+attempts);
					break;
				}
				else if(driver.findElement(by).isEnabled()){
					result = true;
					logger.trace("Element Enabled on attempt "+attempts);
					break;
				}
				else
					result = false;

			} catch(Throwable e) {
				logger.trace("Element Not Displayed With Attempt | "+attempts);
				logger.trace("isElementDisplayed:"+by.toString());
			}
			attempts++;
		}
		logger.trace("isElementDisplayed:"+by.toString()+"="+result);
		return result;
	}

	/**
	 * Finds if an element is displayed/enabled on the page or not. Waits for 100 milliseconds and tries twice before returning a result.  
	 * @param by Locator of the element
	 * @return true if found, false otherwise
	 * @throws InterruptedException
	 */
	public static boolean isElementDisplayed2(By by) throws InterruptedException {
		boolean result = false;
		int attempts = 1;
		while(attempts < 3) {
			try {
				Thread.sleep(100);
				if(driver.findElement(by).isDisplayed()){
					result = true;
					System.out.println("Element Displayed on attempt "+attempts);
					break;
				}
				else if(driver.findElement(by).isEnabled()){
					result = true;
					System.out.println("Element Enabled on attempt "+attempts);
					break;
				}
				else
					result = false;

			} catch(Throwable e) {
				logger.debug("isElementDisplayed2 - Element Not Displayed With Attempt | "+attempts);
			}
			attempts++;
		}
		return result;
	}


	public static String Timestamp(){

		DateFormat df = new SimpleDateFormat("ddMMyyHHmmss");
		Date date = new Date();
		String timestamp = df.format(date);
		return timestamp;

	}

	/**
	 * Finds if an element is displayed/enabled on the page or not. Waits for 10 milliseconds and tries twice before returning a result.
	 * @param by Locator of the element
	 * @return true if found, false otherwise
	 * @throws InterruptedException
	 */


	public static boolean isElementAvailable(By by) throws InterruptedException {
		boolean result = false;
		int attempts = 1;
		while(attempts < 3) {
			try {
				Thread.sleep(10);
				if(driver.findElement(by).isDisplayed()){
					result = true;
					break;
				}
				else if(driver.findElement(by).isEnabled()){
					result = true;
					break;
				}
				else
					result = false;

			} catch(Throwable e) {
				logger.debug("isElementAvailable - Element Not Available on Attempt | "+attempts);
				logger.trace("isElementAvailable_string="+by.toString());
			}
			attempts++;
		}
		logger.trace("isElementAvailable:"+by.toString()+"="+result);
		return result;
	}


	public static boolean isElementPresent(By by)
	{
		boolean result = false;
		try {
			if(driver.findElement(by).isDisplayed())
				result = true;
			/*else if(driver.findElement(by).isEnabled())
				result = true;*/
			else
				result = false;
		} 
		catch(Throwable e) 
		{
			System.out.println("Element not present");	
		}
		return result;
	}


	public static void summaryScreenShot()
	{
		try{
			/*File file = new File(System.getProperty("user.dir")+"//src//main/resources//chromedriver.exe");
			System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
			
			WebDriver dir = new ChromeDriver();		
			dir.get(System.getProperty("user.dir")+"//target//extentreports//extent.html");
			dir.manage().window().maximize();
			Util.waitForSeconds(20);
			dir.findElement(By.xpath("//*[@id=\"enable-dashboard\"]")).click();
			Util.waitForSeconds(3);
			dir.findElement(By.xpath("//*[@id=\"enable-dashboard\"]")).click();
			Util.takeScreenShot1(System.getProperty("user.dir")+"//reports/Summary.png",dir);
			waitForSeconds(5);
			dir.quit();*/
			
			 WebDriverManager.chromedriver().setup();

		        // Create a new instance of ChromeDriver
		        WebDriver driver = new ChromeDriver();

		        // Navigate to the desired URL
		        driver.get(System.getProperty("user.dir") + "//target//extentreports//extent.html");
		        driver.manage().window().maximize();
		        
		        // Wait for a specific number of seconds
		        Util.waitForSeconds(20);

		        driver.findElement(By.xpath("//*[@id=\"enable-dashboard\"]")).click();
		        Util.waitForSeconds(3);
				 driver.findElement(By.xpath("//*[@id=\"enable-dashboard\"]")).click();
					System.out.println("CLicked Dashboard link");
		        Util.waitForSeconds(4);
		        
		        // Take a screenshot
		        Util.takeScreenShot1(System.getProperty("user.dir") + "//reports/Summary.png", driver);
		        
		        // Wait for a specific number of seconds
		        Util.waitForSeconds(5);
		        
		        // Quit the WebDriver
		        driver.quit();
			
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Checks and returns the status of the URL provided. Appends the statuses(separated by semi-colon) if there are redirections.
	 * @param url The URL of which the status needs to be checked.
	 * @return Returns the status message along with status code. If any exception are encountered, returns the corresponding message.
	 * Eg : OK(200), Read timed out, Moved Permanently(301)
	 */
	public static String getLinkStatus(String url)
	{
		String resp = "";
		int respcode = 0;
		try{
			if(url==null)
			{	
				resp = "URL is null";
				return resp;
			}
			if(url.length()==0)
			{	
				resp = "URL is empty";
				return resp;
			}
			if(!url.startsWith("http"))
			{
				resp = "Not a valid URL";
				return resp;
			}

			if(url.startsWith("javascript"))
			{
				resp = "URL contains javascript";
				return resp;
			}

			HttpURLConnection conn;

			if(url.startsWith("https"))
			{
				conn = (HttpsURLConnection) (new URL(url)).openConnection();
			}
			else
			{
				conn = (HttpURLConnection) (new URL(url)).openConnection();	
			}
			//conn.connect();
			conn.setConnectTimeout(20000);
			conn.setReadTimeout(20000); 
			conn.setInstanceFollowRedirects(true);
			HttpURLConnection.setFollowRedirects(true);
			//HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> hostname.equals("192.168.65.15"));
			resp = conn.getResponseMessage();
			respcode = conn.getResponseCode();
			if(respcode == HttpURLConnection.HTTP_MOVED_PERM || respcode == HttpURLConnection.HTTP_MOVED_TEMP || respcode == HttpURLConnection.HTTP_SEE_OTHER)
			{
				String redir = conn.getHeaderField("Location");
				resp = resp + "(" + respcode + ")"+";"+getLinkStatus(redir);
				return resp;
			}


			resp = resp + "(" + respcode + ")";
			//conn.disconnect();
			return resp;

		}
		catch(javax.net.ssl.SSLHandshakeException sslExcep)
		{
			//logger.error("Handshake Exception on URL("+url+"): "+sslExcep);
			return sslExcep.getMessage();
		}
		catch(Exception e)
		{
			//logger.error("Exception while checking URL("+url+"): "+e);
			return e.getMessage();
		}

	}


	public static String isLinkBroken2(String url)
	{
		String resp = "";
		if(url.startsWith("javascript"))
		{
			resp = "URL contains javascript";
			return resp;
		}

		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);
		try
		{
			HttpResponse response = client.execute(request);
			return Integer.toString(response.getStatusLine().getStatusCode());
		}

		catch(Exception e)
		{
			logger.error("Exception while checking URL("+url+"): "+e);
			return e.getMessage();
		}

	}

	/**
	 * Returns the date and time in the format provided
	 * @param format The format of Date and Time
	 * @return
	 */
	public static String getDateAndTime(String format)
	{
		Date date=new Date();
		SimpleDateFormat simple=new SimpleDateFormat(format);
		String DateAndTime=simple.format(date);
		return DateAndTime;
	}

	/**
	* Returns the date and time in dd-MM-yyyy_HH:mm:ss format
	* @return
	*/
	public static String getDateAndTime()
	{
	Date date=new Date();
	SimpleDateFormat simple=new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
	String DateAndTime=simple.format(date);
	return DateAndTime;
	}

	/**
	 * Returns a future date and time
	 * @param format the format of the date and time to be in.
	 * @param amount the number of days to be fast forwarded
	 * @return
	 */
	public static String getFutureDateAndTime(String format, int amount)
	{
		SimpleDateFormat simple=new SimpleDateFormat(format);
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, amount);
		System.out.println();
		return simple.format(cal.getTime());
	}

	/**
	 * Converts a String of numbers to whole number
	 * Returns the whole number
	 */
	public static int getWholeNumber(String format)
	{
		Double myDouble = Double.valueOf(format); 	
		Integer val = Integer.valueOf(myDouble.intValue());
		return val;
	}

	/**
	 * Returns first day of the month
	 * @param format the format of the date and time to be in.
	 * @return
	 */

	public static String getFirstDayOfMonth() {

		Date today = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);

		calendar.set(Calendar.DAY_OF_MONTH, 1);
		Date firstDayOfMonth = calendar.getTime();

		DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		System.out.println("First Day of Month: " + sdf.format(firstDayOfMonth));
		return(sdf.format(firstDayOfMonth));	        
		//System.out.println("Today             : " + sdf.format(today));
	}

	/**
	 * Rounding of a double/float value to 'n' decimal places and return the value
	 */

	public static float getRoundValue(float value, int decimalplaces) {

		BigDecimal bigDecimal = new BigDecimal(value);
		bigDecimal = bigDecimal.setScale(decimalplaces,
				BigDecimal.ROUND_HALF_UP);
		return bigDecimal.floatValue();
	}

	/**
	 * @return
	 */

	public static void zipFolders() {
		try {
			String date = "dd";
			String month = "MMM";
			String year = "yyyy";
			String hour = "h";
			String minute = "m";
			String noon = "a";
			File inFolder = new File(System.getProperty("user.dir")+"/testng-xslt");
			String path=System.getProperty("user.dir")+"\\Reports\\"+" Link Validation " + getDateAndTime(date)+"-"
					+ getDateAndTime(month)+"-"+ getDateAndTime(year)+" at "+ getDateAndTime(hour)
					+"."+ getDateAndTime(minute)+" "+ getDateAndTime(noon) + ".zip";
			final	File outFolder = new File(path);
			ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(outFolder)));
			BufferedInputStream in = null;
			byte[] data = new byte[1000];
			String files[] =  inFolder.list();
			for (int i = 0; i < files.length; i++) {
				in = new BufferedInputStream(new FileInputStream(inFolder.getPath() + "/" + files[i]), 1000);
				out.putNextEntry(new ZipEntry(files[i]));
				int count;
				while ((count = in.read(data, 0, 1000)) != -1) {
					out.write(data, 0, count);
				}
				out.closeEntry();
			}
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Polls the page
	 * @param number number of seconds for which the page needs to be polled 
	 */
	public static void waitForSeconds(int number) {
		try {
			Thread.sleep(TimeUnit.SECONDS.toMillis(number));
			logger.trace("Waited for "+number+" seconds");
		} catch (InterruptedException ie) {
			logger.debug(ie.getMessage());
		}
	}

	/*public static void waitForTextPresent(String actual, String Expected) throws InterruptedException{
		for(int i=0;i<=60;i++)
		{
			if(i>60)fail("Wait for Text Not Present");
			String text = driver.findElement(By.xpath(OR.getProperty(Expected))).getText();
			if(actual.equals(text))
				break;	
			Thread.sleep(1000);
		}
	}*/

	public void getCoordinates (String xpathcoord)throws Exception{
		try{
			WebElement ele = driver.findElement(By.xpath(xpathcoord));
			Point classname = ele.getLocation();
			int xcoordinate = classname.getX();
			int ycoordinate = classname.getY();
			System.out.println("xcoordinate is: " +xcoordinate );
			System.out.println("ycoordinate is: " +ycoordinate);


		}catch (Exception e){
			e.printStackTrace();
			logger.debug(e.getMessage());
		}
	}




	public static boolean isSkip(String testCase){
		System.out.println("Check=>"+testCase);
		for(int rowNum=2 ; rowNum<=datatable.getRowCount("Test Cases");rowNum++){
			if(testCase.equals(datatable.getCellData("Test Cases", "TCID", rowNum))){
				if(datatable.getCellData("Test Cases", "Runmode", rowNum).equals("Y"))
					return false;
				else {
					//	System.out.println("skip");
					return true;
				}	
			}
		}

		return false;
	}

	public static Object[][] getData(String testName){
		System.out.println("Test  -- "+testName);
		if(datatable == null){
			datatable = new Xls_Reader(filename);
		}
		int rows=datatable.getRowCount(testName)-1;
		if(rows <=0){
			Object[][] testData =new Object[1][0];
			return testData;
		}
		rows = datatable.getRowCount(testName);  // 3
		int cols = datatable.getColumnCount(testName);
		System.out.println("Test Data File -- "+filename);
		System.out.println("Test Data Sheet -- "+testName);
		System.out.println("Total Rows -- "+rows);
		System.out.println("Total Cols -- "+cols);
		Object data[][] = new Object[rows-1][cols];
		for(int rowNum = 2 ; rowNum <= rows ; rowNum++){
			for(int colNum=0 ; colNum< cols; colNum++){
				resultSet = new ArrayList<String>();
				data[rowNum-2][colNum]=datatable.getCellData(testName, colNum, rowNum);
				//		resultSet.add(testName);
			}

		}
		//	return getData(testName);
		return data;
	}

	public static void hoverOverAnElement (WebElement element)
	{
		Actions act = new Actions (driver);
		FluentWait<WebDriver> wait= new FluentWait<WebDriver>(driver);
		wait = new FluentWait<WebDriver>(driver);
		wait.pollingEvery(800, TimeUnit.MILLISECONDS);
		wait.withTimeout(30, TimeUnit.SECONDS);
		act.moveToElement(element).perform();	
	}

	public static void fluentwait(By by)
	{
		int whileattempts = 0;
		FluentWait<WebDriver> waitf= new FluentWait<WebDriver>(driver);
		waitf = new FluentWait<WebDriver>(driver);
		waitf.withTimeout(30, TimeUnit.SECONDS);
		waitf.pollingEvery(3, TimeUnit.SECONDS);
		waitf.ignoring(NoSuchElementException.class);
		waitf.ignoring(TimeoutException.class);
		waitf.until(ExpectedConditions.elementToBeClickable(driver.findElement(by)));
		waitf.until(ExpectedConditions.visibilityOf(driver.findElement(by)));

		if(driver.findElement(by).isDisplayed()==false)
		{
			do{
				driver.navigate().refresh();
				logger.info("Current page is being refreshed again");
				Util.waitForPageToLoad();
			}
			while(whileattempts<3);
			whileattempts++;
		}
		else{
			logger.info("Expected element is now successfully displayed with the xpath: " + driver.findElement(by));
		}
	}


	public static void doubleclickanelement(WebElement element){
		Actions action1 = new Actions (driver);
		FluentWait<WebDriver> waitforhover= new FluentWait<WebDriver>(driver);
		waitforhover = new FluentWait<WebDriver>(driver);
		waitforhover.pollingEvery(800, TimeUnit.MILLISECONDS);
		waitforhover.withTimeout(30, TimeUnit.SECONDS);
		action1.doubleClick(element).perform();

	}

	public static String readUrl(String testName, String serial) throws InterruptedException{
		datatable = new Xls_Reader(filename);
		for(int rownum=2; rownum<=datatable.getRowCount(testName); rownum++){
			for(int colnum=1; colnum<=datatable.getColumnCount(testName); colnum++){
				datatable.getCellData(testName, "Serial", rownum);
				if(datatable.getCellData(testName, "Serial", rownum).equals(serial)){
					datatable.getCellData(testName, colnum, rownum);
					url = datatable.getCellData(testName, "Url", rownum);
				}
			}
		}
		return url;
	}

	public static String readMemberUrl(String testName, String serial) throws InterruptedException{
		datatable = new Xls_Reader(filename);
		for(int rownum=2; rownum<=datatable.getRowCount(testName); rownum++){
			for(int colnum=1; colnum<=datatable.getColumnCount(testName); colnum++){
				datatable.getCellData(testName, "Serial", rownum);
				if(datatable.getCellData(testName, "Serial", rownum).equals(serial)){
					datatable.getCellData(testName, colnum, rownum);
					url = datatable.getCellData(testName, "MemberUrl", rownum);
				}
			}
		}
		return url;
	}


	public static String readData(String testName, String serial, String columnName) throws InterruptedException{
		datatable = new Xls_Reader(filename);
		for(int rownum=2; rownum<=datatable.getRowCount(testName); rownum++){
			for(int colnum=1; colnum<=datatable.getColumnCount(testName); colnum++){
				datatable.getCellData(testName, "Serial", rownum);
				if(datatable.getCellData(testName, "Serial", rownum).equals(serial)){
					datatable.getCellData(testName, colnum, rownum);
					url = datatable.getCellData(testName, columnName, rownum);
				}
			}
		}
		return url;
	}


	public static void loadingdata(String entertextbox, String list, String entertext){
		for(int i=0;i<10;i++){
			try{
				/*boolean l = */driver.findElement(By.xpath(OR.getProperty(list))).isDisplayed();
				break;
			}catch(Exception e){
				getObject(entertextbox).clear();
				getObject(entertextbox).sendKeys(entertext);
				i++;
			}
		}
	}

	/*	
	 Method to get page title 
	 */

	public static String getTitle()
	{
		String ActualTitle = driver.getTitle();
		return ActualTitle;
	}

	/*
	 To get the URL which is embedded on the .
	 */

	public static String getlinkattributeofelement(String xpath)
	{
		return(driver.findElement(By.xpath(xpath)).getAttribute("href"));
	}

	/*
	 To get the URL embedded by passing the Object to the below method
	 */

	public static String getlinkattributeofobject(By by){
		return (driver.findElement(by).getAttribute("href"));
	}


	/**
	 * Updates the Result column in the respective sheet with FAIL
	 * @param testName the name of the test data sheet to be updated
	 * @param serial the serial number of the test data to be updated
	 * @throws InterruptedException
	 */
	public static void createXLSFailReport(String testName, String serial){
		datatable = new Xls_Reader(filename);
		for(int rownum=2; rownum<=datatable.getRowCount(testName); rownum++){
			for(int colnum=1; colnum<=datatable.getColumnCount(testName); colnum++){
				datatable.getCellData(testName, "Serial", rownum);
				if(datatable.getCellData(testName, "Serial", rownum).equals(serial)){
					datatable.getCellData(testName, colnum, rownum);
					datatable.setCellData(testName, "Result", rownum,"FAIL" );
				}
			}
		}
	}

	/**
	 * Updates the Result column in the respective sheet with PASS
	 * @param testName the name of the test data sheet to be updated
	 * @param serial the serial number of the test data to be updated
	 * @throws InterruptedException
	 */
	public static void createXLSPassReport(String testName, String serial){
		datatable = new Xls_Reader(filename);
		for(int rownum=2; rownum<=datatable.getRowCount(testName); rownum++){
			for(int colnum=1; colnum<=datatable.getColumnCount(testName); colnum++){
				datatable.getCellData(testName, "Serial", rownum);
				if(datatable.getCellData(testName, "Serial", rownum).equals(serial)){
					datatable.getCellData(testName, colnum, rownum);
					datatable.setCellData(testName, "Result", rownum,"PASS" );
				}
			}
		}
	}

	public static void Intfail(String testName, String serial){
		for(int x=2; x<=datatable.getRowCount(testName); x++){
			if(datatable.getCellData(testName, "Serial", x).equals(serial))
				datatable.setCellData(testName, "Result", x, "Fail");
		}
	}
	// Set Pass fails in New Sheet
	public static void failNewSheet(String testName, String TCIDNum, int rowNum) throws IOException{
		datatable = new Xls_Reader(System.getProperty("user.dir")+"//Automation_TS.xlsx");
		if(datatable.getCellData(testName, "TestCaseID", rowNum+1).equals(TCIDNum))
			if(datatable.getCellData(testName, "TestCaseID", rowNum+1).equals(TCIDNum)){
				if(CONFIG.getProperty("browser").equals("Firefox"))
					datatable.setCellData(testName, "Firefox", rowNum+1, "Fail");
				else if(CONFIG.getProperty("browser").equals("Chrome"))
					datatable.setCellData(testName, "Chrome", rowNum+1, "Fail");
				else if(CONFIG.getProperty("browser").equals("IE"))
					datatable.setCellData(testName, "IE", rowNum+1, "Fail");
			}else
				System.out.println("Passed Parameter is wrong please verify");
	}

	// Set Pass Pass in New Sheet
	public static void passNewSheet(String testName, String TCIDNum, int rowNum) throws IOException{
		datatable = new Xls_Reader(System.getProperty("user.dir")+"//Automation_TS.xlsx");
		if(datatable.getCellData(testName, "TestCaseID", rowNum+1).equals(TCIDNum)){
			if(CONFIG.getProperty("browser").equals("Firefox"))
				datatable.setCellData(testName, "Firefox", rowNum+1, "Pass");
			else if(CONFIG.getProperty("browser").equals("Chrome"))
				datatable.setCellData(testName, "Chrome", rowNum+1, "Pass");
			else if(CONFIG.getProperty("browser").equals("IE"))
				datatable.setCellData(testName, "IE", rowNum+1, "Pass");
		}else
			System.out.println("Passed Parameter is wrong please verify");
	}

	public static void takeScreenShot1(String fileName,WebDriver dir) {
		File scrFile = ((TakesScreenshot)dir).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File(fileName));
			
		} catch (IOException e) {
			e.printStackTrace();
		}	   
	}

	

	/**
	 * Takes screenshot
	 * @param fileName The name of the file to be saved.
	 */
	public static void takeScreenShot(String fileName) {
        try {
           /* if(CUName == null){
                CUName = environment(en);
            }*/
            if(fileValue== null){
                fileValue = folder("f");
                File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+"\\screenshots\\"+"//"+fileValue+"\\"+CUName+"-"+fileName+"_"+getDateAndTime()+".jpg"));
            }else{
                File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                try {
                    FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+"\\screenshots\\"+"//"+fileValue+"\\"+CUName+"-"+fileName+"_"+getDateAndTime()+".jpg"));
                } catch (IOException e) {
                    e.printStackTrace();
                }      
            }     

        } catch (IOException e1) {
            e1.printStackTrace();
        }
        }

	
	public static void zip(String filepath){
		try	{
			File inFolder=new File(filepath);
			File outFolder=new File("Reports.zip");
			ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(outFolder)));
			BufferedInputStream in = null;
			byte[] data  = new byte[1000];
			String files[] = inFolder.list();
			for (int i=0; i<files.length; i++){
				in = new BufferedInputStream(new FileInputStream
						(inFolder.getPath() + "/" + files[i]), 1000);  
				out.putNextEntry(new ZipEntry(files[i])); 
				int count;
				while((count = in.read(data,0,1000)) != -1){
					out.write(data, 0, count);
				}
				out.closeEntry();
			}
			out.flush();
			out.close();
		}catch(Exception e){
			e.printStackTrace();
		} 
	}	

	public static Properties getProperties()
	{ 
		Properties prop=null;
		try {
			prop= new Properties();
			System.out.println("System current directory from Util.getProperties() is: "+ System.getProperty("user.dir"));
			FileInputStream fn =new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//confi.properties");			
			prop.load(fn);
			return prop;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	//page load method by using java script.
	/**
	 * Clicks the element with Java Script
	 * @param elementName the locator of the WebElement to be clicked
	 */
	public static void clickElementWithJSE( By elementName ) {
		//Create the object of JavaScript Executor 
		JavascriptExecutor js = (JavascriptExecutor)driver;
		WebElement element= driver.findElement(elementName ) ;
		//element.click();
		js.executeScript("arguments[0].setAttribute('style', arguments[1]);",element, "color: Green; border: 2px solid White;");
		js.executeScript("arguments[0].setAttribute('style', arguments[1]);",element, "");
		js.executeScript( "arguments[0].click();", element );
		logger.trace("clickElementWithJSE : "+element);
		js = null;
	}

	//pageload status
	/**
	 * Waits till the page load status becomes Complete, in turn waits till the page is loaded.
	 */
	public static void waitForPageToLoad() {
		do {
			js = (JavascriptExecutor) driver;
			pageLoadStatus = (String)js.executeScript("return document.readyState");
			logger.trace("Page Loading...");
		} while ( !pageLoadStatus.equals("complete") );
		logger.trace("Page Loaded");
	}

	public static void setData(String testName, String columnName, String serial, String resultValue) throws InterruptedException{
		datatable = new Xls_Reader(filename);

		for(int rownum=2; rownum<=datatable.getRowCount(testName); rownum++){
			for(int colnum=1; colnum<=datatable.getColumnCount(testName); colnum++){
				datatable.getCellData(testName, "Serial", rownum);
				if(datatable.getCellData(testName, "Serial", rownum).equals(serial)){
					datatable.setCellData(testName, columnName,rownum, resultValue);
					break;
				}
			}
		}

	}
	public static void handlesecalertIE9(){
		Util.waitForSeconds(5);
		String pageTitle = driver.getTitle();
		logger.info(pageTitle);
		if(pageTitle.contains("Certificate Error")){
			for(int i=0; i<3; i++){
				driver.getKeyboard().pressKey(Keys.TAB);
			}
			driver.getKeyboard().pressKey(Keys.ENTER);
		}else
			logger.info("Certificate not required for this environment");
		waitForSeconds(5);
	}

	/**
	 * Writes a map of Strings to an xlsx file. Serial number would be inserted, and two columns with keys and values.
	 * @param filename Name of the file to which the map is to be written.
	 * @param sheetname Name of the sheet in the file to which the map is to be written. If sheet already exists in the file, it won't be written.
	 * @param map The map which needs to be written.
	 * @param column1Name The name of the first column where the key would be inserted
	 * @param column2Name The name of the second column where the corresponding value would be inserted
	 */
	public static void mapToSpreadsheet(String filename, String sheetname, Map<String,String> map, String column1Name, String column2Name)
	{
		try {
			String path = System.getProperty("user.dir");

			filename = path+"\\outputfiles\\"+filename+".xlsx";
			Workbook wb;

			if(new File(filename).isFile())
			{
				FileInputStream fis = new FileInputStream(new File(filename));
				wb = new XSSFWorkbook(fis);
				fis.close();
			}
			else
				wb = new XSSFWorkbook();

			Sheet sh = wb.createSheet(sheetname);

			Set<String> keys = map.keySet();
			Iterator<String> itr = keys.iterator();

			sh.createRow(0);

			for(int j=0; j<3; j++)
				sh.getRow(0).createCell(j);

			sh.getRow(0).getCell(0).setCellValue("Sl No");
			sh.getRow(0).getCell(1).setCellValue(column1Name);
			sh.getRow(0).getCell(2).setCellValue(column2Name);

			for(int i=1; i<=map.size(); i++)
			{
				String key = itr.next();
				String value = map.get(key);

				sh.createRow(i);

				for(int j=0; j<3; j++)
					sh.getRow(i).createCell(j);

				sh.getRow(i).getCell(0).setCellValue(Integer.toString(i));

				if(key.length()>200)
					sh.getRow(i).getCell(1).setCellValue(key.substring(0, 200));
				else
					sh.getRow(i).getCell(1).setCellValue(key);

				if(value.length()>200)
					sh.getRow(i).getCell(2).setCellValue(value.substring(0, 200));
				else
					sh.getRow(i).getCell(2).setCellValue(value);
			}
			sh.autoSizeColumn(1);
			sh.autoSizeColumn(2);

			FileOutputStream fileOut = new FileOutputStream(filename);
			wb.write(fileOut);
			wb.close();
			fileOut.close();
			logger.info("Successfully written to sheet "+sheetname+" on file "+filename);
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}catch (Exception e){
			logger.error(e.getMessage());
		}
	}



	/**
	 * Reads a map and returns the count of unique values.
	 * @param map consisting of String keys and values.
	 * @return map with the unique value of original map as key and the count of them as value.
	 */
	public static Map<String, String> valuesCount(Map<String, String> map)
	{
		Set<String> values = new HashSet<String>(map.values());
		Set<String> keys = new HashSet<String>(map.keySet());
		System.out.println("TotalUniqueValues="+values.size());
		Iterator<String> itr = values.iterator();
		Map<String, String> newmap = new HashMap<String, String>();
		while(itr.hasNext())
		{
			newmap.put(itr.next(), new String("0"));
		}

		itr = keys.iterator();
		for(int i=1; i<=map.size(); i++)
		{
			String key = itr.next();
			String value = map.get(key);
			if(newmap.containsKey(value))
				newmap.put(value, String.valueOf((Integer.parseInt(newmap.get(value))+1)));
		}

		System.out.println("NEW MAP");
		System.out.println(newmap);

		return newmap;
	}

	/**
	 * Generates and returns a random number between zero(including) and the limit specified(excluding)
	 * @param end the limit 
	 * @return the random number  
	 */
	public static int getRandom(int end)
	{
		if(end==0)
			return 0;
		Random r = new Random();
		return r.nextInt(end);

	}

	/**
	 * Generates and returns a random 10-digit number  
	 * @return the random number  
	 */
	public static String getRandomNumber()
	{
		Random generator = new Random();

		String strippedNum;
		int num1 = 0;
		int num2 = 0;
		int num3 = 0;

		num1 = generator.nextInt(600) + 100;//numbers can't include an 8 or 9, can't go below 100.
		num2 = generator.nextInt(641) + 100;//number has to be less than 742//can't go below 100.
		num3 = generator.nextInt(8999) + 1000; // make numbers 0 through 9 for each digit.//can't go below 1000.

		strippedNum = Integer.toString(num1) + Integer.toString(num2) + Integer.toString(num3);
		return strippedNum;
	}

	
	/**
	 * Returns the home page of the url passed
	 * @param url the url of which the home page needs to be found
	 * @return the homepage
	 */
	
	public static String getHome(String url)
	{
		String[] ar = url.split("/");
		url = ar[0]+"//"+ar[2]+"/";
		return url;
	}

	public static void jsExecutorSetValue(WebElement ele, String value)
	{
		js =  ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].setAttribute('value', arguments[1]);", ele, value);
		logger.debug("JSExecution Completed. Value Set = "+value);
	}

	/**
	 * Generates and returns several random numbers between zero(including) and the limit specified(excluding)
	 * @param max the maximum limit under which the numbers should be
	 * @param count the count of numbers to be generated
	 * @return an int array containing unique numbers
	 */
	public static int[] getRandomNumbers(int max, int count)
	{
		int[] finalnums = new int[count];
		Random r = new Random();

		Set<Integer> nums = new HashSet<Integer>();

		while(nums.size()<count)
		{
			nums.add(Integer.valueOf(r.nextInt(max)));
		}
		logger.debug("Random Numbers Generated : "+nums);
		int i = 0;
		for(Integer item:nums)
		{
			finalnums[i] = item.intValue();
			i++;
		}
		return finalnums;
	}

	/**
	 * Generates a number with the specified length. Can be used to generate <i>phone number, fax number</i> and <i>extension</i>.
	 * @param length the length of the number to be generated
	 * @return the number generated, as a <b>String<b>
	 */
	public static String generateNumber(int length)
	{
		String number = "";
		for(int i=0; i<length; i++)
		{
			number = number + String.valueOf(Util.getRandom(10));
		}
		logger.debug("Number generated : "+number);
		return number;
	}

	/**
	 * Converts String with commas, spaces and $, % to float. Can be used to convert amount so as to compare/validate.
	 * @param input the string to be converted
	 * @return the float value of the String
	 */
	public static float getAmount(String input)
	{
		if(input==null)
			return 0;
		else if(input.equalsIgnoreCase(""))
			return 0;
		input = input.replace("(", "-");
		input = input.replace(")", "");
		input = input.replace(",", "");
		input = input.replace("$", "");
		input = input.replace(" ", "");
		input = input.replace("%", "");
		input = input.replace("-", "");	
		return Float.parseFloat(input);

	}

	/**
	 * Converts String with commas, spaces and $ to float. Can be used to convert amount so as to compare/validate.
	 * @param input the string to be converted
	 * @return the float value of the String
	 */
	public static String getAmountAsString(String input)
	{
		if(input==null)
			return "0";
		else if(input.equalsIgnoreCase(""))
			return "0";
		input = input.replace("(", "-");
		input = input.replace(")", "");
		input = input.replace(",", "");
		input = input.replace("$", "");
		input = input.replace(" ", "");
		input = input.replace("%", "");
		return input;

	}
	
	
	String merge(String S1, String S2) {
		StringBuilder finalString = new StringBuilder(); 
	       finalString.append(S1);
	       finalString.append(S2);
		return S2; 
	}
	/**
	 * Compares two lists
	 * @param list1 the first list to be compared
	 * @param list2 the second list to be compared to list1
	 * @return the list with the delta
	 */
	public static List<String> compareLists(List<String> list1, List<String> list2)// list1 = 1,2,3,4,5 ; list2 = 1,2,3,4,5
	{
		List<String> result = new ArrayList<String>(list1); // list1 = result= 1,2,3,4,5
		list1.removeAll(list2); // zero records in list1
		list2.removeAll(result); // zero records in list2
		result.clear(); // clearing result
		result.addAll(list1); // zero records added from list1 to result
		result.addAll(list2); // zero records added from list2 to result
		return result; // no records in list , so size is zero --> implies both the list are matching
	}

	/**
	 * Clicks the element with Java Script
	 * @param elementName the locator of the WebElement to be clicked
	 */

	public static void JExecutorClick (String specificxpath)
	{
		try 
		{
			WebElement element = driver.findElement(By.xpath(specificxpath));
			if (element.isEnabled() && element.isDisplayed()) {
				js.executeScript("arguments[0].click();", element);
			} else {
				System.out.println("Unable to click on element");
			}
		} catch (StaleElementReferenceException e) {
			System.out.println("Element is not attached to the page document "+ e.getMessage());
		} catch (NoSuchElementException e) {
			System.out.println("Element was not found in DOM "+ e.getMessage());
		} catch (Exception e) {
			System.out.println("Unable to click on element "+ e.getMessage());
		} 
	}   


	/** Retrieves first day of month
	 * Code for other functions related to Date & Calendar is commented in the function below
	 */	
	public static String getFirstDay()
	{
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		int month = calendar.get(Calendar.MONTH) + 1;
		int year = calendar.get(Calendar.YEAR);
		// Date format :: "MM/dd/yyyy"
		String date = "0"+month+"/01/" + year ;	
		return date;

	}
	/**
	 * Copies and pastes a file from source to destination
	 * @param source the file to be copied including the path. Eg: <i>//folder/srcfilename.extension</i>
	 * @param destination the destination to which the file is to be copied including the filename. Eg: <i>//folder/destfilename.extension</i>
	 */
	public static void copyFile(String source, String destination)
	{
		try 
		{
			File srcFile = new File(source);
			FileUtils.copyFile(srcFile, new File(destination));
			logger.info(source+" successfully copied to "+destination);

			File file = new File(destination);
			file.setExecutable(true, false);
			file.setWritable(true, false);
			file.setReadable(true, false);

		} catch (FileNotFoundException e) {
			logger.error("File Not Found while copying "+source+" "+e.getMessage());
		} catch (IOException e) {
			logger.error("IO Exception while copying "+source+" "+e.getMessage());
		}
	}
	
	/*
	*  @Method Name : selectCheckbox
	*  @Method Description : Check whether checkbox is selected, if not select the checkbox of an element
	*  @Params : element
	*/
	public static void selectCheckbox(String element)
	{
	try
	{
	WebDriverWait waite = new WebDriverWait(driver, 90);
	waite.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(element))));
	if ( !getObject(element).isSelected() )
	{
	getObject(element).click();
	logger.info("Checkbox is selected");
	}
	else if(getObject(element).isSelected())
	{
	getObject(element).click();
	logger.info("Checkbox is un-selected");
	}
	}
	catch (Exception e) {
	logger.error("Checkbox is selected"+element+" "+e.getMessage());
	}
	}

	
	/**
	 * @Method Name : verifyText()
	 * @param header
	 * @param expectedHeader
	 * @throws InterruptedException
	 */
	public static void verifyText(String textElement, String expectedText) throws InterruptedException {

	try {
	String ActualText = getObject(textElement).getText().trim();
	Assert.assertEquals(ActualText, expectedText);
	logger.info(" " + ActualText + "is displayed");
	} catch (Exception e) {
	logger.info(" " + expectedText + "is not displayed");
	}
	}

	/**
	 * @Method Name : clickAnElement()
	 * @param clickElement
	 * @param elementName
	 */
	public static void clickAnElement(String xpath,String elementName) {
	try {
	WebDriverWait waite = new WebDriverWait(driver, 90);
	waite.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(xpath))));
	getObject(xpath).click();
	logger.info(" Clicked " +elementName+  " element");

	} catch(Exception e)
	{
	logger.info(" " + elementName + " is not displayed");
	Assert.assertTrue(false, "Assertion Error: " + elementName +  " is not displayed. ");
	}

	}

	/**
	 * @Method Name : assertString()
	 * @Method Description : Assert two Strings are equal
	 * @param webElement
	 * @param expectedName
	 */
	public static void assertString(String webElement, String expectedName) {
	String ActualResult = null;
	try {
	WebDriverWait waite = new WebDriverWait(driver, 90);
	waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(webElement))));
	ActualResult =getObject(webElement).getText().trim();
	String ExpectedResult = expectedName;
	Assert.assertEquals(ActualResult, ExpectedResult);
	logger.info(" Actual: " +ActualResult+ " And Expected : "+expectedName+ " is equal");
	} catch(Exception e)
	{
	logger.info(" Actual: "+ActualResult + " And Expected : " +expectedName+ "  is not equal");
	}
	}
	
	/**
	* Finds if an element is not displayed / not enabled on the page or not. Waits for 200 milliseconds and tries three times before returning a result.
	* @param by Locator of the WebElement
	* @return true if not displayed, false otherwise
	* @throws InterruptedException
	*/
	public static boolean isElementNotDisplayed(By by) throws InterruptedException {
	boolean result = false;
	int attempts = 1;
	while(attempts < 2) {
	try {
	Thread.sleep(200);
	if(!driver.findElement(by).isDisplayed()){
	result = true;
	logger.trace("Element Not Displayed on attempt "+attempts);
	logger.info("Element Not Displayed");
	break;
	}
	else if(!driver.findElement(by).isEnabled()){
	result = true;
	logger.trace("Element Not Enabled on attempt "+attempts);
	logger.info("Element Not Enabled");
	break;
	}
	else
	result = false;

	} catch(Throwable e) {
	logger.trace("Element is Displayed With Attempt | "+attempts);
	logger.trace("isElementNotDisplayed:"+by.toString());
	}
	attempts++;
	}
	logger.trace("isElementNotDisplayed:"+by.toString()+"="+result);
	return result;
	}

	/**
	* @Method Name : AssertElementSize()
	* @Method Description : Get the count from the list and assert with expected count
	* @param webelement
	* @param count
	*
	*/
	public static void AssertElementSize(String webelement, int count)
	{
		try
		{
			WebDriverWait waite = new WebDriverWait(driver, 90);
			waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(webelement))));
			List<WebElement> sizeXpath = getObjects(webelement);
			int size = sizeXpath.size();
			logger.info("Size of an element" +webelement+ " is present");
			if(size==count)
			{
				logger.info("Expected " +webelement+ ":" + size + " and actual count : " +count+ " matched");
			}
			else
			{
				logger.info("Expected " +webelement+ ":" + size + "  and actual count : " +count+ " not matched");
				Assert.assertTrue(false, "Expected webelement list count did not match with the actual count. ");
			}
		}
		catch(Exception e)
		{
			logger.info("Size of an element " +webelement+ " is not present");
			Assert.assertTrue(false, "Expected webelement list count did not match with the actual count. ");
		}
	}

	/**
	* @Method Name : assertStringwithScroll()
	* @Method Description : Scrolling to particular element and verifying two Strings are equal
	* @param webElement
	* @param expectedName
	*/
	public static void assertStringwithScroll(String webElement, String expectedName) {
	String ActualResult = null;
	try {
	WebDriverWait waite = new WebDriverWait(driver, 90);
	waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(webElement))));
	WebElement webe=driver.findElement(By.xpath(OR.getProperty(webElement)));
	Util.scrollIntoView(webe);
	ActualResult =getObject(webElement).getText().trim();
	String ExpectedResult = expectedName;
	Assert.assertEquals(ActualResult, ExpectedResult);
	logger.info(" Actual Name: " +ActualResult+ " And the Expected Name : "+expectedName+ " are the same");
	} catch(AssertionError | Exception e)
	{
	logger.error(" Actual Name: "+ActualResult + " And the Expected Name : " +expectedName+ " are not the same");
	}
	}
	/**
	* @Method Name : SplitStringWithSpecialCharandCompareAsset()
	* @Method Description : Get the string from web element and split until the special characted displayed and compare with the expected text
	* @param webelement
	* @param SplChar
	* @param ExpText
	*/

	public static void SplitStringWithSpecialCharandCompareAsset(String webElement, String SplChar, String ExpText)
	{
		String ActualString = null;
		String FinalString = null;
		try
		{
			WebDriverWait waite = new WebDriverWait(driver, 90);
			waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(webElement))));
			ActualString =getObject(webElement).getText().trim();
			String separator = SplChar;
			int FinalTextIndex = ActualString.lastIndexOf(separator);
			FinalString = ActualString.substring(0,FinalTextIndex).trim();
			Assert.assertEquals(FinalString, ExpText);
			logger.info(" Actual: " +FinalString+ " And Expected : "+ExpText+ " is equal");
		} catch(Exception e)
		{
			logger.info(" Actual: "+FinalString + " And Expected : " +ExpText+ "  is not equal");
		}
	}


	/**
	* @Method Name : getRandomElementfromList()
	* @Method Description : Get the random number from the list
	*/
	public static int getRandomElementfromList(List<Integer> list)
	   {
	       Random rand = new Random();
	       return list.get(rand.nextInt(list.size()));
	   }
		
	
public static String readExcel(String SheetName, String TestCaseName, String ColumnName) throws IOException {
FileInputStream Input = new FileInputStream("DataSheet.xlsx");
XSSFWorkbook wb = new XSSFWorkbook(Input);
XSSFSheet Sheet = wb.getSheet(SheetName);
int Row = Sheet.getLastRowNum();
String CellValue, data = null;
int RowIndex = 0;
int ColumnIndex = 0;
for (int i = 1; i <= Row; i++) {
CellValue = Sheet.getRow(i).getCell(0).getStringCellValue();
if (CellValue.equalsIgnoreCase(TestCaseName)) {
RowIndex = i;
break;
}
}
for (int i = 1; i < 15; i++) {
CellValue = Sheet.getRow(0).getCell(i).getStringCellValue();
if (CellValue.equalsIgnoreCase(ColumnName)) {
ColumnIndex = i;
break;
}
}
if (RowIndex != 0 && ColumnIndex != 0) {
data = Sheet.getRow(RowIndex).getCell(ColumnIndex).getStringCellValue();
}
return data;
}

/**
* @Method Name : getTomorrow()
* @Method Description : To get tommorrow's date
* @param strFormat
*/
public static String getTomorrow(String strFormat) {
   Calendar cal = new GregorianCalendar();
   cal.add(Calendar.DATE, 1);
   return new SimpleDateFormat(strFormat).format(cal.getTime());
}

	
}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

