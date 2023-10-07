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

public class MyInfoTest {
	static final Logger logger = LogManager.getLogger(MyInfoTest.class.getName());
	private WebDriver driver;
	private TestBase testbase = new TestBase();
	private LoginPage loginpage;
	private DashboardPage dash;
	private MyInfoPage mip; 
  @Test(priority= 1,enabled=true, description="Validate Info Menu Count")
  public void verifyMyInfoMenuCount_TC005() throws InterruptedException {
	  		dash.clickOnMyInfoTab();
	  		TestUtils.addScreenshotToReport(driver);
	  		Assert.assertEquals(mip.isPersonalDetailsDisplayed(), true);
	  		Reporter.log("Exp:"+12);
	  		Reporter.log("Act:"+mip.verifyMyInfoMenuCount());
	  	   	Assert.assertEquals(mip.verifyMyInfoMenuCount(),12);
	  	   	dash.logoutFromApplication();
	  }
  
  @Test(priority= 2,enabled=true, description="Validate Info menu text", dependsOnMethods="verifyMyInfoMenuCount_TC005")
  public void verifyMyInfoMenuText() throws InterruptedException
  {
	  List<String> exp = new ArrayList<String>();
	  exp.add("Personal Details");
	  exp.add("Contact Details");
      exp.add("Emergency Contacts");
      exp.add("Dependents");
      exp.add("Immigration");
      exp.add("Job");
      exp.add("Salary");
      exp.add("Tax Exemptions");
      exp.add("Report-to");
      exp.add("Qualification");
      exp.add("Membership");
      dash.clickOnMyInfoTab();
      TestUtils.addScreenshotToReport(driver);
      List<String> act = mip.getMenuOptionsText();
      SoftAssert sa = new SoftAssert();
      //sa.assertEquals(act, exp);
      for(int i=0;i<exp.size();i++)
      {
        sa.assertEquals(act.get(i), exp.get(i));
        Reporter.log(act.get(i)+" --> "+exp.get(i));
      }
      sa.assertAll();
      dash.logoutFromApplication();
      
  }
 
  @BeforeMethod
  public void beforeMethod() throws IOException, InterruptedException {
	  driver = testbase.getInstance();
	  logger.info("Browser opened and url launched");
	  loginpage = new LoginPage(driver);
	  dash = new DashboardPage(driver);
	  mip = new MyInfoPage(driver);
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
