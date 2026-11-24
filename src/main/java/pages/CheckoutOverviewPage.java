package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutOverviewPage extends BasePage{
    private final By itemPriceLocator= By.className("inventory_item_price");
    private final By finishButtonLocator=By.id("finish");
    private final By itemTotalLocator =
            By.cssSelector("[data-test='subtotal-label']");
    public CheckoutOverviewPage(WebDriver driver){
        super(driver);
    }

//    public double getProductsTotal() {
//        double total = 0;
//
//        int numberOfProducts = actions.countElements(itemPriceLocator);
//
//        for (int i = 0; i < numberOfProducts; i++) {
//            String price = driver.findElements(itemPriceLocator).get(i).getText();
//            total += Double.parseDouble(price.replace("$", ""));
//        }
//
//        return total;
//    }
    public double getTotalPrice(){
        String totalPrice=actions.getText(itemTotalLocator);
        return  Double.parseDouble(totalPrice.replace("Item total: $",""));

    }
    public void clickFinish(){
        actions.click(finishButtonLocator);
    }


}
