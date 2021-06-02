package aaa.bbb.FinalProject.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;
import net.bytebuddy.asm.Advice.Origin;

public class TestValidation {

	public static boolean verifyElementIsClickable(WebDriver driver, By by, String name) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(by));
			System.out.println("Element: " + name + " is clickable");
			return true;
		} catch (Exception e) {
			Reporter.reportError(driver, "The exception for verifyElementIsClickable function. "  + e.getMessage());
			return false;
		}

	}

	public static void verifyRegistration(String origValue, String valToVerify, boolean expectedResult) {

		try {
			//The error string are being compared - Negative test
			//The value are equal and the expected result is true.

			Reporter.varificationComparation(origValue, valToVerify);
			if (valToVerify.equalsIgnoreCase(origValue) && expectedResult == false)
				Reporter.reportPassStep("The test failed as expected.");
			else if (valToVerify != origValue && expectedResult == true)
				Reporter.reportPassStep("The test succedded as expected.");
			else 
				Reporter.reportFailStep("The test has been failed.");
		}
		catch (Exception e) {
			Reporter.reportError("The exception for verifyRegistration function. "  + e.getMessage());
		}
	}

	public static void verifyTest(String origValue, String valToVerify, boolean expectedResult) {

		//Positive test
		//The value are equal and the expected result is true.
		try {
			Reporter.varificationComparation(origValue, valToVerify);
			//The error string are being compared
			if (valToVerify.equalsIgnoreCase(origValue) && expectedResult == true)
				Reporter.reportPassStep("The test succedded as expected.");
			else if (valToVerify != origValue && expectedResult == false)
				Reporter.reportPassStep("The test succedded as expected.");
			else 
				Reporter.reportFailStep("The test has been failed.");
		}

		catch (Exception e) {
			Reporter.reportError("The exception for verifyTest function. "  + e.getMessage());
		}
	}


	public static void verifyTestArray(boolean compare, boolean expectedResult) {

		//Positive test
		//The value are equal and the expected result is true.
		try {
			//The error string are being compared
			if (compare == expectedResult)
				Reporter.reportPassStep("The test succedded as expected.");
			else 
				Reporter.reportFailStep("The test has been failed.");
		}

		catch (Exception e) {
			Reporter.reportError("The exception for verifyTestArray function. "  + e.getMessage());
		}
	}

	public static boolean verifyExpectedAndActualArray(String [] origValue, String [] valToVerify) {

		boolean compare = false;
		try {
			for (int i=0; i < origValue.length  ; i++) {
				if (origValue[i].equalsIgnoreCase(valToVerify[i]))
					compare = true;
				else
					compare = false;
			}
			printExpectedAndActual("Expected", origValue);
			printExpectedAndActual("Actual", valToVerify);
		}
		catch (Exception e) {
			Reporter.reportError("The exception for verifyExpectedAndActualArray function. "  + e.getMessage());
			return false;
		}
		return compare;
	}


	public static void printExpectedAndActual(String name, String [] arrayToPrint) {

		try {
			System.out.println(name + ":");
			for (int i=0; i < arrayToPrint.length ; i++) {
				System.out.println(i+1 + " item is: " + arrayToPrint[i]);
			}
		}
		catch (Exception e) {
			Reporter.reportError("The exception for verifyExpectedAndActualArray function. "  + e.getMessage());
		}
	}

	public static String getVerificationValue(WebDriver driver, By by) {

		String varificationValue;
		try {
			WebElement element = driver.findElement(by);
			varificationValue = element.getText();
			varificationValue = varificationValue.replace("[\\n]", "").trim();
		}

		catch (Exception e) {
			Reporter.reportError("The exception for getVerificationValue function. "  + e.getMessage());
			return null;
		}
		return varificationValue;
	}
}
