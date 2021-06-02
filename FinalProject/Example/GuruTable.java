package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GuruTable extends GuruCommon {

	// Elements
	By table = By.xpath("//table");

	public GuruTable(WebDriver driver) {

		super(driver);

	}

	public int getTableCellsCount() {

		WebElement tableElement = driver.findElement(table);
		List<WebElement> cells = tableElement.findElements(By.tagName("td"));
		return cells.size();

	}

	public int getTableCellsCountByValue(By by,String value) {

		int counter = 0;


		try {

			List<WebElement> tableCell  = driver.findElements(by);
			for (int i=0; i < tableCell.size()  ; i++) 
				if (tableCell.get(i).getText().trim().equalsIgnoreCase(value))
					counter++;	
		}catch(Exception e) {
			System.out.println("The exception for tableCell function. " + e.getMessage());
		}





		/*WebElement tableElement = driver.findElement(table);
		List<WebElement> cells = tableElement.findElements(By.xpath(".//td[.='" + value + "']"));
		counter = cells.size();*/

		//List<WebElement> cells = tableElement.findElements(By.tagName("td"));
		//for(WebElement e : cells) {
		//	if(e.getText().trim().equals(value)) {
		//		counter++;
		//	}
		//}
		return counter;

	}

}
