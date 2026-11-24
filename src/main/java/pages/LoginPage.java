package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private final By usernameLocator=By.id("user-name");
    private final By passwordLocator=By.id("password");
    private final By loginButtonLocator=By.id("login-button");
    private final By errorMessageLocator=By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver){
        super(driver);
    }
    public void enterPassword(String password){
        actions.enter(passwordLocator,password);

    }
    public void enterUsername(String username){
        actions.enter(usernameLocator,username);
    }
    public String getErrorMessage(){
        return actions.getText(errorMessageLocator);
    }
    public void clickLoginButton(){
        actions.click(loginButtonLocator);

    }



}
