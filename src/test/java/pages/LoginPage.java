package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testbase.TestBase;

public class LoginPage {
	static final Logger logger = LogManager.getLogger(LoginPage.class.getName());
	private WebDriver driver;
	//Constructor for initialization of page elements
	public LoginPage(WebDriver dr)
	{
		logger.info("Login Page");
		this.driver=dr;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="username")
	private WebElement user;
	
	@FindBy(css="[placeholder='Password']")
	private WebElement pass;
	
	@FindBy(xpath="//button[@type='submit']")
	private WebElement login;
	
	@FindBy(className="orangehrm-login-forgot")
	private WebElement fgp;
	
	@FindBy(css="[class='oxd-text oxd-text--p oxd-alert-content-text']")
	private WebElement error;
	
	public void loginToApp(String username, String password)
	{
		user.sendKeys(username);
		pass.sendKeys(password);
		login.click();
	}
	
	public void clickOnForgotPassword()
	{
		fgp.click();
	}
	
	public boolean isErrorDisplayed()
	{
		boolean b=false;
		try {
			b = error.isDisplayed();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return b;
	}
	
	public boolean isLoginButtonDisplayed()
	{
		boolean b=false;
		try {
			b = login.isDisplayed();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return b;
	}
	public boolean isErrorTextCorrect()
	{
		return error.getText().equals("Invalid credentials");
	}
}
