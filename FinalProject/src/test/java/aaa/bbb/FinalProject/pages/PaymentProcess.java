package aaa.bbb.FinalProject.pages;

import javax.swing.text.Element;
import aaa.bbb.FinalProject.utils.*;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PaymentProcess extends WebStoreCommon{

	By area = By.tagName("textarea");
	By submit = By.xpath("//a[contains(@title,'Proceed to checkout') and contains(@class, 'standard')]");
	By submitAddress = By.xpath("//button[@name='processAddress']");
	By submitCarrier = By.xpath("//button[@type='submit' and @name='processCarrier']");
	By termaggreement = By.id("cgv");
	By confirmPayment = By.xpath("//p[@id='cart_navigation']//button");

	
	
	String comment = "Please rup it as a present.";
	TableAndAddToCartPage tableAndAddToCartPage = new TableAndAddToCartPage(driver);

	public PaymentProcess(WebDriver driver) {
		super(driver);
	}

	public void addAComment (String comment) {
		try {

			ElementsActions.enterInputValue(driver, area, comment, "comment");

		}catch (Exception e) {
			Reporter.reportError(driver, "The exception for addAComment function. "  + e.getMessage());
		}
	}

	public void payment (String comment, String paymentMethod) {

		try {

			WebElement element = driver.findElement(submit);
			ElementsActions.click(driver, element, "Clicking on submit.");
			addAComment(comment);
			ElementsActions.click(driver, submitAddress, "Click on submit Address");		
			ElementsActions.click(driver, termaggreement, "Click on term aggreement");
			ElementsActions.click(driver, submitCarrier, "Click on submint carrier.");
			element = driver.findElement(By.xpath("//a[@title='" + paymentMethod +"']"));
			ElementsActions.click(driver, element, "Click on submint Paymenent method.");			
			ElementsActions.click(driver, confirmPayment, "Click on confirm method Paymenent method.");

		}catch (Exception e) {
			Reporter.reportError(driver, "The exception for payment function. "  + e.getMessage());
		}
	}

}
