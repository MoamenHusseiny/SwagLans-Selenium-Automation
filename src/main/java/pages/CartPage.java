package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class CartPage  extends BasePage{
    private final By checkoutButton=By.id("checkout");
    private final By cartItemsLocator=By.className("inventory_item_name");
    private final By contShoppingButton=By.id("continue-shopping");
    public CartPage(WebDriver driver){
        super(driver);
    }
    public int countItemsInCart(){
        return actions.countElements(cartItemsLocator);

    }
    public void clickCheckoutButton(){
        actions.click(checkoutButton);
    }
    public void clickContShoppingButton(){
        actions.click(contShoppingButton);
    }
    public List<String> getProductsNames(){
        return actions.getTexts(cartItemsLocator);
    }

}
