package test;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import pages.HomePage;
import pages.TestBase;
import util.TestUtil;

public class HomePageTest extends TestBase {
	
	HomePage homePage;
	
	public HomePageTest() {
		super();
	}

	@BeforeTest
	public void beforeTest() {
		setExtent();
	}
	
	@AfterTest
	public void afterTest() {
		endReport();
	}
	
	@BeforeMethod
	public void setUp(){
		loadBrowser();
		homePage = new HomePage();	
		
	}
	
	@Test(priority=1)
	public void loginPageTitleTest(){
		extentTest = extent.startTest("loginPageTitleTest");
		String title = homePage.validateHomePageTitle();
		Logger logger = Logger.getLogger(HomePageTest.class);
		logger.info("  log message  ");
		Assert.assertEquals(title, "PHPTRAVELS | Travel Technology Partner");
	}
	
	
	
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException{
		
		if(result.getStatus()==ITestResult.FAILURE){
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS "+result.getName()); //to add name in extent report
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS "+result.getThrowable()); //to add error/exception in extent report
			
			String screenshotPath = TestUtil.takeScreenshotAtEndOfTest();
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath)); //to add screenshot in extent report
			//extentTest.log(LogStatus.FAIL, extentTest.addScreencast(screenshotPath)); //to add screencast/video in extent report
		}
		else if(result.getStatus()==ITestResult.SKIP){
			//extentTest.log(LogStatus.SKIP, "Test Case SKIPPED IS " + result.getName());
			extentTest.log(LogStatus.SKIP, "TEST CASE SKIPPED IS "+result.getName()); //to add name in extent report
			//extentTest.log(LogStatus.SKIP, "TEST CASE SKIPPED IS "+result.getThrowable()); //to add error/exception in extent report
			
			String screenshotPath = TestUtil.takeScreenshotAtEndOfTest();
			extentTest.log(LogStatus.SKIP, extentTest.addScreenCapture(screenshotPath)); //to add screenshot in extent report
			
		}
		else if(result.getStatus()==ITestResult.SUCCESS){
			//extentTest.log(LogStatus.PASS, "Test Case PASSED IS " + result.getName());
			extentTest.log(LogStatus.PASS, "TEST CASE PASSED IS "+result.getName()); //to add name in extent report
			//extentTest.log(LogStatus.PASS, "TEST CASE PASSED IS "+result.getThrowable()); //to add error/exception in extent report
			
			String screenshotPath = TestUtil.takeScreenshotAtEndOfTest();
			extentTest.log(LogStatus.PASS, extentTest.addScreenCapture(screenshotPath)); //to add screenshot in extent report
			

		}
		
		
		extent.endTest(extentTest); //ending test and ends the current test and prepare to create html report
		driver.quit();
	}
	
	

}
