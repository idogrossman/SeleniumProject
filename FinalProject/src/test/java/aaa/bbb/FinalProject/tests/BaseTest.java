package aaa.bbb.FinalProject.tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import aaa.bbb.FinalProject.utils.*;

public class BaseTest {

	public WebDriver driver;

	@BeforeMethod(alwaysRun = true)
	public void beforeMethod() {

		try {
			Properties prop = new Properties();
			InputStream input = new FileInputStream("D:\\SeleniumProject\\FinalProject\\src\\test\\java\\aaa\\bbb\\FinalProject\\tests\\Environment.properties");
			prop.load(input);
			String browser = prop.getProperty("browser");
			String url = prop.getProperty("url");

			if (browser.equalsIgnoreCase("chrome"))
			{
				System.setProperty("webdriver.chrome.driver", "D:\\Eclipse\\Resource\\chromedriver.exe");
				driver = new ChromeDriver();
				driver.manage().window().maximize();
				driver.manage().deleteAllCookies();
				driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				driver.get(url);
			}

			if (browser.equalsIgnoreCase("firefox"))
			{
				System.setProperty("webdriver.gecko.driver","D:\\Eclipse\\Resource\\geckodriver.exe");
				driver = new FirefoxDriver(); //Creating an object of FirefoxDriver
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				driver.get(url);
			}

		}catch(Exception e) {
			Reporter.reportError(driver, "The exception for beforeMethod function. "  + e.getMessage());
		}
	}

		@AfterMethod(alwaysRun = true)
		public void afterMethod() {

			driver.quit();
		}

		@BeforeClass(alwaysRun = true)
		public void beforeClass() {

		}

		@AfterClass(alwaysRun = true)
		public void afterClass() {

		}
	}
