package com.qa.tech;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ECSDigital {

	private String baseUrl;

	private static int returnArrayIndex(Integer[] arrayNum) {

		int indexOfarr = 0;
		int sumLeft = 0;
		int sumRight = 0;

		for (int i = 1; i < arrayNum.length; i++) {

			int j = i;

			while (j > 0) {
				sumLeft = sumLeft + arrayNum[j - 1];
				j--;
			}
			j = i;

			while (j < arrayNum.length - 1) {
				sumRight = sumRight + arrayNum[j + 1];
				j++;
			}

			if (sumLeft == sumRight) {
				indexOfarr = i;
			}

			sumLeft = 0;
			sumRight = 0;
		}
		return indexOfarr;
	}

	@Test
	public void ECSDigitalTest() {
		
		//set chrome driver to perform test
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/chromedriver");
		WebDriver driver = new ChromeDriver();
		baseUrl = "localhost:3000";
		
		//Navigate to ECSTest
		driver.navigate().to(baseUrl);
		
		//click the button to Render the problem
		driver.findElement(By.xpath("//*[@id=\"home\"]/div/div/button/div/div/span")).click();

		WebElement table_or_tbody = driver
				.findElement(By.xpath("//*[@id=\"challenge\"]/div/div/div[1]/div/div[2]/table/tbody"));

		// find all table rows
		List<WebElement> rows = table_or_tbody.findElements(By.cssSelector("tr"));

		List<Integer[]> table_data = new ArrayList<Integer[]>();

		for (WebElement row : rows) {

			// find all cells of each table row
			List<WebElement> cells = row.findElements(By.cssSelector("td"));
			Integer[] num = new Integer[0];

			// read number of each cell
			for (WebElement cell : cells) {
				num = (Integer[]) (ArrayUtils.add(num, Integer.parseInt(cell.getText().trim())));
			}

			table_data.add(num);
		}

		Integer[] a = table_data.get(0);
		Integer[] b = table_data.get(1);
		Integer[] c = table_data.get(2);

		System.out.println("Index of first row of table" + returnArrayIndex(a));
		System.out.println("Index of second row of table" + returnArrayIndex(b));
		System.out.println("Index of third row of table" + returnArrayIndex(c));

		driver.quit();
	}
}
