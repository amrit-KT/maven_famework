package test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.TestBase;

public class HomePageTest extends TestBase {
	
	HomePage homePage;
	
	public HomePageTest() {
		super();
	}
	
	
	@BeforeMethod
	public void setUp(){
		loadBrowser();
		homePage = new HomePage();	
	}
	
	@Test(priority=1)
	public void loginPageTitleTest(){
		String title = homePage.validateHomePageTitle();
		Assert.assertEquals(title, "PHPTRAVELS | Travel Technology Partner");
	}
	
	
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
	
	

}
