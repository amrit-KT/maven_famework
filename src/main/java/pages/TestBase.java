package pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;


import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import util.TestUtil;
import util.WebEventListener;

public class TestBase {
	
	public static Properties prop;
	public static WebDriver driver;
	
	public static EventFiringWebDriver e_driver;
	public static WebEventListener webEventListner;
	
	
	public ExtentReports extent;
	public ExtentTest extentTest;
	
	
	
	
	public TestBase() {
	
		try {
			prop = new Properties();
			
			FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"/src/main/java"+
															"/config/config.properties");
			
			prop.load(fip);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
	
	public static void loadBrowser() {
		
		String browserName = prop.getProperty("browser");
		
	
		if (browserName.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ "\\Drivers\\ChromeDriver\\chromedriver.exe");
			
			driver = new ChromeDriver();

	     
		}
		if (browserName.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+ "\\Drivers\\FireFoxDriverDriver\\geckodriver.exe");
			
			driver = new FirefoxDriver();
			
		}
		
		
		//// Now create object of EventListerHandler to register it with EventFiringWebDriver
		webEventListner = new WebEventListener();
		e_driver = new EventFiringWebDriver(driver);
		e_driver.register(webEventListner);
		driver = e_driver;
		
		//set timeouts and window properties
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		driver.manage().timeouts().pageLoadTimeout(TestUtil.pageLoadTimeout, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.implicitWaitTimeout, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
		
		
	}
	
	public void setExtent(){
		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/ExtentReport.html", true);
		extent.addSystemInfo("Host Name", "Amritesh");
		extent.addSystemInfo("User Name", "Amritesh");
		extent.addSystemInfo("Environment", "QA");
		
	}
	public void endReport(){
		extent.flush();
		extent.close(); 
	}



}
