package testcase;

import java.util.List;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import base.driver;

public class PracticeCaw extends driver {

	  @Test
	    public void testDynamicTable() {
	        if (this.testData == null) {
	            System.err.println("Test data is not loaded. Skipping test.");
	            return;
	        }
	        
	      

	        // Step 1: Click "Table Data" button
	        WebElement tableDataButton = driver.findElement(By.xpath("/html/body/div/div[3]/details/summary"));
	        tableDataButton.click();

	        // Step 2: Locate the input box and enter JSON data as a single string
	        WebElement inputBox = driver.findElement(By.id("jsondata"));
	        inputBox.clear();
	        inputBox.sendKeys(testData.toJSONString());

	        // Step 3: Click "Refresh Table" to populate the table with JSON data
	        WebElement refreshTableButton = driver.findElement(By.id("refreshtable"));
	        refreshTableButton.click();

	        // Step 4: Validate the populated data
	        List<WebElement> rows = driver.findElements(By.xpath("//tr"));
	        
	        // Adjusting row count to match the number of data entries
	        Assert.assertEquals(rows.size(), testData.size() + 1, "Row count mismatch!");  // +1 for the header

	        for (int i = 1; i < rows.size(); i++) { // Start from 1 to skip header
	            JSONObject expectedData = (JSONObject) testData.get(i - 1); // Get expected data
	            WebElement row = rows.get(i);
	            List<WebElement> columns = row.findElements(By.tagName("td"));

	            // Logging actual values for debugging
	            System.out.println("Row " + i + " values: ");
	            for (int j = 0; j < columns.size(); j++) {
	                System.out.println("Column " + j + ": " + columns.get(j).getText());
	            }

	            // Validate each cell against corresponding JSON data (updated order)
	            Assert.assertEquals(columns.get(0).getText(), expectedData.get("gender").toString(), "Gender mismatch!");  // Gender in column 0
	            Assert.assertEquals(columns.get(1).getText(), expectedData.get("name").toString(), "Name mismatch!");  // Name in column 1
	            Assert.assertEquals(columns.get(2).getText(), expectedData.get("age").toString(), "Age mismatch!");    // Age in column 2
	        }
	    }


	    @AfterClass
	    public void teardown() {
	        if (driver != null) {
	            driver.quit();
	        }
	    }
}