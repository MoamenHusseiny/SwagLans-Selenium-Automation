package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ElementsActions {


    private static final Duration TIMEOUT = Duration.ofSeconds(10);
    private final WebDriver driver;
    private final WebDriverWait wait;

    public ElementsActions(WebDriver driver){
        this.driver=driver;
        wait= new WebDriverWait(driver,TIMEOUT);
    }
    // Explicit wait condition: Visibility of element
    public WebElement waitForVisibility(By locator){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    // click method using locator
    public void click(By locator){
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }
        // enter keys into a field
        public void enter(By locator,String text){
            WebElement field =waitForVisibility(locator);
            field.clear();
            field.sendKeys(text);
    }
    // get text by locator
    public String getText(By locator){
       return waitForVisibility(locator).getText().trim();
    }

//    get multiple texts
    public List<String> getTexts(By locator) {
        List<String> texts = new ArrayList<>();


        for (WebElement element : driver.findElements(locator)) {
            texts.add(element.getText().trim());
        }
        return texts;
    }
    public int countElements(By locator){
        return driver.findElements(locator).size();
    }


    // browser methods
    public String getTitle(){
        return driver.getTitle();
    }

    //Window swapping if a new tap is opened

    public void switchToNewWindow() {
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(driver.getWindowHandle())) {
                driver.switchTo().window(handle);
                break;
            }

        }

    }
    public void switchToFrame(By locator) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
    }
    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    public Set<String> getWindowHandles(){
        return driver.getWindowHandles();
    }
    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }



}
