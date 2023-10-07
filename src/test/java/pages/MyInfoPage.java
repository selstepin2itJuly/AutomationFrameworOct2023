package pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testbase.TestBase;

public class MyInfoPage {
	static final Logger logger = LogManager.getLogger(MyInfoPage.class.getName());
	private WebDriver driver;
	public MyInfoPage(WebDriver dr)
	{
		logger.info("MyInfo Page");
		this.driver=dr;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="div[class='orangehrm-tabs-wrapper']>a")
	private List<WebElement> myinfomenu;
	
	public int verifyMyInfoMenuCount()
	{
		return myinfomenu.size();
	}
	
	public boolean isPersonalDetailsDisplayed()
	{
		boolean b = false;
		try {
			b = myinfomenu.get(0).isDisplayed();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return b;
	}
	
	public List<String> getMenuOptionsText()
	{
		List<String> temp = new ArrayList<String>();
		for(WebElement e:myinfomenu)
		{
			temp.add(e.getText());
		}
		return temp;
	}
	
}
