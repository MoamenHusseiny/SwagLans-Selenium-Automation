package pages;

import org.openqa.selenium.WebDriver;
import utilities.ElementsActions;

public abstract class BasePage {
    protected final WebDriver driver;
    protected final ElementsActions actions;
    public BasePage(WebDriver driver){
        this.driver=driver;
        this.actions= new ElementsActions(driver);
    }
}
