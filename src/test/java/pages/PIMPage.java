package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PIMPage {

	private WebDriver driver;
	public PIMPage(WebDriver dr)
	{
		this.driver=dr;
		PageFactory.initElements(driver, this);
	}
}
