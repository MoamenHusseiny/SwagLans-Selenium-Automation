package tests;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;

public class InventoryTests extends BaseTests {
    LoginPage loginPage;
    InventoryPage inventoryPage;
    @BeforeMethod
    public void loginSetup(){
        loginPage=new LoginPage(driver);
        loginPage.enterUsername((String) validLogin.get("username"));
        loginPage.enterPassword((String) validLogin.get("password"));
        loginPage.clickLoginButton();
        inventoryPage= new InventoryPage(driver);

    }
    @Test
    public void checkSocialLinks(){
        String linkedInURL=inventoryPage.clickLinkedInIcon();
        Assert.assertTrue(linkedInURL.contains("linkedin"));

        String XUrl=inventoryPage.clickXIcon();
        Assert.assertTrue(XUrl.contains("x.com"));

        String FacebookUrl=inventoryPage.clickFacebookIcon();
        Assert.assertTrue(FacebookUrl.contains("facebook"));

    }


}
