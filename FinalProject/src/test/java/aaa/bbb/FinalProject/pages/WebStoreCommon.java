package aaa.bbb.FinalProject.pages;

import static org.testng.Assert.assertTrue;
import aaa.bbb.FinalProject.utils.*;
import aaa.bbb.FinalProject.utils.Reporter.ReporterStatus;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.xml.xpath.XPath;
import aaa.bbb.FinalProject.utils.*;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.SendKeysAction;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;


import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class WebStoreCommon {

	public static WebDriver driver;
	By pageLoaded = By.xpath("//h4[.='Follow us']");
	static By searchField = By.id("search_query_top");
	static By submitSearch = By.xpath("//button[@name='submit_search']");
	static By logout = By.xpath("//a[@class='logout']");
	static int picNum1 = 0;
	static String picPath1 = "D:\\img\\imageScreenshot_" + picNum1 + ".png";
	
	public WebStoreCommon(WebDriver driver) {
		WebStoreCommon.driver = driver;
	}

	public void selectFootMenu(String fullMenu) {

		try {
			String[] parts;
			String footItem = "", subMenu;

			if (fullMenu.contains(";"))
			{
				parts = fullMenu.split(";");
				footItem = parts [0];
				subMenu = parts [1];
				selectFootMenu(footItem, subMenu);
			}
			else
				selectFootMenu("", fullMenu);	
		} catch (Exception e) {
			System.out.println("Error in selectFootMenu function." + e.getMessage());
		}

	}

	public void selectFootMenu(String menu, String subMenu) {

		try {
			if(!menu.equals("")) {
				WebElement footItem = driver.findElement(By.xpath("//h4[.='" + menu + "']"));   
				footItem.click();
				WebElement subItem = driver.findElement(By.xpath("//a[contains(@title,'" + subMenu + "')]"));
				subItem.click();
			}
			else {
				WebElement footItem = driver.findElement(By.xpath("//a[@title='" + subMenu + "']"));
				footItem.click();
			}
			ElementsActions.waitForVisibility(driver, pageLoaded);
		} catch (Exception e) {;
			System.out.println("Error in selectFootMenu function." + e.getMessage());
		}
	}

	public static void usingHref (By by) {

		try {

			WebElement element = driver.findElement(by);
			String href = element.getAttribute("href");
			driver.get(href);
		}catch (Exception e) {
			System.out.println("The exception for usingHref function. "  + e.getMessage());

		}
	}

	public static void globalSearch (String searchString)  {
		try {
			ElementsActions.enterInputValue(driver, searchField, searchString, searchString);
			WebElement submit = driver.findElement(submitSearch);
			ElementsActions.click(driver, submit, "Click on submit");
		} catch (Exception e) {
			System.out.println("The exception for takeScreenshot function. " + e.getMessage());
		}
	}
	
	public static void sighout ()  {
		try {
			WebElement element = driver.findElement(logout);
			ElementsActions.click(driver, element, "Logout from the account.");
		} catch (Exception e) {
			System.out.println("The exception for sighout function. " + e.getMessage());
		}
	}
}
