package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage extends BasePage{
    private final By cartIconLocator=By.cssSelector("[data-test='shopping-cart-link']");
    private final By productsLocator=By.className("inventory_item_description");
    private final By socialMediaXLocator=By.cssSelector("[data-test='social-x']");
    private final By socialLinkedInLocator=By.cssSelector("[data-test='social-linkedin']");
    private final By socialFacebookLocator=By.cssSelector("[data-test='social-facebook']");
    private final By sideTabLocator= By.id("react-burger-menu-btn");
    private final By logoutLinkLocator=By.id("logout_sidebar_link");


    public InventoryPage(WebDriver driver){
        super(driver);

    }
    public String getTitle(){
        return actions.getTitle();
    }
    public int getNumberOfProducts(){
      return   actions.countElements(productsLocator);
    }
    public boolean isCartIconDisplayed(){
        return actions.waitForVisibility(cartIconLocator).isDisplayed();
        }

    public String clickXIcon(){
        String originalWindow=driver.getWindowHandle();
        actions.click(socialMediaXLocator);
        actions.switchToNewWindow();
        String Url= actions.getCurrentUrl();
        driver.close();
        driver.switchTo().window(originalWindow);
        return Url;

    }
    public String clickFacebookIcon(){
        String originalWindow=driver.getWindowHandle();
        actions.click(socialFacebookLocator);
        actions.switchToNewWindow();
        String Url= actions.getCurrentUrl();
        driver.close();
        driver.switchTo().window(originalWindow);
        return Url;

    }
    public String clickLinkedInIcon(){
        String originalWindow=driver.getWindowHandle();
        actions.click(socialLinkedInLocator);
        actions.switchToNewWindow();
        String Url= actions.getCurrentUrl();
        driver.close();
        driver.switchTo().window(originalWindow);
        return Url;
    }
    public void addItem(String productName){
        By addItemByNameLocator=By.xpath("//div[contains(.,'" + productName +"')]/ancestor::div[@class='inventory_item']//button[text()='Add to cart']");
        actions.click(addItemByNameLocator);
    }
    public void removeItem(String productName){
        By removeItemByNameLocator=By.xpath("//div[contains(.,'" + productName +"')]/ancestor::div[@class='inventory_item']//button[text()='Remove']");
        actions.click(removeItemByNameLocator);
    }
    public double getProductPrice(String productName){
        By priceLocator=By.xpath(
                "//div[contains(.,'" + productName +
                        "')]/ancestor::div[@class='inventory_item']//div[@class='inventory_item_price']"
        );
        String priceText = actions.getText(priceLocator);
        return Double.parseDouble(priceText.replace("$",""));

    }
    public void clickCartIcon(){
        actions.click(cartIconLocator);
    }
    public String getButtonText(String productName){
        By productButtonLocator=By.xpath(
                "//div[contains(.,'" + productName +
                        "')]/ancestor::div[@class='inventory_item']//button");
        return actions.getText(productButtonLocator);
    }
    public void clickSideTab(){
        actions.click(sideTabLocator);
    }
    public void clickLogoutLink(){
        actions.click(logoutLinkLocator);
    }

}
