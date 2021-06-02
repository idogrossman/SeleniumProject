package aaa.bbb.FinalProject.utils;

import org.openqa.selenium.WebDriver;

public class Reporter {

	static int picNum = 0;
	static String picPath = "D:\\img\\reporterImageSreenshot_" + picNum + ".png";

	public enum ReporterStatus {

		FAIL,
		PASS,
		WARNING,
		INFO,
		ERROR,
		TEST_EXCECUTION,
		TEST_PASSED,
		TEST_ENDED
	}

	public static void reportFailStep(String stepDescription) {

		reportStep(ReporterStatus.FAIL, stepDescription);
	}

	public static void reportPassStep(String stepDescription) {

		reportStep(ReporterStatus.PASS, stepDescription);
	}

	public static void reportStep(ReporterStatus stepStatus, int stepDescription) {

		reportStep(stepStatus, String.valueOf(stepDescription));
	}

	public static void reportStep(ReporterStatus stepStatus, String stepDescription) {

		System.out.println(stepStatus + ": " + stepDescription);
	}

	public static void reportError(String stepDescription) {

		reportStep(ReporterStatus.ERROR, stepDescription);
	}

	public static void reportError(WebDriver driver, String stepDescription) {

		reportStep(ReporterStatus.ERROR, stepDescription);
		picNum ++;
		ElementsActions.takeFullScreenshot(driver, picPath, "Taking screenshot of the error");
	}

	public static void reportStartTest(String stepDescription) {

		System.out.println("\n****************** TEST STARTED *********************");
		reportStep(ReporterStatus.TEST_EXCECUTION, stepDescription);
	}

	public static void reportEndTest() {

		reportStep(ReporterStatus.TEST_ENDED, "\n****************** TEST ENDED *********************");
	}

	public static void varificationComparation(String origValue, String valToVerify) {

		reportStep(ReporterStatus.INFO,"Verification: \nExpected: " + origValue + "\nActual: " + valToVerify);	
	}

	public static void varificationComparation(String [] origValue, String [] valToVerify) {

		reportStep(ReporterStatus.INFO,"Verification: \nExpected: " + origValue + "\nActual: " + valToVerify);	
	}
}
