package aaa.bbb.FinalProject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import aaa.bbb.FinalProject.utils.*;
import aaa.bbb.FinalProject.utils.Reporter.ReporterStatus;

public class ContactUsPage extends WebStoreCommon{

	By subject = By.id("id_contact");
	By email = By.id("email");
	By orderRefrerance = By.xpath("//select[@name='id_order']");
	By message = By.id("message");
	By product = By.id("314345_order_products");
	By submit = By.id("submitMessage");
	By submitMessage = By.xpath("//p[@class='alert alert-success']");

	public ContactUsPage(WebDriver driver) {
		super(driver);
	}


	public void ContactUs (String reqSubject, String reqMail, String reqOrderReferance, String reqMessage, String reqProduct) {

		try {

			WebElement element;
			ElementsActions.selectItemFromList(driver, subject, reqSubject);
			ElementsActions.enterInputValue(driver, email, reqMail, reqMail);
			ElementsActions.selectItemFromList(driver, orderRefrerance, reqOrderReferance);
			ElementsActions.enterInputValue(driver, message, reqMessage, "message");
			ElementsActions.selectItemFromList(driver, product, reqProduct);
			ElementsActions.click(driver, submit, "Click on submit");
			ElementsActions.waitForVisibility(driver, pageLoaded);
			element = driver.findElement(submitMessage);
			Reporter.reportStep(ReporterStatus.INFO, "Printing the site notification after sending the request.");

		}catch (Exception e) {
			Reporter.reportError(driver, "The exception for ContactUs function. "  + e.getMessage());
		}
	}
}
