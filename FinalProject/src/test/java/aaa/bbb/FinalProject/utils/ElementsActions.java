package aaa.bbb.FinalProject.utils;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import aaa.bbb.FinalProject.pages.WebStoreCommon;
import aaa.bbb.FinalProject.utils.Reporter.ReporterStatus;

public class ElementsActions  {

	public static void click(WebDriver driver, WebElement element, String name) {

		try {

			String tagName = element.getTagName();
			element.click();
			Reporter.reportStep(ReporterStatus.INFO, "Clicked on: " + name);

		}catch(Exception e) {
			Reporter.reportError(driver, "The exception for click function. "  + e.getMessage());
		}
	}

	public static void click(WebDriver driver, By by, String name) {

		try {
			WebElement element = driver.findElement(by);
			click(driver, element, name);

		}catch(Exception e) {
			Reporter.reportError(driver, "The exception for click function. "  + e.getMessage());
		}
	}


	public static boolean isDisplay(WebDriver driver, WebElement element, String name) {

		try {
			if (element.isDisplayed() == true)
				Reporter.reportPassStep("Error is display.");

		}catch(Exception e) {
			Reporter.reportError(driver, "None existing element - Please continue to next step");
			return false;
		}
		return true;

	}


	public static void mouseOver(WebDriver driver ,By by, String itemDescription) {

		Actions action = new Actions(driver);

		try {

			WebElement element = driver.findElement(by);
			mouseOver(driver, element, itemDescription);

		}catch(Exception e) {
			Reporter.reportError(driver, "The exception for mouseOver function. "  + e.getMessage());

		}
	}

	public static void mouseOver(WebDriver driver, WebElement element , String itemDescription) {

		Actions action = new Actions(driver);

		try {

			action.moveToElement(element).build().perform();
			Reporter.reportStep(ReporterStatus.INFO, "Mouse over on the element: " + itemDescription);

		}catch(Exception e) {
			Reporter.reportError(driver, "The exception for mouseOver function. "  + e.getMessage());
		}
	}

	public static WebElement waitForVisibility(WebDriver driver, By by) {

		WebElement element = driver.findElement(by);

		try {

			WebDriverWait wait10 = new WebDriverWait(driver, 10); 
			wait10.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
			Reporter.reportStep(ReporterStatus.INFO, "Item: " + driver.findElement(by).getText() +  " is visible");
		}
		catch(Exception e) {
			Reporter.reportError(driver, "The exception for waitForVisibility function. "  + e.getMessage());
		}
		return element;
	}


	public static WebElement enterInputValue(WebDriver driver, By by, String input, String name) {


		try {
			WebElement element = driver.findElement(by);
			element.clear();
			Reporter.reportStep(ReporterStatus.INFO, "Clear field from privious keys.");
			element.sendKeys(input);
			Reporter.reportStep(ReporterStatus.INFO, "Input value is " + name + ".");
			//System.out.println("Input value is " + name);
			return element;

		}
		catch(Exception e) {
			Reporter.reportError(driver, "The exception for enterInputValue function. "  + e.getMessage());
			return null;
		}
	}

	public static void selectItemFromList(WebDriver driver, By by, String value) {

		try {

			WebElement elemet = driver.findElement(by);
			Select item = new Select(elemet);
			item.selectByVisibleText(value);
			Reporter.reportStep(ReporterStatus.INFO, "The value that has been choosen is: " + value);
			//System.out.println("The value that has been choosen is: " + value);

		} catch (Exception e) {
			Reporter.reportError(driver, "The exception for selectItemFromList function. "  + e.getMessage());
		}

	}

	public static void clickOnXYCordinate(WebDriver driver, WebElement element) {

		try {

			Actions actions = new Actions(driver); 
			int getX = element.getLocation().getX(); 
			int getY = element.getLocation().getY(); 
			Reporter.reportStep(ReporterStatus.INFO, "Clicking on the element cordinates.");
			actions.moveByOffset(getX+1, getY+1).click(); 
			actions.build().perform(); 

		}catch (Exception e) {
			Reporter.reportError(driver, "The exception for clickOnXYCordinate function. "  + e.getMessage());
		}
	}

	public static void takeFullScreenshot(WebDriver driver, String imagePath , String imageDesctiption)  {
		try {

			Reporter.reportStep(ReporterStatus.INFO, imageDesctiption);
			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(src, new File(imagePath));
		} catch (Exception e) {
			Reporter.reportError(driver, "The exception for takeFullScreenshot function. "  + e.getMessage());
		}
	}


	public static void takeItemScreenshot(WebDriver driver, String imagePath, String item, String imageDesctiption)  {
		try {
			WebElement element = driver.findElement(By.xpath("//img[contains(@title, '" + item + "')]"));
			Reporter.reportStep(ReporterStatus.INFO, imageDesctiption);
			File src = ((TakesScreenshot) element).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(src, new File(imagePath));

		} catch (Exception e) {
			Reporter.reportError(driver, "The exception for takeItemScreenshot function. "  + e.getMessage());
		}
	}

	public static void takeItemScreenshot(WebDriver driver, String imagePath, By by, String imageDesctiption)  {
		try {
			WebElement element = driver.findElement(by);
			Reporter.reportStep(ReporterStatus.INFO, imageDesctiption);
			File src = ((TakesScreenshot) element).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(src, new File(imagePath));

		} catch (Exception e) {
			Reporter.reportError(driver, "The exception for takeItemScreenshot function. "  + e.getMessage());
		}
	}

}
