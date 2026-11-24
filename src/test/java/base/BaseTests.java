package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import utilities.DataDriven;

import java.util.Map;

public abstract class BaseTests {
    protected WebDriver driver;
    protected static final String BaseURL="https://www.saucedemo.com";
    protected Map<String,Object> loginData;
    protected Map<String,Object> validLogin;
    @BeforeClass
    public void loadLoginData() {
        loginData = DataDriven.readJson("testData/loginData.json");
        validLogin = (Map<String, Object>) loginData.get("validLogin");

    }
    @BeforeMethod
    public void setup(){
        driver= new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(BaseURL);
    }
    @AfterMethod
    public void tearDown() {
        if (driver !=null) {
            driver.quit();
        }
        driver =null;

    }

}
