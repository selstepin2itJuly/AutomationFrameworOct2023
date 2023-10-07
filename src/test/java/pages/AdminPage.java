package pages;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

import testbase.TestBase;

public class AdminPage {
	static final Logger logger = LogManager.getLogger(AdminPage.class.getName());
	private WebDriver driver;
	public AdminPage(WebDriver dr)
	{
		logger.info("Admin Page");
		this.driver=dr;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//h6[text()='User Management']")
	private WebElement adminheader;
	
	public boolean isAdminHeaderDisplayed()
	{
		boolean b=false;
		try {
			b = adminheader.isDisplayed();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return b;
	}
	
	public Map<String, String> searchUser(String name, String role)
	{
		Map<String, String> usersDetail = new HashMap<String, String>();
		String user = null;
		String erole = null;
		int row=driver.findElements(By.cssSelector("[role=table]>div[class$=body]>div>div[role=row]>div:nth-child(3)>div")).size();
		Reporter.log(String.valueOf(row));
		for(int i=1;i<row;i++)
		{
			user = driver.findElement(By.cssSelector("[role=table] div[class$=card]:nth-child("+i+") div:nth-child(4) div")).getText();
			Reporter.log(user);
			if(user.equalsIgnoreCase(name))
			{
				erole = driver.findElement(By.cssSelector("[role=table] div[class$=card]:nth-child("+i+") div:nth-child(3) div")).getText();
				//Assert.assertEquals(r, role);
				Reporter.log(user+"-->" +erole);
				break;
			}
		}
		usersDetail.put(user, erole);
		return usersDetail;
		
	}
}
