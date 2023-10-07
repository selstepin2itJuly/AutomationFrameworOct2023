package utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.testng.Reporter;

public class TestUtils {

	public static void scrollToElementJS(WebDriver dr, WebElement ele)
	{
		JavascriptExecutor je=(JavascriptExecutor) dr;		
		je.executeScript("arguments[0].scrollIntoView();", ele);//true
		je.executeScript("window.scrollBy(0,-400);", "");
	}
	
	public static void clickOnElementJS(WebDriver dr, WebElement ele)
	{
		JavascriptExecutor je=(JavascriptExecutor) dr;		
		je.executeScript("arguments[0].click();", ele);//true
	}
	
	public static void scrollToElementActions(WebDriver dr, WebElement ele)
	{
		Actions ac = new Actions(dr);
		ac.scrollToElement(ele).perform();
		((JavascriptExecutor)dr).executeScript("window.scrollBy(0,400);", "");	
	}
	
	public static void moveToElementActions(WebDriver dr, WebElement ele)
	{
		Actions ac = new Actions(dr);
		ac.moveToElement(ele).perform();
	}
	
	public static void captureImageInFile(WebDriver dr) throws IOException
	{
		File file = new File("screenshot/"+getDateForFolder());
		System.out.println(file.isDirectory());
		if(!file.isDirectory())
		{
			file.mkdirs();
		}else {
			System.out.print("Folder Exist!");
		}
		TakesScreenshot sc = (TakesScreenshot) dr;
		File scr = sc.getScreenshotAs(OutputType.FILE);
		FileHandler.copy(scr, new File(file+"/"+getDate()+"_image.jpg"));//png & jpg

		//getDate();
	}
	
	public static void addScreenshotToReport(WebDriver dr) throws InterruptedException
	{
		Thread.sleep(3000);
		TakesScreenshot sh = (TakesScreenshot) dr;
		String src = sh.getScreenshotAs(OutputType.BASE64);
		String image="<img src=\"data:image/png;base64,"+src+"\" height=\"600\" width=\"800\" />";
		Reporter.log(image);
	}
	private static String getDate()
	{
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY_MM_dd_HH_mm_ss_SSS");
		//System.out.println(sdf.format(dt));
		return sdf.format(dt);
	}
	
	private static String getDateForFolder()
	{
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY_MM_dd");
		System.out.println(sdf.format(dt));
		return sdf.format(dt);
	}
	
}


