package testcases;

import org.testng.annotations.Test;

import pages.DashboardPage;
import pages.ForgotPasswordPage;
import pages.LoginPage;
import testbase.TestBase;
import utilities.TestUtils;

import org.testng.annotations.BeforeMethod;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.util.Constants;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class LoginTest {
	static final Logger logger = LogManager.getLogger(LoginTest.class.getName());
	private WebDriver driver;
	private TestBase testbase = new TestBase();
	private LoginPage loginpage;
	private DashboardPage dash;
	private ForgotPasswordPage fpp;
  @Test(priority= 1,enabled=true, description="Validate successfull login")
  public void verifyLoginSuccessfull_TC001() throws InterruptedException {
	 TestUtils.addScreenshotToReport(driver);
	 loginpage.loginToApp("Admin", "admin123");
	 logger.info("Credentials Entered");
	 boolean actual = dash.isDashboardDislpayed();
	 logger.info("Dashboard Displayed"+actual);
	 TestUtils.addScreenshotToReport(driver);
	 Assert.assertEquals(actual, true);
	 dash.logoutFromApplication();
	 logger.info("logout of application");
	 TestUtils.addScreenshotToReport(driver);
	  }
  @Test(priority= 2,enabled=true, description="Validate unsuccessfull login")
  public void verifyLoginUnsuccessfull_TC002() throws InterruptedException
 {
	  TestUtils.addScreenshotToReport(driver);
	  loginpage.loginToApp(utilities.Constants.user,utilities.Constants.pass);
	  logger.info("entered user credentials");
	  boolean actual = loginpage.isErrorDisplayed();
	  logger.info("Error Displaued"+actual);
	  TestUtils.addScreenshotToReport(driver);
	  Assert.assertEquals(actual, true);
	  Assert.assertEquals(loginpage.isErrorTextCorrect(), true);
 }
  
  @Test(priority= 3,enabled=true, description="Validate Cancel Button bring to login page")
  public void verifyForgotPasswordCancelButton() throws InterruptedException
  {
	  TestUtils.addScreenshotToReport(driver);
	  loginpage.clickOnForgotPassword();
	  logger.info("clicked on Forgot passwrod");
	  TestUtils.addScreenshotToReport(driver);
	  Assert.assertEquals(fpp.isResetButtonDisplayed(), true);
	  logger.info("Reset Button Displayed");
	  fpp.enterUsername("sample");
	  TestUtils.addScreenshotToReport(driver);
	  fpp.clickOnCancelOnForgotPasswordPage();
	  logger.info("clicked on Cancel Button");
	  TestUtils.addScreenshotToReport(driver);
	  Assert.assertEquals(loginpage.isLoginButtonDisplayed(), true);
  }
  
  @BeforeMethod
  public void beforeMethod() throws IOException, InterruptedException {
	  driver = testbase.getInstance();
	  logger.info("Browser opened and url launched");
	  loginpage = new LoginPage(driver);
	  dash = new DashboardPage(driver);
	  fpp = new ForgotPasswordPage(driver);
  }

  @AfterMethod
  public void afterMethod() {
	  driver.quit();
	  logger.info("Browser closed");
  }

}
