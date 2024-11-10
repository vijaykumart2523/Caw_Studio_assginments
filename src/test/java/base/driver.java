package base;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.json.simple.JSONArray;

import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;



import io.github.bonigarcia.wdm.WebDriverManager;

public class driver {

	
		public static WebDriver driver;
		public static Properties pro = new Properties();
		public static Properties loc = new Properties();
		public static Properties testDataPro = new Properties();
		public static FileReader fr;
		public static FileReader fr1;
		public static FileReader testDataReader;
		@BeforeMethod
		public void Setup() throws IOException {
			if(driver==null) {
				 fr = new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\configfiles\\cofig.properties");
				 fr1 = new FileReader(System.getProperty("user.dir") +"\\src\\test\\resources\\configFiles\\locators.properties");
				pro.load(fr);
				loc.load(fr1);
				}
			if(pro.getProperty("browser").equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				driver.get(pro.getProperty("testurl"));
			}
			if(pro.getProperty("browser").equalsIgnoreCase("edge")) {
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
				driver.get(pro.getProperty("testurl"));
			}
			if(pro.getProperty("browser").equalsIgnoreCase("Firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				driver.get(pro.getProperty("testurl"));
			}
			
		}
		
		  protected JSONArray testData;

		    @BeforeClass
		    public void loadTestData() {
		        try {
		            FileReader reader = new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\testdata\\data.json");
		            JSONParser parser = new JSONParser();
		            this.testData = (JSONArray) parser.parse(reader);
		        } catch (Exception e) {
		            System.err.println("Error loading test data: " + e.getMessage());
		            
		        }
		        
		    }
	
		@AfterMethod()
		public void tearDown() {
			driver.close();
			System.out.println("Teardown succesful");
		}
		

	}

