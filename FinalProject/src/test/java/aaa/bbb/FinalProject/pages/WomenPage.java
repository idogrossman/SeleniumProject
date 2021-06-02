package aaa.bbb.FinalProject.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import aaa.bbb.FinalProject.utils.*;

public class WomenPage extends WebStoreCommon {
	
	TableAndAddToCartPage tableAndAddToCartPage = new TableAndAddToCartPage(driver);
	GoToPaymentWindow goToPaymentWindow = new GoToPaymentWindow(driver);
	WebStoreCommon webStoreCommon = new WebStoreCommon(driver);
	By wishList = By.xpath("//a[@title='My wishlists']");
	By itemList = By.xpath("//td[@class='cart_product']//img");

	public WomenPage(WebDriver driver) {
		super(driver);
	}

	public void selectParameter (String param) {
		try {

			WebElement element = driver.findElement(By.xpath("//a[contains(text(), '" + param + "')]//ancestor::li[contains(@class, 'nomargin')]//input[@type='checkbox']"));
			ElementsActions.click(driver, element, "Click on select element.");
			ElementsActions.waitForVisibility(driver, pageLoaded);
			element.findElement(By.xpath("//span[.='Add to cart']"));
			ElementsActions.click(driver, element, "Adding to cart.");

		}catch(Exception e) {
			Reporter.reportError(driver, "The exception for selectParameter function. "  + e.getMessage());
		}
	}

	public void selectColor (String color) {
		try {

			WebElement element = driver.findElement(By.xpath("//a[contains(text(), '" + color + "')]"));
			ElementsActions.click(driver, element, "Choosing a color.");
			ElementsActions.waitForVisibility(driver, pageLoaded);

		}catch(Exception e) {
			Reporter.reportError(driver, "The exception for selectColor function. "  + e.getMessage());
		}
	}

	public void addToCart (String item) {
		try {

			WebElement element = driver.findElement(By.xpath("//a[contains(@title,'" + item + "') and @itemprop='url']"));
			String href = element.getAttribute("href");
			driver.get(href);
			ElementsActions.waitForVisibility(driver, pageLoaded);

		}catch(Exception e) {
			Reporter.reportError(driver, "The exception for addToCart function. "  + e.getMessage());
		}
	}

	public void addToCart (String item, String itemQuantity, String size, String color) {
		try {

			addToCart(item);
			tableAndAddToCartPage.selectItemParameters(itemQuantity, size, color);
			goToPaymentWindow.proceedToCheckout();
	
		}catch(Exception e) {
			Reporter.reportError(driver, "The exception for addToCart function. "  + e.getMessage());
		}
	}
		
	public void wishList (String item) {
		try {

			addToCart(item);
			tableAndAddToCartPage.addToWishList();

		}catch(Exception e) {
			Reporter.reportError(driver, "The exception for wishList function. "  + e.getMessage());
		}
	}
	
	public void NavigateTowishListPage (WebDriver driver) {
		try {
			WebElement element;
			webStoreCommon.selectFootMenu("Manage my customer account");
			element = driver.findElement(wishList);
			ElementsActions.click(driver, element, "Clicking on my wish list.");

		}catch(Exception e) {
			Reporter.reportError(driver, "The exception for NavigateTowishListPage function. "  + e.getMessage());
		}
	}
	
	public String [] getItemsList () {
		
		String [] listOfItem;
		try {
			List<WebElement> items  = driver.findElements(itemList);
			listOfItem = new String[items.size()]; 
			
			for (int i=0; i < items.size()  ; i++) 
				listOfItem[i] = items.get(i).getAttribute("alt");		

		}catch(Exception e) {
			Reporter.reportError(driver, "The exception for getItemList function. "  + e.getMessage());
			return null;
		}
		return listOfItem;
	}
}
