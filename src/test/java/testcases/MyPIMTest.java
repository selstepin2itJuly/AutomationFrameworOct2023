package testcases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.DashboardPage;
import pages.ForgotPasswordPage;
import pages.LoginPage;
import pages.MyInfoPage;
import testbase.TestBase;
import utilities.TestUtils;

import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;

public class MyPIMTest {
	static final Logger logger = LogManager.getLogger(MyPIMTest.class.getName());
	private WebDriver driver;
	private TestBase testbase = new TestBase();
	private LoginPage loginpage;
	private DashboardPage dash;
	
  @Test(priority= 1,enabled=true, description="Validate Info Menu Count")
  public void verifyMyInfoMenuCount_TC005() throws InterruptedException {
	  		
	  }
  
  @Test(priority= 2,enabled=true, description="Validate Info menu text", dependsOnMethods="verifyMyInfoMenuCount_TC005")
  public void verifyMyInfoMenuText() throws InterruptedException
  {
	 
  }
 
  @BeforeMethod
  public void beforeMethod() throws IOException, InterruptedException {
	  driver = testbase.getInstance();
	  logger.info("Browser opened and url launched");
	  loginpage = new LoginPage(driver);
	  dash = new DashboardPage(driver);
	 
	  loginpage.loginToApp("Admin", "admin123");
	  logger.info("Credentials entered");
	  Assert.assertEquals(dash.isDashboardDislpayed(),true);
	  logger.info("Dashboard displayed"+dash.isDashboardDislpayed());
  }

  @AfterMethod
  public void afterMethod() {
	  driver.quit();
	  logger.info("Browser closed");
  }

}
