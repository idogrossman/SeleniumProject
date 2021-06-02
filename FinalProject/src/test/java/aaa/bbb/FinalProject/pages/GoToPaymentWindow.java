package aaa.bbb.FinalProject.pages;

import org.openqa.selenium.By;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import aaa.bbb.FinalProject.utils.*;


public class GoToPaymentWindow extends WebStoreCommon{
	
	By checkout = By.xpath("//a[@title='Proceed to checkout']");

	public GoToPaymentWindow(WebDriver driver) {
		super(driver);
	}

	public void proceedToCheckout () {
		try {

			WebElement elemet = driver.findElement(checkout);
			ElementsActions.click(driver, elemet, "Click on Check out.");

		}catch(Exception e) {
			Reporter.reportError(driver, "The exception for proceedToCheckout function. "  + e.getMessage());
		}
	}
}
