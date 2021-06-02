package aaa.bbb.FinalProject.pages;

import java.util.List;

import javax.swing.Spring;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import aaa.bbb.FinalProject.utils.*;
import aaa.bbb.FinalProject.utils.Reporter.ReporterStatus;


public class CostumerRegisterPage extends WebStoreCommon{

	By registerEmail = By.xpath("//input[@name='email_create']");
	By submit = By.id("SubmitCreate");
	By error = By.id("create_account_error");
	By mr = By.id("id_gender1");
	By mrs = By.id("id_gender2");
	By xFname = By.id("customer_firstname");
	By xLname = By.id("customer_lastname");
	By xEmail = By.id("email");
	By xPW = By.id("passwd");
	By xDays = By.id("days"); 
	By xMonths = By.id("months");
	By xYears = By.id("years");
	By xNewsletter = By.xpath("//input[@type='checkbox' and @id='newsletter']");
	By xOptin = By.id("optin");
	By xCompany = By.id("company");
	By xAddress  = By.id("address1");
	By xCity = By.id("city");
	By xState = By.id("id_state");
	By xPostcode = By.id("postcode");
	By xCountry = By.id("id_country");
	By xMobile = By.id("phone_mobile");
	By xAliasaAddress = By.id("alias"); 
	By xRegister = By.id("submitAccount");
	By xEntranceMail = By.xpath("//input[@name='email']");
	By xSubmitLogin = By.id("SubmitLogin");
	By yourAddress = By.xpath("//h3[.='Your address']");
	By ErrorNotification = By.xpath("//div[@id='create_account_error']//li");

	public CostumerRegisterPage(WebDriver driver) {
		super(driver);
	}

	//Entrace with existing mail
	public void signIn (String email, String pw) {
		try {

			ElementsActions.waitForVisibility(driver, xSubmitLogin);
			ElementsActions.enterInputValue(driver, xEntranceMail, email, "Email");
			ElementsActions.enterInputValue(driver, xPW, pw, "Password");
			ElementsActions.click(driver, xSubmitLogin, "Submit on login.");

		}catch(Exception e) {
			Reporter.reportError(driver, "The exception for firstRegister function "  + e.getMessage());
		}
	}

	//Register with new mail
	public String createAcount (String email) {
		String result;

		try {

			ElementsActions.waitForVisibility(driver, submit);
			ElementsActions.enterInputValue(driver, registerEmail, email, "Email");
			ElementsActions.click(driver, submit, "Clicking on submit button.");
			result = createAcountErrorCheck();

		}catch(Exception e) {
			Reporter.reportError(driver,"The exception for register. "  + e.getMessage());
			return null;
		}
		return result;
	}

	public String createAcountErrorCheck () {

		String createAcountErrorOnSite;
		try {

			WebElement element = driver.findElement(error);
			Thread.sleep(1000);

			boolean isDisplay =  ElementsActions.isDisplay(driver, element, "is display");
			if (isDisplay == true)
			{
				System.out.println("Please see site notification.");
				Thread.sleep(500);
				ElementsActions.takeItemScreenshot(driver, "D:\\img\\registrationError.png", error, "Error while registration.");
				WebElement acountError = driver.findElement(ErrorNotification);
				createAcountErrorOnSite = acountError.getText();
			}
			else {
				System.out.println("Please register");
				return ("New email. Please register");
			}
		}catch(Exception e) {
			System.out.println("Please register");
			return ("New email. Please register");
		}
		return createAcountErrorOnSite;
	}


	public void fillingTheForm (String gender, String fname, String lname, String password, String days, String mounth, String year, String company, String address, String city, String postcode, String country, String mobile, String aliasAddress) {

		WebElement element;

		try {
			//YOUR PERSONAL INFORMATION
			ElementsActions.waitForVisibility(driver, yourAddress);
			selectGender("mr");

			ElementsActions.enterInputValue(driver, xFname, fname, "First Name");
			ElementsActions.enterInputValue(driver, xLname, lname, "Last Name");
			element = driver.findElement(xEmail);
			Reporter.reportStep(ReporterStatus.INFO, "The email is: " + element.getAttribute("value"));
			//Checking character length
			if (password.length() < 5)
				Reporter.reportError(driver, "The password should be 5 characters at list.");
			else
				ElementsActions.enterInputValue(driver, xPW, password, "Password");

			//Date of birth

			ElementsActions.selectItemFromList(driver, xDays, days + "  ");
			ElementsActions.selectItemFromList(driver, xMonths, mounth + " ");
			ElementsActions.selectItemFromList(driver, xYears, year + "  ");
			//mark the checkbox
			ElementsActions.click(driver, xNewsletter, "Click on news letter.");
			ElementsActions.click(driver, xOptin, "Click on news option.");

			//YOUR ADDRESS
			ElementsActions.enterInputValue(driver, xFname, fname, "First Name");
			ElementsActions.enterInputValue(driver, xLname, lname, "Last Name");
			ElementsActions.enterInputValue(driver, xCompany, company, "Company");
			ElementsActions.enterInputValue(driver, xAddress, address, "Address");
			ElementsActions.enterInputValue(driver, xCity, city, "City");
			ElementsActions.selectItemFromList(driver, xState, city);
			ElementsActions.enterInputValue(driver, xPostcode, postcode, "Postcode");
			ElementsActions.selectItemFromList(driver, xCountry, country);
			ElementsActions.enterInputValue(driver, xMobile, mobile, "Mobile");
			ElementsActions.enterInputValue(driver, xAliasaAddress, aliasAddress, "Alias Address");

			// will not click to submit the form, for not register, when register, the email can't be use again
			//There for the next clicking action is marked as unavailable!!!

			//ElementsActions.click(driver, xRegister, "Submint account registration.");
		}
		catch(Exception e) {
			Reporter.reportError(driver, "The exception for fillingTheForm function "  + e.getMessage());
		}
	}

	public void selectGender(String genderType) {

		try {

			if (genderType.equalsIgnoreCase("mr")) 

				ElementsActions.click(driver, mr, "Click on 'Mr'.");
			else if (genderType.equalsIgnoreCase("mrs"))
				ElementsActions.click(driver, mr, "Click on 'Mrs'.");
			else
				Reporter.reportError(driver, "The gender type should be Mr or Mrs.");
		}catch (Exception e) {
			Reporter.reportError(driver ,"The exception for selectGender function. "  + e.getMessage());
		}
	}

	public void clickOnSubmit() {

		WebElement element;
		String submitNotification ="";

		try {

			element = driver.findElement(submit);
			ElementsActions.waitForVisibility(driver, submit);
			submitNotification  = element.getAttribute("name");
			ElementsActions.click(driver, element, "Clicking on submitNotification button");

		}catch(Exception e) {
			Reporter.reportError(driver, "The exception for clickOnRadioButton function. "  + e.getMessage());
		}
	}
}

