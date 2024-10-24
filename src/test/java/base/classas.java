package base;


	
	import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
	
	public class classas {
	   @Test
	    	public void test1() {
	    	WebDriverManager.chromedriver().setup();
	    	ChromeDriver driver = new ChromeDriver();
	    	List<Map<String,Object>> tableData = new ArrayList<>();
	    	tableData.add(CreateData("Bob",20, "male"));
	    	tableData.add(CreateData("George",42, "male"));
	    	tableData.add(CreateData("Sara",42, "female"));
	    	tableData.add(CreateData("Conor",40, "male"));
	    	tableData.add(CreateData("Jennifer",42, "female"));
	    	
	    	try {
	    		driver.get("https://testpages.herokuapp.com/stled/tag/dynamic-table.html");
	    		WebElement tableDataButton = driver.findElement(By.xpath("html/body/div/div[3]/details/summary"));
	    		tableDataButton.click();
	    		WebElement inputBox = driver.findElement(By.id("jsondata"));
	    		
	    		String inputData = formatDataAsJsonString(tableData);
	    		inputBox.clear();
	    		inputBox.sendKeys(inputData);
	    		WebElement refreshButton = driver.findElement(By.id("re-freshtable"));
	    		refreshButton.click();
	    	//	new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.id("dynamictable")));
	    		
	    		WebElement table = driver.findElement(By.xpath("/html/body/div/div[3]/details/summary/text()"));
	    		List<WebElement> rows = table.findElements(By.tagName("tr"));
	    		List<Map<String, Object>> retrievedData = new ArrayList<>();
	    		for(int i = 1; i <rows.size(); i++) {
	    			List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));
	    			String name = cells.get(0).getText();
	    			int age = Integer.parseInt(cells.get(1).getText());
	    			String gender = cells.get(1).getText();
	    			retrievedData.add(CreateData(name, age, gender));
	    		}
	    		assert retrievedData.equals(tableData): "Data mismatch!";
	    			
	    		System.out.println("Test Passed: Data matches the input and table data.");
	    	}finally {
	    		driver.quit();
	    			
	    		}
	    	}
	    	


		private static Map<String, Object> CreateData(String name, int age, String gender) {
			Map<String, Object> data = new HashMap<>();
			data.put("name", name);
			data.put("age", age);
			data.put("gender", gender);
			return data;
			
		}

		private static String formatDataAsJsonString(List<Map<String, Object>>tableData) {
			StringBuilder json = new StringBuilder("[");
			for(Map<String, Object> entry : tableData) {
				json.append("{\"name\":\"").append(entry.get("name")).append("\",\"age\":").append(entry.get("age")).append(",\"gender\":\"").append(entry.get("gender")).append("\"},");
				
			}
			json.deleteCharAt(json.length() - 1);
			json.append("]");
			
			return json.toString();
		}
	    
}
