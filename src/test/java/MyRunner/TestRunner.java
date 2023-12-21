package MyRunner;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.qcash.HealthCheck.QATestSuitePageFunctions;
import com.qcash.testbase.TestBase;
import com.qcash.utilities.Util;

import cucumber.api.CucumberOptions;
import cucumber.api.java.Before;
import cucumber.api.testng.AbstractTestNGCucumberTests;



@CucumberOptions(
		features = {"src\\test\\java\\Features"},
		glue={"StepDefinitions"},//path of the step definition file
		dryRun=false,//to check the mapping is proper between feature file and step definition file
		monochrome=true,//display the console output in proper readable format
		strict=true,
				
				tags= "@SanityCheck",
			   
				
				plugin = {"junit:target/cucumber-results.xml","pretty:target/cucumber-pretty.txt","json:target/cucumber.json","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
							
		)

@Test
public class TestRunner  extends AbstractTestNGCucumberTests
{

	static Logger logger = LogManager.getLogger(QATestSuitePageFunctions.class);
	@BeforeClass
	public static void BrowserInitialize(){
		try{
			TestBase.initialize();		
			logger.info("Browser Initialization is success");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Before
	
	
	
	public static void clearSiteName() throws Exception{
		try{
			Util.CUName = "";
			//Util.AssertStmt="";

		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	@AfterClass
	public static void quitBrowser() throws Exception{
		try{
			Util.waitForSeconds(3);
			TestBase.driver.close();
			logger.info("Browser successfully closed and chromedriver exe has been killed.");

		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	

	
}
	
