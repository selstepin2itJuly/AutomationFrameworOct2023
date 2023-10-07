package testcases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.AdminPage;
import pages.DashboardPage;
import pages.ForgotPasswordPage;
import pages.LoginPage;
import pages.MyInfoPage;
import testbase.TestBase;
import utilities.TestUtils;

import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;

public class AdminTest {
	
	static final Logger logger = LogManager.getLogger(AdminTest.class.getName());
	private WebDriver driver;
	private TestBase testbase = new TestBase();
	private LoginPage loginpage;
	private DashboardPage dash;
	private AdminPage admin;
  @Test(priority= 1,enabled=true, description="Validate User and Role")
  public void verifyUserAndUserRole_TC007() throws InterruptedException {
	  		logger.info("Admin Test");
	  		dash.clickOnAdminTab();
	  		logger.info("Clicked on Admin Tab");
	  		TestUtils.addScreenshotToReport(driver);
	  		Assert.assertEquals(admin.isAdminHeaderDisplayed(),true);
	  		String name = "Melan Peiris";//"Linda Anderson";
	  		String role = "ESS";//"Admin";
	  		logger.info("Search-->"+name+"-->"+role);
	  		Map<String, String> act = admin.searchUser(name, role);
	  		Map<String,String> exp= new HashMap<String, String>();
	  		exp.put(name, role);
	  		logger.info("Found-->"+act.toString());
	  		Reporter.log(exp.toString());
	  		Reporter.log(act.toString());
	  		SoftAssert sa = new SoftAssert();
	  		for(String s: exp.keySet())
	  		{
	  			sa.assertEquals(exp.get(s), act.get(s));
	  		}
			//Assert.assertEquals(act, exp);
	  		sa.assertAll();
	  		dash.logoutFromApplication();
	  }
  
 
  @BeforeMethod
  public void beforeMethod() throws IOException, InterruptedException {
	  driver = testbase.getInstance();
	  logger.info("Browser Opened and URL launched");
	  loginpage = new LoginPage(driver);
	  dash = new DashboardPage(driver);
	  admin = new AdminPage(driver);
	  loginpage.loginToApp("Admin", "admin123");
	  logger.info("User credentials entered");
	  Assert.assertEquals(dash.isDashboardDislpayed(),true);
	  logger.info("Dashboard Visible");
  }

  @AfterMethod
  public void afterMethod() {
	  driver.quit();
	  logger.info("Browser Closed");
  }

}
