package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutInfoPage extends BasePage{
    private final By firstNameLocator=By.id("first-name");
    private final By lastNameLocator=By.id("last-name");
    private final By zipCodeLocator=By.id("postal-code");
    private final By continueButtonLocator=By.id("continue");
    public CheckoutInfoPage(WebDriver driver){
        super(driver);
    }
    public void enterFirstName(String firstName){
        actions.enter(firstNameLocator,firstName);
    }
    public void enterLastName(String lastName){
        actions.enter(lastNameLocator,lastName);
    }
    public void enterZipCode(String ZipCode){
       actions.enter(zipCodeLocator,ZipCode);
    }
    public void clickContinue(){
        actions.click(continueButtonLocator);
    }

}
