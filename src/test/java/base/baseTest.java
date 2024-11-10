package base;

import java.io.FileReader;

import java.io.IOException;
import java.util.Properties;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;

public class baseTest {

    protected JSONArray testData;

    @BeforeClass
    public void loadTestData() {
        try {
            FileReader reader = new FileReader("C:\\eclipse-workspace\\Caw_Studio_Assignment\\src\\test\\resources\\testdata\\data.json");
            JSONParser parser = new JSONParser();
            this.testData = (JSONArray) parser.parse(reader);
        } catch (Exception e) {
            System.err.println("Error loading test data: " + e.getMessage());
            
        }
        
    }
}
