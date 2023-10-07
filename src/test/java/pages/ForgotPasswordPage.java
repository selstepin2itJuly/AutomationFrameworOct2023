package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testbase.TestBase;

public class ForgotPasswordPage {
	static final Logger logger = LogManager.getLogger(ForgotPasswordPage.class.getName());
	private WebDriver driver;
	public ForgotPasswordPage(WebDriver dr)
	{
		logger.info("Forgot Password Page");
		this.driver=dr;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="button[type='button']")
	private WebElement cancel;
	
	@FindBy(css="button[type='submit']")
	private WebElement reset;
	
	@FindBy(css="input[name='username']")
	private WebElement username;
	
	public void clickOnCancelOnForgotPasswordPage()
	{
		cancel.click();
	}
	
	public boolean isResetButtonDisplayed()
	{
		boolean b =false;
		try {
			b = reset.isDisplayed();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return b;
	}
	
	public void enterUsername(String s)
	{
		username.sendKeys(s);
	}
}

