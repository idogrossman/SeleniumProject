package aaa.bbb.FinalProject.pages;

import java.util.List;
import java.util.Random;

import aaa.bbb.FinalProject.utils.*;
import aaa.bbb.FinalProject.utils.Reporter.ReporterStatus;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OurStorePage extends WebStoreCommon {

	By AproveButton = By.xpath("//button[@class='dismissButton']");
	By storeDetails = By.xpath("//a[.='Get directions']/..");
	By direction = By.xpath("//a[.='Get directions']");
	By storeOnMap = By.xpath("//div[contains(@style, 'z-index: ') ]//img[@src='http://maps.gstatic.com/mapfiles/transparent.png']");
	By storeGetScreenshot = By.xpath("//div[@class='gm-style-iw-d']");
	

	public OurStorePage(WebDriver driver) {
		super(driver);
	}

	public void selectStore() {

		try {
			Thread.sleep(2500);
			WebElement element = driver.findElement(AproveButton);
			if (element.isDisplayed() == true)
				ElementsActions.click(driver, element, "Clicking on google notification if exist.");
			List<WebElement> stores = driver.findElements(storeOnMap);
			//Choosing random store from the map existing stores.
			Random rn = new Random();
			int randomStore = rn.nextInt(stores.size()) + 1;
			Reporter.reportStep(ReporterStatus.INFO, "The chosen store number from the list is: " + randomStore);
			ElementsActions.click(driver, stores.get(randomStore), "Click on random store.");
		} catch (Exception e) {
			Reporter.reportError(driver, "The exception for selectStore function. "  + e.getMessage());
		}

	}	

	public void printTheStoreDetail() {

		//there is unstable with class, sometime it run and sometime not
		try {
			WebElement element = driver.findElement(storeDetails);
			ElementsActions.waitForVisibility(driver, direction);
			//Print store details
			ElementsActions.takeItemScreenshot(driver, picPath1, storeGetScreenshot, "Store detail");
			picNum1 ++;
			String storeDetail = element.getAttribute("innerHTML");
			String[] parts = storeDetail.split("<p>");
			//Cleaning all unecassery characters
			parts[0] = parts[0].replace("</b>", "").replace("<br>", "\n").replace("<b>", "");

			System.out.println("\nStore Address:\n***************\n" + parts[0]);
			List<WebElement> days  = element.findElements(By.xpath(".//strong"));
			List<WebElement> hours  = element.findElements(By.xpath(".//span"));

			for (int i= 1; i < days.size()  ; i++) 
				System.out.println(days.get(i).getText() + "  " + hours.get(i).getText());

		} catch (Exception e) {
			Reporter.reportError(driver, "The exception for printTheStoreDetail function. "  + e.getMessage());
		}
	}	

}

