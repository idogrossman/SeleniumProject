package aaa.bbb.FinalProject.pages;

import java.io.File;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import aaa.bbb.FinalProject.utils.ElementsActions;
import aaa.bbb.FinalProject.utils.Reporter;
import aaa.bbb.FinalProject.utils.TestValidation;
import aaa.bbb.FinalProject.utils.Reporter.ReporterStatus;



public class BestSellerPage extends WebStoreCommon{

	By items = By.xpath("//h5[@itemprop='name']");
	By pageLoaded = By.xpath("//h4[.='Follow us']");
	By itemName = By.xpath("//h1[@itemprop='name']");
	TableAndAddToCartPage tableAndAddToCartPage = new TableAndAddToCartPage(driver);

	public BestSellerPage(WebDriver driver) {
		super(driver);
	}

	public String [] printBestSellerItems() {

		try {
			List<WebElement> bestSellerItems  = driver.findElements(items);
			String [] bItems = new String[bestSellerItems.size()]; 
			Reporter.reportStep(ReporterStatus.INFO, "\nPrinting best seler items.");;
			for (int i=0; i < bestSellerItems.size()  ; i++) {
				System.out.println(i+1 + " item is: " + bestSellerItems.get(i).getText());
				bItems[i] = bestSellerItems.get(i).getText();
			}
			return bItems;
		}catch(Exception e) {
			Reporter.reportError(driver, "The exception for printBestSellerItems function. "  + e.getMessage());
			return null;
		}
	}

	public String printItemDetails(String item) {

		String itemNameOnsite;
		try {
			WebElement element;
			element = driver.findElement(By.xpath("//a[contains(@title, '" + item + "') and @class='product-name']"));
			ElementsActions.click(driver, element, "Clicking on product name.");
			System.out.println("**************** " + item + " details: ****************");
			tableAndAddToCartPage.printItemDetail();
			itemNameOnsite = TestValidation.getVerificationValue(driver, itemName);
			//for choosing another item in this same page we navigate back
			driver.navigate().back();
			Reporter.reportStep(ReporterStatus.INFO, "Go back to the previous page");
			ElementsActions.waitForVisibility(driver, pageLoaded);

		}catch(Exception e) {
			Reporter.reportError(driver, "The exception for printItemDetails function. "  + e.getMessage());
			return null;
		}
		return itemNameOnsite;
	}


}
