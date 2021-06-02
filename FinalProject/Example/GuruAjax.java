package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GuruAjax extends GuruCommon{

	By checkButton = By.xpath("//p[@class='radiobutton']");
	
	public GuruAjax(WebDriver driver) {
		super(driver);
	}
	
	public void clickOnRadioButton(String radioText) {

		
		try {
			
			WebElement element;
			element = driver.findElement(By.xpath("//input[@value='" + radioText + "']"));
			element.click(); 
			Thread.sleep(1000);


			if (radioText == "Check")
				System.out.println("The notification is: " + driver.findElement(checkButton).getText());

		}catch(Exception e) {
			System.out.println("The exception for clickOnRadioButton function. " + e.getMessage());

		}
	}

}
