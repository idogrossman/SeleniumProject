package aaa.bbb.FinalProject.pages;

import java.util.List;
import aaa.bbb.FinalProject.utils.*;
import aaa.bbb.FinalProject.utils.Reporter.ReporterStatus;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MyOrderPage extends WebStoreCommon{

	By orderTable = By.id("order-list");
	By rows = By.tagName("tr");
	By reOrderXpath = By.xpath(".//td[1]/following-sibling::td[@class='history_detail footable-last-column']//a[@class='link-button']");
;
	public MyOrderPage(WebDriver driver) {
		super(driver);
	}

	public List orderReferenceList () {
		try {
			
			WebElement table = driver.findElement(orderTable);
			List<WebElement> orderReference = table.findElements(rows);
			return orderReference;
			
		}catch (Exception e) {
			Reporter.reportError(driver, "The exception for orderReferenceList function. "  + e.getMessage());
			return null;

		}
	}
	
	public void reOrderPurchase (int PurchaseNum) {
		try {
			
			WebElement reOrder;
			List<WebElement> referenceList = orderReferenceList();
			//The size is reduce by 1 due to the table header row
			Reporter.reportStep(ReporterStatus.INFO, "Printing list size and the item that has been chooosen to be reorder.");
			System.out.println("The list size: " + (referenceList.size()-1));
			System.out.println("The item your have chosen to re-order is: " + referenceList.get(PurchaseNum).findElement(By.xpath(".//td[1]")).getText());
			reOrder = referenceList.get(PurchaseNum).findElement(reOrderXpath);
			System.out.println("reorder is: " + reOrder.getText());
			ElementsActions.click(driver, reOrder, "Clicking on re-order item");
			
		}catch (Exception e) {
			Reporter.reportError(driver, "The exception for reOrderPurchase function. "  + e.getMessage());
		}
	}

}
