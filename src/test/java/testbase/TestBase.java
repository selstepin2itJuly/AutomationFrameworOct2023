package testbase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.TestUtils;


public class TestBase {

	static final Logger logger = LogManager.getLogger(TestBase.class.getName());
	private WebDriver driver;
	public Properties prop;
	public String browserType;
	BrowserSupport browser; 
	public WebDriver getInstance() throws IOException, InterruptedException
	{
		logger.info("Test Base ->");
		logger.debug("Test Base ->");
		
		//Read data from config.properties
		String config = "./src/test/resources/config.properties";
		File file = new File(config);
		FileInputStream inStream = new FileInputStream(file);
		prop = new Properties();
		prop.load(inStream);
		browserType = prop.getProperty("browser");
		if(browserType.equalsIgnoreCase("chrome"))
		{
			browser=BrowserSupport.CHROME;
		}else if(browserType.equalsIgnoreCase("firefox"))
		{
			browser = BrowserSupport.FIREFOX;
		}else if(browserType.equalsIgnoreCase("edge"))
		{
			browser = BrowserSupport.EDGE;
		}
		//
		
		switch(browser)
		{
		case CHROME:
				System.setProperty("webdriver.chrome.driver",prop.getProperty("driverChrome"));
				ChromeOptions opt = new ChromeOptions();
				//opt.addArguments("start-fullscreen");
				opt.addArguments("--start-maximized");
				opt.setExperimentalOption("excludeSwitches",Arrays.asList("disable-popup-blocking"));
				driver = new ChromeDriver(opt);
				break;
		case FIREFOX:
			System.setProperty("webdriver.gecko.driver",prop.getProperty("driverFirefox"));
			FirefoxOptions optF = new FirefoxOptions();
			//opt.addArguments("start-fullscreen");
			optF.addArguments("--start-maximized");
			optF.setCapability("excludeSwitches",Arrays.asList("disable-popup-blocking"));
			//driver = new FirefoxDriver(optF);
			driver = new FirefoxDriver();
			break;
		case EDGE:
			System.setProperty("webdriver.edge.driver",prop.getProperty("driverEdge"));
			EdgeOptions optE = new EdgeOptions();
			//opt.addArguments("start-fullscreen");
			optE.addArguments("--start-maximized");
			//optE.setExperimentalOption("excludeSwitches",Arrays.asList("disable-popup-blocking"));
			driver = new EdgeDriver(optE);
			//driver = new EdgeDriver();
			break;
		default:
			new Throwable().initCause(null);
			
		}	
		//driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		TestUtils.addScreenshotToReport(driver);
		return driver;
	}
	
	//Explicit Wait
	public void waitForElementClickable(WebElement ele)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15),Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}
	
	//Explicit Wait
	public void waitForElementVisible(WebElement ele)
	{
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15), Duration.ofSeconds(4));
			wait.until(ExpectedConditions.visibilityOf(ele));
	}
	public void waitForElement(WebElement element) {
	    FluentWait<WebDriver> wait = new FluentWait<>(driver)
	            .withTimeout(Duration.ofSeconds(10))
	            .pollingEvery(Duration.ofMillis(500))
	            .ignoring(NoSuchElementException.class);

	    wait.until(ExpectedConditions.visibilityOf(element));
	}
}
