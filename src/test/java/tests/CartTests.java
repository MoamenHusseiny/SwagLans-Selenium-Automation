package tests;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import utilities.DataDriven;

import java.util.List;
import java.util.Map;

public class CartTests extends BaseTests {
    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CartPage cartPage;
    private CheckoutOverviewPage checkoutOverviewPage;
    private CheckoutInfoPage checkoutInfoPage;
    private Map<String,Object>cartData;
    private List<String> cartProducts;
    private Map<String, Object> checkoutData;
    private Map<String, Object> validCheckout;

    @BeforeClass
    public void dataSetup(){
        cartData=DataDriven.readJson("testData/cartData.json");
        cartProducts=(List<String>) cartData.get("cartProducts");

        checkoutData = DataDriven.readJson("testData/checkoutData.json");
        validCheckout = (Map<String, Object>) checkoutData.get("validCheckout");
    }
    @BeforeMethod
    public void loginSetup() {
        loginPage = new LoginPage(driver);

        loginPage.enterUsername((String) validLogin.get("username"));
        loginPage.enterPassword((String) validLogin.get("password"));
        loginPage.clickLoginButton();

        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
        checkoutInfoPage= new CheckoutInfoPage(driver);
        checkoutOverviewPage= new CheckoutOverviewPage(driver);


    }
    @Test
    public void checkCartIsEmpty(){
        inventoryPage.clickCartIcon();
        int itemsCount=cartPage.countItemsInCart();
        Assert.assertEquals(itemsCount,0);

    }
    @Test
    public void verifyCartProductsAndOrder(){


        for(String product : cartProducts){
            inventoryPage.addItem(product);
        }
        inventoryPage.clickCartIcon();
        List<String> actualProducts = cartPage.getProductsNames();

        Assert.assertEquals(actualProducts, cartProducts,
                "Cart products or their order do not match");


    }
    @Test
    public void verifyRemovingItemFromCart(){
        for(String product : cartProducts){
            inventoryPage.addItem(product);
        }
        inventoryPage.clickCartIcon();
        List<String> actualProducts = cartPage.getProductsNames();
        cartPage.clickContShoppingButton();
        Assert.assertEquals(inventoryPage.getButtonText("Sauce Labs Bolt T-Shirt")
                ,"Remove");
        inventoryPage.removeItem("Sauce Labs Bolt T-Shirt");
        Assert.assertEquals(inventoryPage.getButtonText("Sauce Labs Bolt T-Shirt")
                ,"Add to cart");
        Assert.assertEquals(
                inventoryPage.getButtonText("Sauce Labs Backpack"),
                "Remove"
        );

        Assert.assertEquals(
                inventoryPage.getButtonText("Sauce Labs Onesie"),
                "Remove"
        );
    }
    @Test
    public void verifyTotalPriceCalculation(){
        double totalPrice=0;
        for(String product:cartProducts) {
            totalPrice+= inventoryPage.getProductPrice(product);
            inventoryPage.addItem(product);

        }
        inventoryPage.clickCartIcon();
        cartPage.clickCheckoutButton();

        checkoutInfoPage.enterFirstName((String) validCheckout.get("firstName"));
        checkoutInfoPage.enterLastName((String) validCheckout.get("lastName"));
        checkoutInfoPage.enterZipCode((String) validCheckout.get("postalCode"));
        checkoutInfoPage.clickContinue();
        double actualTotalPrice=checkoutOverviewPage.getTotalPrice();
        Assert.assertEquals(actualTotalPrice,totalPrice,0.01,
                "Total price doesn't match items price");


    }
    @Test
    public void emptyCartCheckout(){
        inventoryPage.clickCartIcon();
        int items=cartPage.countItemsInCart();
        Assert.assertEquals(items,0,"Cart is not empty");
        cartPage.clickCheckoutButton();
        checkoutInfoPage.enterFirstName((String) validCheckout.get("firstName"));
        checkoutInfoPage.enterLastName((String) validCheckout.get("lastName"));
        checkoutInfoPage.enterZipCode((String) validCheckout.get("postalCode"));
        checkoutInfoPage.clickContinue();
        checkoutOverviewPage.clickFinish();
        String url=driver.getCurrentUrl();
        Assert.assertEquals(url, "https://www.saucedemo.com/checkout-complete.html",
                "Checkout didn't proceed");

    }
    @Test
    public void cartPersistenceCheck(){
        for(String products:cartProducts){
        inventoryPage.addItem(products);

        }
        inventoryPage.clickSideTab();
        inventoryPage.clickLogoutLink();

        loginPage.enterUsername((String)validLogin.get("username"));
        loginPage.enterPassword((String) validLogin.get("password"));
        loginPage.clickLoginButton();
        inventoryPage.clickCartIcon();
        List<String> actualItems= cartPage.getProductsNames();
        Assert.assertEquals(actualItems,cartProducts);



    }





}
