package practice4.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Liza on 02.12.2016.
 */

public class LoginPage {

    @FindBy(id="username")
    private WebElement usernameInput;

    @FindBy(id="password")
    private WebElement passwordInput;

    @FindBy(id="logIn")
    private WebElement logInButton;

    protected WebDriver driver;
    protected static final String URL="http://80.92.229.236:81/auth/login";

    public LoginPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    public void open(){
        driver.get(URL);
    }

    public void setUsername(String value) {
        usernameInput.clear();
        usernameInput.sendKeys(value);
    }

    public void setPassword(String value) {
        passwordInput.clear();
        passwordInput.sendKeys(value);
    }

    public void logIn() {
        logInButton.click();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public String getUsernameErrorMessage() {
        WebElement errorElement= driver.findElement(By.xpath(".//*[@id='username-element']/ul/li"));
        return errorElement.getText();
    }

    public String getPasswordErrorMessage() {
        WebElement errorElement= driver.findElement(By.xpath(".//*[@id='password-element']/ul/li"));
        return errorElement.getText();
    }

    public void setUsernameUsingJS(String value) {
        String script ="document.getElementById('username').value=' "+value+"' ";
        ((JavascriptExecutor) driver).executeScript(script);
    }

}
