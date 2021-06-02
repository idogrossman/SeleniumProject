package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GuruCostumer extends GuruCommon{

	By submit = By.xpath("//input[@name='submit']");
	By costumerNum = By.xpath("//table[@class='alt access']//h3");
	By label = By.xpath("//label[' ']");
	By xFname = By.xpath("//input[@id='fname']");
	By xLname = By.xpath("//input[@id='lname']");
	By xEmail = By.xpath("//input[@id='email']");
	By xAdress = By.xpath("//textarea[@id='message']");
	By xTelephone = By.xpath("//input[@id='telephoneno']");

	public GuruCostumer(WebDriver driver) {
		super(driver);
	}

	public void fillingTheForm (String fname,String lname, String email, String adress,String telephone) {

		try {

			clickOnElement(label);
			enterInputValue(xFname, fname, "First Name");
			enterInputValue(xLname, lname, "Last Name");
			enterInputValue(xEmail, email, "Mail");
			enterInputValue(xAdress, adress, "Mail");
			enterInputValue(xTelephone, telephone, "Telephone");

		}
		catch(Exception e) {
			System.out.println("The exception for fillingTheForm function. " + e.getMessage());
		}
	}


	public void clickOnSubmit() {

		WebElement element;
		String submitNotification ="";
		
		try {

			element = driver.findElement(submit);
			waitForVisibility(submit);
			submitNotification  = element.getAttribute("name");
			element.click(); 
			System.out.println("Clicked on " + submitNotification +  " button");

		}catch(Exception e) {
			System.out.println("The exception for clickOnRadioButton function. " + e.getMessage());

		}
	}

	public  void printCostumerNum () {

		WebElement element;

		try {

			element = driver.findElement(costumerNum);
			System.out.println("The costumer number is: " + element.getText());


		}
		catch(Exception e) {
			System.out.println("The exception for printTheCostumerNum function. " + e.getMessage());
		}
	}


}
