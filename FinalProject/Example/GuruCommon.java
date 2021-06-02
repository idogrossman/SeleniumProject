package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class GuruCommon {

	public static WebDriver driver;

	public GuruCommon(WebDriver driver) {

		this.driver = driver;

	}

	public void selectTopMenu(String fullMenu) {
		
		String[] parts;
		String topMenu = "", subMenu;

		if (fullMenu.contains(";"))
		{
			parts = fullMenu.split(";");
			topMenu = parts [0];
			subMenu = parts [1];
			selectTopMenu(topMenu, subMenu);
		}
		else
			selectTopMenu(fullMenu, "");


	}

	public void selectTopMenu(String menu, String subMenu) {


		if(!subMenu.equals("")) {
			WebElement topItem = driver.findElement(By.xpath("//a[contains(text(),'" + menu + "') and @class='dropdown-toggle']"));
			topItem.click();
			WebElement subItem = driver.findElement(By.xpath("//ul[@class='dropdown-menu']//a[contains(text(),'" + subMenu + "')]"));
			subItem.click();
		}
		else {
			WebElement topItem = driver.findElement(By.xpath("//a[contains(text(),'" + menu + "')]"));
			topItem.click();
		}

	}


	public WebElement checkForInvisibility(By by) {

		WebElement element = driver.findElement(by);

		try {

			WebDriverWait wait10 = new WebDriverWait(driver, 10); 
			if (wait10.until(ExpectedConditions.invisibilityOfElementLocated(by)) == true)
				System.out.println("Item: " + driver.findElement(by).getText() +  " isn't visible");
			else 
				System.out.println("Item: " + driver.findElement(by).getText() +  " is visible");

		}
		catch(Exception e) {
			System.out.println("The exception for wait for invisibility function. " + e.getMessage());
		}
		return element;
	}

	public static WebElement waitForVisibility(By by) {

		WebElement element = driver.findElement(by);

		try {

			WebDriverWait wait10 = new WebDriverWait(driver, 10); 
			wait10.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
			System.out.println("Item: " + driver.findElement(by).getText() +  " is visible");

		}
		catch(Exception e) {
			System.out.println("The exception for wait for visibility function. " + e.getMessage());
		}
		return element;
	}


	public static void clickOnElement(By by) {

		WebElement element;
		try {

			element = driver.findElement(by);
			element.click(); 

		}catch(Exception e) {
			System.out.println("The exception for clickOnElement function. " + e.getMessage());

		}
	}


	public static WebElement enterInputValue(By by, String input, String name) {


		try {
			WebElement e = driver.findElement(by);
			e.clear();
			e.sendKeys(input);
			System.out.println("Input value is " + name);
			return e;

		}
		catch(Exception e) {
			System.out.println("The exception for enterInputValue function. " + e.getMessage());
			return null;
		}
	}

}
