package base;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTestNegative {
    public static WebDriver driver;
    public static Properties pro = new Properties();
    public static Properties loc = new Properties();
    public static Properties testDataPro = new Properties();
    public static FileReader fr;
    public static FileReader fr1;
    public static FileReader testDataReader;
    protected JSONArray testData;

    @BeforeMethod
    public void Setup() throws IOException {
        if (driver == null) {
            fr = new FileReader(System.getProperty("user.dir") + "\\src\\test\\resources\\configfiles\\cofig.properties");
            fr1 = new FileReader(System.getProperty("user.dir") + "\\src\\test\\resources\\configfiles\\locators.properties");
            pro.load(fr);
            loc.load(fr1);
        }
        String browser = pro.getProperty("browser").toLowerCase();
        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        driver.get(pro.getProperty("testurl"));
    }

    @BeforeClass
    public void loadTestData() {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\testdata\\NegativeTestData.json";
        System.out.println("Loading test data from: " + filePath);

        // Step 1: Verify if the file exists
        try {
            File file = new File(filePath);
            System.out.println("File exists: " + file.exists());

            // Step 2: Print the file content for debugging
            if (!file.exists()) {
                throw new FileNotFoundException("Test data file not found at path: " + filePath);
            }

            System.out.println("Reading file content:");
            try (FileReader reader = new FileReader(filePath)) {
                int i;
                while ((i = reader.read()) != -1) {
                    System.out.print((char) i);
                }
            }

            System.out.println("\nFinished reading file content.");

            // Step 3: Parse the JSON data
            JSONParser parser = new JSONParser();
            FileReader jsonReader = new FileReader(filePath);
            this.testData = (JSONArray) parser.parse(jsonReader);

            // Step 4: Verify if data is loaded
            if (this.testData == null || this.testData.isEmpty()) {
                throw new Exception("Test data is empty or null after parsing.");
            }

            System.out.println("Test data loaded successfully: " + this.testData.toJSONString());

        } catch (ClassCastException e) {
            System.err.println("JSON structure is not an array: " + e.getMessage());
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            System.err.println("JSON file not found: " + e.getMessage());
            e.printStackTrace();
        } catch (org.json.simple.parser.ParseException e) {
            System.err.println("Error parsing JSON test data: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("I/O error while reading the test data file: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Unexpected error while loading test data: " + e.getMessage());
            e.printStackTrace();
        }
    }



    public void takeScreenshot(String fileName) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File(System.getProperty("user.dir") + "\\screenshots\\" + fileName + ".png"));
            System.out.println("Screenshot taken: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterMethod
    public void captureScreenshotOnFailure(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            takeScreenshot(result.getName());
        }
        tearDown();
    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Teardown successful");
        }
    }
}
