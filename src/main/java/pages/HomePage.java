package pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class HomePage extends TestBase {
	
	@FindBy(xpath="//img[@alt='PHPTRAVELS | Travel Technology Partner']")
	WebElement title;
	
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	
	public String validateHomePageTitle() {
		return driver.getTitle();
		
	}

}
