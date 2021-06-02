package aaa.bbb.FinalProject.pages;

import java.util.List;

import javax.swing.text.Element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import aaa.bbb.FinalProject.utils.*;
import aaa.bbb.FinalProject.utils.Reporter.ReporterStatus;

public class TableAndAddToCartPage extends WebStoreCommon {

	By table = By.xpath("//table");
	By rows = By.xpath("//tr");
	By quantity = By.id("quantity_wanted");
	By selectSize = By.id("group_1");
	By submit = By.xpath("//button[@name='Submit']");
	By addToCartButton = By.xpath("//button[@class='exclusive']");
	By addToCartWindow = By.id("layer_cart");
	By wishList = By.id("wishlist_button");
	By wishListMessage = By.xpath("//p[@class='fancybox-error']");
	By close = By.xpath("//a[@title='Close']");

	public TableAndAddToCartPage(WebDriver driver) {
		super(driver);
	}

	public void printItemDetail() {

		try {
			List<WebElement> tableRows  = driver.findElements(rows);
			String split [] = new String [tableRows.size()];
			Reporter.reportStep(ReporterStatus.INFO, "Printing item details.");
			System.out.println("*****************************");	

			for (int i=0; i < tableRows.size()  ; i++) {

				System.out.print(tableRows.get(i).findElement(By.xpath(".//td[1]")).getText() + "  -  " + tableRows.get(i).findElement(By.xpath(".//td[2]")).getText() );
				System.out.println();
			}

			System.out.println("*****************************");	

		}catch(Exception e) {
			Reporter.reportError(driver, "The exception for printItemDetail function. "  + e.getMessage());
		}
	}

	public void selectQuantity (String itemQuantity) {
		try {

			Reporter.reportStep(ReporterStatus.INFO, "Select the count of the item.");
			ElementsActions.enterInputValue(driver, quantity, itemQuantity, "quantity " + itemQuantity);

		}catch(Exception e) {
			Reporter.reportError(driver, "The exception for selectQuantity function. "  + e.getMessage());
		}
	}

	public void selectSize (String size) {
		try {
			Reporter.reportStep(ReporterStatus.INFO, "Select the size of the item.");
			ElementsActions.selectItemFromList(driver, selectSize, size);

		}catch(Exception e) {
			Reporter.reportError(driver, "The exception for selectSize function. "  + e.getMessage());
		}
	}

	public void selectColor (String color) {
		try {

			Reporter.reportStep(ReporterStatus.INFO, "Select the color of the item.");
			WebElement element = driver.findElement(By.xpath("//a[@title='" + color + "']"));
			System.out.println("Input color is " + color);
			ElementsActions.click(driver, element, color);
			
		}catch(Exception e) {
			Reporter.reportError(driver, "The exception for selectColor function. "  + e.getMessage());
		}
	}

	public void selectItemParameters (String itemQuantity, String size, String color) {
		try {

			selectQuantity(itemQuantity);
			selectSize(size);
			selectColor(color);
			submit();
			ElementsActions.waitForVisibility(driver, addToCartWindow);

		}catch(Exception e) {
			Reporter.reportError(driver, "The exception for selectItemParameters function. "  + e.getMessage());
		}
	}

	public void addToWishList () {
		try {

			WebElement element = driver.findElement(wishList);
			Reporter.reportStep(ReporterStatus.INFO, "Add item to wish list.");
			ElementsActions.click(driver, element, "Click on wishlist button.");
			//ElementsActions.clickOnXYCordinate(driver, element);
			Reporter.reportStep(ReporterStatus.INFO, "Print site notification:");
			System.out.println(driver.findElement(wishListMessage).getText()); 
			ElementsActions.click(driver, close, "Closing site notification.");

		}catch(Exception e) {
			Reporter.reportError(driver, "The exception for addToWishList function. "  + e.getMessage());
		}
	}

	public void submit () {
		try {

			WebElement elemet = driver.findElement(submit);
			ElementsActions.click(driver, elemet, "Clicking on submit");

		}catch(Exception e) {
			Reporter.reportError(driver, "The exception for submit function. "  + e.getMessage());
		}
	}
}
