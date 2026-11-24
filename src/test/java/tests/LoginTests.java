package tests;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import utilities.DataDriven;

import java.util.Map;

public class LoginTests extends BaseTests {

    private Map<String,Object> invalidLogin;
    private Map<String,Object> emptyPassLogin;
    private Map<String,Object> wrongPassAndUserLogin;
    private LoginPage loginPage;
    @BeforeClass
    public void loadLoginData(){
        loginData= DataDriven.readJson("testData/loginData.json");
        validLogin = (Map<String,Object>) loginData.get("validLogin");
        invalidLogin= (Map<String, Object>) loginData.get("invalidLogin");
        emptyPassLogin=(Map<String, Object>) loginData.get("emptyPasswordLogin");
        wrongPassAndUserLogin=(Map<String, Object>) loginData.get("wrongUserAndPass");

    }
    @BeforeMethod
            public void setupLoginPage(){
        loginPage = new LoginPage(driver);
    }

    @Test
    public void validLoginTest() {


        loginPage.enterUsername((String) validLogin.get("username"));
        loginPage.enterPassword((String) validLogin.get("password"));
        loginPage.clickLoginButton();

        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"),
                "Login was not successful");
    }

    @Test
    public void invalidLoginTest() {
        loginPage.enterUsername((String) invalidLogin.get("username"));
        loginPage.enterPassword((String) invalidLogin.get("password"));
        loginPage.clickLoginButton();

        Assert.assertTrue(
                loginPage.getErrorMessage().contains("Username and password do not match"),
                "Expected invalid login error was not displayed"
        );
    }

    @Test
    public void emptyPasswordLoginTest() {
        loginPage.enterUsername((String) emptyPassLogin.get("username"));
        loginPage.enterPassword((String) emptyPassLogin.get("password"));
        loginPage.clickLoginButton();

        Assert.assertTrue(
                loginPage.getErrorMessage().contains("Password is required"),
                "Expected password required error was not displayed"
        );
    }

    @Test
    public void wrongUserAndPassLoginTest() {
        loginPage.enterUsername((String) wrongPassAndUserLogin.get("username"));
        loginPage.enterPassword((String) wrongPassAndUserLogin.get("password"));
        loginPage.clickLoginButton();

        Assert.assertTrue(
                loginPage.getErrorMessage().contains("Username and password do not match"),
                "Expected invalid credentials error was not displayed"
        );
    }



}


