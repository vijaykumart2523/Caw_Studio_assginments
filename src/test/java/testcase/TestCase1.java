package testcase;

import static org.testng.Assert.assertEquals;


import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.baseTest;
import utilities.excelData;

public class TestCase1 extends baseTest {

	@Test
	public static void TestPage() {
		System.out.print("Successfully open test page");
		
		driver.findElement(By.xpath(loc.getProperty("Table_Data"))).click();
		driver.findElement(By.xpath(loc.getProperty("input_text"))).clear();
		driver.findElement(By.xpath(loc.getProperty("input_text"))).sendKeys(loc.getProperty("input_data"));
		driver.findElement(By.xpath(loc.getProperty("refresh_button"))).click();
	}
	@Test
    public void testRow1() {
	   SoftAssert softassert = new SoftAssert();
	   
	   //Expected Response
    	String expectedName = "Bob";
    	String expectedAge =  "20";
    	
    	// Actual Response
    	String actualName = driver.findElement(By.xpath("//tr[2]/td[1]")).getText();
    	String actualAge = driver.findElement(By.xpath("//tr[2]/td[2]")).getText();
    	
    
    	softassert.assertEquals(actualName, expectedName,"Name verification");
    	softassert.assertEquals(actualAge, expectedAge,"Age verification");
    
    	softassert.assertAll();
    	       
    	    }
	@Test
	public void Verfying_2ndRow() {
		 SoftAssert softassert = new SoftAssert();
		 
		 // Expected Response
		String expected2ndRowName = "George";
    	String expected2ndRowAge =  "42";
    	
    	//Acutal Response
    	String actual2ndRowName = driver.findElement(By.xpath("//tr[3]/td[1]")).getText();
    	String actual2ndRowAge = driver.findElement(By.xpath("//tr[3]/td[2]")).getText();
    	
    	softassert.assertEquals(actual2ndRowName, expected2ndRowName,"Name verification");
    	softassert.assertEquals(actual2ndRowAge, expected2ndRowAge,"Age verification");
    	softassert.assertAll();
    	
	}
	@Test
	public void Verfying_3rdRow() {
		SoftAssert softassert = new SoftAssert();
		
		// Expcted Response
		String expected3rdRowName = "Sara";
    	String expected3rdRowAge =  "42";
    	
    	// Actual Response
    	String actual3rdRowName = driver.findElement(By.xpath("//tr[4]/td[1]")).getText();
    	String actual3rdRowAge = driver.findElement(By.xpath("//tr[4]/td[2]")).getText();
    	
    	softassert.assertEquals(actual3rdRowName, expected3rdRowName,"Name verification");
    	softassert.assertEquals(actual3rdRowAge, expected3rdRowAge,"Age verification");
    	
    	
    	
		softassert.assertAll();
	}
	
	@Test
	public void Verfying_4thRow() {
		SoftAssert softassert = new SoftAssert();
		
		// Expcted Response
		String expected4thRowName = "Conor";
    	String expected4thRowAge =  "40";
    	
    	// ActualResponse
    	String actual4thRowName = driver.findElement(By.xpath("//tr[5]/td[1]")).getText();
    	String actual4thRowAge = driver.findElement(By.xpath("//tr[5]/td[2]")).getText();
    	
    	softassert.assertEquals(actual4thRowName, expected4thRowName,"Name verification");
    	softassert.assertEquals(actual4thRowAge, expected4thRowAge,"Age verification");
    	
    	
		softassert.assertAll();
	}
	
	@Test
	public void Verfying_5thRow() {
		SoftAssert softassert = new SoftAssert();
	
		
		// ActualResponse
		
		String actual5thRowName = driver.findElement(By.xpath("//tr[6]/td[1]")).getText();
    	String actual5thRowAge = driver.findElement(By.xpath("//tr[6]/td[2]")).getText();
    	
    	// Expcted Response
		String expected5thRowName = "Jennifer";
    	String expected5thRowAge =  "42";
    	
    	softassert.assertEquals(actual5thRowName, expected5thRowName,"Name verification");
    	softassert.assertEquals(actual5thRowAge, expected5thRowAge,"Age verification");
    	
    	
    	
		softassert.assertAll();
	}
	

  @Test
   public void Verfying_Gender_In_Rows() {
	   SoftAssert softassert = new SoftAssert();
	   
	   //first row gender
	   String expected1st_Row = "male";
		  
	   Wait <WebDriver> Wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(3))
				.pollingEvery(Duration.ofSeconds(2))
				.withMessage("Element is presented");
				Wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"dynamictable\"]/tr[2]/td[3]"))).getText();
	String actual1stRow_Gender = driver.findElement(By.xpath("//*[@id=\"dynamictable\"]/tr[2]/td[3]")).getText();
	   System.out.print(actual1stRow_Gender);
	   softassert.assertEquals(actual1stRow_Gender, expected1st_Row,"Gender verification");
	   softassert.assertAll();
	   
	   // 2nd row Gender
	   String expected_2nd_Row_Gender = "male";
	  
	   Wait <WebDriver> Wait1 = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(3))
				.pollingEvery(Duration.ofSeconds(2))
				.withMessage("Element is presented");
				Wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[3]/td[3][text()='male']"))).getText();
	String actual_2nd_Gender_Row = driver.findElement(By.xpath("//tr[3]/td[3][text()='male']")).getText();
	   System.out.print(actual_2nd_Gender_Row);
	   softassert.assertEquals(actual_2nd_Gender_Row, expected_2nd_Row_Gender,"Gender verification");
	   softassert.assertAll();
	   
	// Third row Gender
	   
	   String expected_3Row_Gender = "female";
	   Wait <WebDriver> Wait2 = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(3))
				.pollingEvery(Duration.ofSeconds(2))
				.withMessage("Element is presented");
				Wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[4]/td[3][text()='female']"))).getText();
	String actual_3rdGender = driver.findElement(By.xpath("//tr[4]/td[3][text()='female']")).getText();
	   System.out.print(actual_3rdGender);
	   softassert.assertEquals(actual_3rdGender, expected_3Row_Gender,"Gender verification");
	   softassert.assertAll();
	   
	// 4th row Gender
	   String expected_4th_Row_Gender = "male";
	  
	   Wait <WebDriver> Wait3 = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(3))
				.pollingEvery(Duration.ofSeconds(2))
				.withMessage("Element is presented");
				Wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[5]/td[3][text()='male']"))).getText();
	String actual_4th_Row_Gender = driver.findElement(By.xpath("//tr[5]/td[3][text()='male']")).getText();
	   System.out.print(actual_4th_Row_Gender);
	   softassert.assertEquals(actual_4th_Row_Gender, expected_4th_Row_Gender,"Gender verification");
	   softassert.assertAll();
	   
	// 5th row Gender
	   String expected_5th_Row_Gender = "male";
	  
	   Wait <WebDriver> Wait4 = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(3))
				.pollingEvery(Duration.ofSeconds(2))
				.withMessage("Element is presented");
				Wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[6]/td[3][text()='male']"))).getText();
	String actual_5th_Row_Gender = driver.findElement(By.xpath("//tr[6]/td[3][text()='male']")).getText();
	   System.out.print(actual_5th_Row_Gender);
	   softassert.assertEquals(actual_5th_Row_Gender, expected_5th_Row_Gender,"Gender verification");
	   softassert.assertAll();
   }
    	
   
}   

    
	
	
	









	   

    

