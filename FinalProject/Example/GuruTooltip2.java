package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GuruTooltip2 extends GuruCommon {
	
	static By downLoad = By.xpath("//a[@id='download_now']");
	
	public GuruTooltip2(WebDriver driver) {
		super(driver);
	}

	public static void mouseOver() {

		Actions action = new Actions(driver);

		try {

			WebElement element = driver.findElement(downLoad);
			action.moveToElement(element).build().perform();
			System.out.println("Mouse is over: " + element.getText() + ".");

		}catch(Exception e) {
			System.out.println("The exception for mouseOver function. " + e.getMessage());

		}
	}

	
}
