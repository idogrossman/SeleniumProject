package aaa.bbb.FinalProject.tests;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;

import org.openqa.selenium.WebDriver;

import aaa.bbb.FinalProject.utils.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import aaa.bbb.FinalProject.pages.*;


public class StoreTest extends BaseTest {

	@Test
	public void Store()  {

		//variable and objects
		
		String createAcountError = "An account using this email address has already been registered. Please enter a valid password or request a new one.";
		String signInUser = "Ido Grossman";
		String [] clothesInCart = new String[] {"Faded Short Sleeve T-shirts", "Blouse"};
		String itemList [];
		String paymentComplete = "Your order on My Store is complete.";
		String sendMessage = "Your message has been successfully sent to our team.";
		String signOutVerification = "Sign in";
		String result;
		By signIn = By.xpath("//a[@title ='View my customer account']//span");
		By pComplete = By.xpath("//p[@class='cheque-indent']//strong");
		By sMessage = By.xpath("//p[@class='alert alert-success']");
		By signOut = By.xpath("//a[@class='login']");
		WebStoreCommon webStoreCommon = new WebStoreCommon(driver);
		CostumerRegisterPage costumerRegisterPage = new CostumerRegisterPage(driver);
		BestSellerPage bestSellerPage = new BestSellerPage(driver);
		WomenPage womenPage = new WomenPage(driver);
		TableAndAddToCartPage tableAndAddToCartPage = new TableAndAddToCartPage(driver);
		GoToPaymentWindow goToPaymentWindow = new GoToPaymentWindow(driver);
		PaymentProcess paymentProcess = new PaymentProcess(driver);
		ContactUsPage contactUsPage = new ContactUsPage(driver);
		OurStorePage ourStorePage = new OurStorePage(driver);
		MyOrderPage myOrderPage = new MyOrderPage(driver);
		SearchPage searchPage = new SearchPage(driver);

		try {

			//test
			//Get an error when register with existing email
			Reporter.reportStartTest("Get an error with existing email.");
			webStoreCommon.selectFootMenu("Log in to your customer account");
			result = costumerRegisterPage.createAcount("ig@gmail.com");
			TestValidation.verifyRegistration(createAcountError, result, false);
			Reporter.reportEndTest();

			//Register with non register email
			Reporter.reportStartTest("Register with non register email.");
			webStoreCommon.selectFootMenu("Log in to your customer account");
			result = costumerRegisterPage.createAcount("igggg@gmail.com");
			TestValidation.verifyRegistration(createAcountError, result, true);
			Reporter.reportEndTest();
			
			//Full register for new acount
			Reporter.reportStartTest("Full register for new acount.");
			costumerRegisterPage.fillingTheForm("mr","ido","Grossman","iggg@gmail.com", "27", "May", "2020", "TEOCO" , "22 Herzel st.", "Hawaii", "11111", "United States", "0503526894" , "12 Palmach st.");
			Reporter.reportEndTest();
			//This test will not be verify, as register is not completed, for it can register every time. 

			//Login with existing user
			Reporter.reportStartTest("Login with existing user.");
			webStoreCommon.selectFootMenu("Log in to your customer account");
			costumerRegisterPage.signIn("ig@gmail.com" , "11111");
			String vSignIn = TestValidation.getVerificationValue(driver, signIn);
			TestValidation.verifyTest(signInUser, vSignIn, true);
			Reporter.reportEndTest(); 

			//Go to best seler and print item details of one item (Blouse)
			Reporter.reportStartTest("Go to best seler and print item details of one item.");
			webStoreCommon.selectFootMenu("Information;Best sellers");
			bestSellerPage.printBestSellerItems();
			String itemName = bestSellerPage.printItemDetails("Blouse");
			TestValidation.verifyTest("Blouse", itemName, true);
			Reporter.reportEndTest();

			//Go to women page -> select cotton -> choose item + its all parameter
			Reporter.reportStartTest("Go to women page -> select cotton -> choose item + all its parameters.");
			webStoreCommon.selectFootMenu("Categories;women");
			womenPage.selectParameter("Cotton");
			womenPage.addToCart("Faded","3", "S", "Blue");
			webStoreCommon.selectFootMenu("Categories;women");
			womenPage.addToCart("Blouse","2", "M", "Black");
			itemList = womenPage.getItemsList();
			boolean verifyItems =  TestValidation.verifyExpectedAndActualArray(clothesInCart,itemList);
			TestValidation.verifyTestArray(verifyItems, true);
			Reporter.reportEndTest();

			//Add the item to the cart and do all payment process
			Reporter.reportStartTest("Add the item to the cart and do all payment process.");
			paymentProcess.payment("Please wrap it as a present.", "Pay by bank wire");
			String vPayment = TestValidation.getVerificationValue(driver, pComplete);
			TestValidation.verifyTest(paymentComplete, vPayment, true);
			Reporter.reportEndTest();

			//Contact the site regarding one of the item in the order list
			Reporter.reportStartTest("Contact the site regarding one of the item in the order list.");
			webStoreCommon.selectFootMenu("Categories;Contact us");
			contactUsPage.ContactUs("Customer service", "ig@gmail.com", "GCTBHDCNJ - 04/26/2021", "About the item", "Faded Short Sleeve T-shirts - Color : Blue, Size : L");
			String vMessage = TestValidation.getVerificationValue(driver, sMessage);
			TestValidation.verifyTest(sendMessage, vMessage, true);
			Reporter.reportEndTest();

			//Go to store page and get store details
			Reporter.reportStartTest("Go to store page and get store details.");
			webStoreCommon.selectFootMenu("Categories;Our stores");
			ourStorePage.selectStore();
			ourStorePage.printTheStoreDetail();
			Reporter.reportEndTest();
			//The verification will be using screenshot of page, as a random store is selected every time.

			//Go to women and print item picture
			Reporter.reportStartTest("Go to women and print item picture.");
			webStoreCommon.selectFootMenu("Categories;women");
			ElementsActions.takeItemScreenshot(driver, "D:\\img\\dressScreenshot.png", "Faded", "Faded item is choosen.");
			Reporter.reportEndTest();
			//A test of screenshot, no need for verification

			//ReOrder one of the privious purchases
			Reporter.reportStartTest("ReOrder one of the privious purchases.");
			webStoreCommon.selectFootMenu("My account;My orders");
			myOrderPage.reOrderPurchase(9);
			paymentProcess.payment("Please wrap it as a present.", "Pay by bank wire");
			vPayment = TestValidation.getVerificationValue(driver, pComplete);
			TestValidation.verifyTest(paymentComplete, vPayment, true);
			Reporter.reportEndTest();

			//Signout
			Reporter.reportStartTest("Sighout test.");
			webStoreCommon.sighout();
			String vSignOut = TestValidation.getVerificationValue(driver, signOut);
			TestValidation.verifyTest(signOutVerification, vSignOut, true);
			Reporter.reportEndTest();
		 
		}catch (Exception e) {
			Reporter.reportError("The exception for Main test function. "  + e.getMessage());

		}
	}

	@Test(dataProvider = "searchAndCompare")

	public void searchAndCompare (String item, String path, String notification) {

		//Test is search for all the define items and compare between them and take a screenshoot of the compare.

		Reporter.reportStartTest("Search for " + item  + " items and compare between them and take a screenshoot of the compare.");
		WebStoreCommon webStoreCommon = new WebStoreCommon(driver);
		SearchPage searchPage = new SearchPage(driver);
		String itemList [];

		try {
			webStoreCommon.globalSearch(item);
			searchPage.addToCompareList();
			Thread.sleep(500);
			ElementsActions.takeFullScreenshot(driver, path, notification);
			Reporter.reportEndTest();
		}catch (Exception e) {
			Reporter.reportError("The exception for searchCompare test function. "  + e.getMessage());
		}
	}

	@Test (groups = "XML")
	//Running with XML with childeren
	//Go to women page, choose some items and add the items to the cart.
	public void addToCart()  {
		
		String itemList [];
		WomenPage womenPage = new WomenPage(driver);
		String [] clothesInCart = new String[] {"Faded Short Sleeve T-shirts", "Blouse"};

		try {
			String [][] tests = ActionOnXMLFile.getXmlListChildren("StoreData2.xml", "//test");
			ActionOnXMLFile.runOnArrayChildren(tests, driver);
			itemList = womenPage.getItemsList();
			Thread.sleep(500);
			boolean verifyItems =  TestValidation.verifyExpectedAndActualArray(clothesInCart,itemList);
			TestValidation.verifyTestArray(verifyItems, true);

		}catch (InterruptedException e) {
			Reporter.reportError("The exception for addToCart test function. "  + e.getMessage());
		}
	}

	@Test (groups = "XML")
	//Running with XML with attribute
	//Add some items to wish list and take screenshot of the wishlist
	public void wishList()  {
		WebStoreCommon webStoreCommon = new WebStoreCommon(driver);
		CostumerRegisterPage costumerRegisterPage = new CostumerRegisterPage(driver);
		WomenPage womenPage = new WomenPage(driver);

		try {
			webStoreCommon.selectFootMenu("Log in to your customer account");
			costumerRegisterPage.signIn("ig@gmail.com" , "11111");

			String [][] tests = ActionOnXMLFile.getXmlList("StoreData1.xml", "//test");
			ActionOnXMLFile.runOnArrayAttribute(tests, driver);
			Thread.sleep(500);

			womenPage.NavigateTowishListPage(driver);
			ElementsActions.takeFullScreenshot(driver, "D:\\img\\wishlistScreenshot.png", "Taking screenshot of wishlist page");
		}catch (InterruptedException e) {
			Reporter.reportError("The exception for wishList test function. "  + e.getMessage());
		}
	}

	@DataProvider
	public Object[][] searchAndCompare() {
		return new Object[][] {
			new Object[] { "yellow", "D:\\img\\FirstCycle.png", "Printing item to compare - First cycle"},
			new Object[] { "blue", "D:\\img\\SecondCycle.png", "Printing item to compare - Second cycle"},
			new Object[] { "black", "D:\\img\\ThirdCycle.png", "Printing item to compare - Third cycle"},
		};
	}

}




