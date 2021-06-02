package aaa.bbb.FinalProject.pages;

import java.util.List;
import aaa.bbb.FinalProject.utils.*;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchPage extends WebStoreCommon{

	By addItem = By.xpath("//a[@class='add_to_compare']");
	By pictures = By.xpath("//div[@class='product-image-container']");
	By close = By.xpath("//a[@title='Close']");
	By error = By.xpath("//p[@class='fancybox-error']");

	public SearchPage(WebDriver driver) {
		super(driver);
	}

	public void addToCompareList() {

		try {
			List<WebElement> pics = driver.findElements(pictures);
			List<WebElement> items = driver.findElements(addItem);

			for (int i=0; i<pics.size(); i++) {

					ElementsActions.mouseOver(driver, pics.get(i), "Add to compare.");
					ElementsActions.click(driver, items.get(i), "Click on add to compare.");
				
				if (i > 2) {
					Reporter.reportFailStep("To many items are added");
					WebElement element = driver.findElement(close);
					ElementsActions.takeItemScreenshot(driver, "D:\\img\\Error.png", error, "Printing item with the error");
					ElementsActions.click(driver, element, "Click on close.");
				}
				Thread.sleep(2000);
			}
		} catch (Exception e) {
			Reporter.reportError(driver, "The exception for addToCompareList function. "  + e.getMessage());
		}
	}	
}
