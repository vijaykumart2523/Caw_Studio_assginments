package testcase;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.baseTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Test_Case2 extends baseTest {


	@Test
public  void Validating_Table() {
		 // Set up WebDriver for Chrome
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();

        // Navigate to the web page
        driver.get("https://testpages.herokuapp.com/styled/tag/dynamic-table.html");

        
        // Edit the JSON data and refresh the table
        driver.findElement(By.xpath("/html/body/div/div[3]/details/summary")).click();
        driver.findElement(By.xpath("//*[@id=\"jsondata\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"jsondata\"]")).sendKeys(
            "[{\"name\" : \"Bob\", \"age\" : 20, \"gender\": \"male\"}, {\"name\": \"George\", \"age\" : 42, \"gender\": \"male\"}, "
            + "{\"name\": \"Sara\", \"age\" : 42, \"gender\": \"female\"}, {\"name\": \"Conor\", \"age\" : 40, \"gender\": \"male\"}, "
            + "{\"name\": \"Jennifer\", \"age\" : 42, \"gender\": \"female\"}]"
        );
        driver.findElement(By.xpath("//button[@id=\"refreshtable\"]")).click();
        System.out.println("Is the refresh button enabled: " + driver.findElement(By.xpath("//button[@id=\"refreshtable\"]")).isEnabled());

		        // Wait for the table to be visible
		        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dynamictable")));

		        // Locate the table
		        WebElement table = driver.findElement(By.id("dynamictable"));

		        // Define the expected data
		        String[][] expectedData = {
		            {"Bob", "20", "male"},
		            {"George", "42", "male"},
		            {"Sara", "42", "female"},
		            {"Conor", "40", "male"},
		            {"Jennifer", "42", "female"}
		        };

		        // Locate all rows in the table (excluding the header row)
		        List<WebElement> allRows = table.findElements(By.xpath(".//tr[td]")); // Only rows with <td> to skip headers

		        // Assert that the number of rows matches the expected data length
		        Assert.assertEquals(allRows.size(), expectedData.length, "Row count does not match the expected data.");

		        // Iterate through each row and validate the data
		        for (int i = 0; i < allRows.size(); i++) {
		            // Get all the cells (td) in the current row
		            List<WebElement> cells = allRows.get(i).findElements(By.tagName("td"));

		            // Assert that the number of cells matches the expected column count (name, age, gender)
		            Assert.assertEquals(cells.size(), expectedData[i].length, "Column count does not match in row " + (i + 1));

		            // Validate each cell's data
		            for (int j = 0; j < cells.size(); j++) {
		                String actualText = cells.get(j).getText();
		                String expectedText = expectedData[i][j];
		                Assert.assertEquals(actualText, expectedText, "Mismatch at row " + (i + 1) + ", column " + (j + 1));
		                System.out.println("Row " + (i + 1) + " Column " + (j + 1) + ": " + actualText + " (Expected: " + expectedText + ")");
		            }
		        }

		        // Close the browser after completion
		        driver.quit();
		    }
		

				        
	
	}