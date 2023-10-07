package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testbase.TestBase;

public class DashboardPage extends TestBase {
	static final Logger logger = LogManager.getLogger(DashboardPage.class.getName());
	private WebDriver driver;
	//Constructor for initialization of page elements
	public DashboardPage(WebDriver dr)
	{
		logger.info("Dashboard Page");
		this.driver=dr;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[class='oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-module']")
	private WebElement dashboardText;
	
	@FindBy(css="i[class='oxd-icon bi-caret-down-fill oxd-userdropdown-icon']")
	private WebElement arrow;
	
	@FindBy(linkText = "Logout")
	private WebElement logout;
	
	@FindBy(xpath="//span[text()='My Info']")
	private WebElement myinfo;
	
	@FindBy(xpath="//span[text()='Admin']")
	private WebElement admin;
	
	public boolean isDashboardDislpayed()
	{
		boolean b=false;
		try {
			b = dashboardText.isDisplayed();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return b;
	}
	
	public void logoutFromApplication()
	{
		arrow.click();
		//waitForElement(logout);
		logout.click();
	}
	public void clickOnMyInfoTab()
	{
		myinfo.click();
	}
	public void clickOnAdminTab()
	{
		admin.click();
	}
}
