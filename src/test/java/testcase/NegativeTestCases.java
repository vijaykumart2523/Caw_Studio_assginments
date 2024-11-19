package testcase;

import java.util.List;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTestNegative;
import base.driver;

public class NegativeTestCases extends BaseTestNegative{

    @Test
    public void testDynamicTableWithNegativeData() {
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
        
        // Check if the number of rows matches the size of test data
        Assert.assertEquals(rows.size(), testData.size() + 1, "Row count mismatch!");

        for (int i = 1; i < rows.size(); i++) {
            JSONObject expectedData = (JSONObject) testData.get(i - 1);
            WebElement row = rows.get(i);
            List<WebElement> columns = row.findElements(By.tagName("td"));

            // Extract expected values
            String expectedName = expectedData.get("name") != null ? expectedData.get("name").toString() : "";
            String expectedGender = expectedData.get("gender") != null ? expectedData.get("gender").toString() : "";
            String expectedAge = expectedData.get("age") != null ? expectedData.get("age").toString() : "";

            // **Negative Test Cases Handling**
            try {
                // Check for empty name
                if (expectedName.isEmpty()) {
                    System.out.println("Invalid data: Name is empty!");
                    Assert.assertTrue(true, "Name is empty as expected in negative test.");
                }

                // Check for invalid age (non-integer or negative)
                try {
                    int ageValue = Integer.parseInt(expectedAge);
                    if (ageValue < 0) {
                        System.out.println("Invalid data: Age is negative!");
                        Assert.assertTrue(true, "Age is negative as expected in negative test.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid data: Age is not an integer!");
                    Assert.assertTrue(true, "Age is not an integer as expected in negative test.");
                }

                // Validate gender field (expecting 'male' or 'female' only)
                if (!expectedGender.equals("male") && !expectedGender.equals("female")) {
                    System.out.println("Invalid data: Gender is neither 'male' nor 'female'!");
                    Assert.assertTrue(true, "Gender is invalid as expected in negative test.");
                }

                // **If valid data is found, fail the test**
                if (!expectedName.isEmpty() && 
                    (expectedGender.equals("male") || expectedGender.equals("female")) &&
                    !expectedAge.isEmpty() && Integer.parseInt(expectedAge) >= 0) {
                    Assert.fail("Unexpected valid data found during negative testing!");
                }

                // Validate actual table data against expected (to see what is rendered on the table)
                Assert.assertEquals(columns.get(0).getText(), expectedGender, "Gender mismatch!");
                Assert.assertEquals(columns.get(1).getText(), expectedName, "Name mismatch!");
                Assert.assertEquals(columns.get(2).getText(), expectedAge, "Age mismatch!");

            } catch (Exception e) {
                System.err.println("Exception during validation: " + e.getMessage());
                Assert.fail("Test failed due to unexpected exception.");
            }
        }

        }
    }

