package practice4.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * Created by Liza on 02.12.2016.
 */

public class PlayersPage {

    @FindBy(xpath = ".//a[text()='Insert']")
    private WebElement insertLink;

    @FindBy(xpath =".//input[contains(@id,'login')]" )
    private WebElement searchUserNameInput;

    @FindBy(xpath =".//input[@value='Search']")
    private WebElement searchButton;

    @FindBy(xpath =".//input[contains(@id,'email')]")
    private WebElement searchEmailInput;

    @FindBy(xpath =".//select[contains(@id,'real_amount')]")
    private WebElement playerBalanceSelect;

    @FindBy(xpath =".//span[@class='logout']")
    private WebElement logoutSpan;

    @FindBy(xpath =".//div[contains(@id,'messagespanel')]/ul/li")
    private WebElement deleteConfirmationMessage;

    @FindBy(xpath =".//input[@value='Search']")
    private WebElement resetButton;

    protected WebDriver driver;

    public PlayersPage(WebDriver driver) {
        this.driver =driver;
        PageFactory.initElements(driver,this);
    }


    public void openByLink() {
        insertLink.click();
    }

    public void openByPlusIcon(){
       // waitForPresenceOfElementLocated(By.xpath(".//img[@title='Insert']"));
        driver.findElement(By.xpath(".//img[@title='Insert']")).click();
    }




    public void enterUserNameToSearch(String value){
        searchUserNameInput.clear();
        searchUserNameInput.sendKeys(value);
    }

    public void search(){
        searchButton.click();
    }

    public void delete(String userName) {
        driver.findElement(By.xpath(".//tr[.//a[text()='" + userName + "']]//img[@title='Delete']")).click();
        Alert alertOK = driver.switchTo().alert();
        alertOK.accept();
    }

    public void openEditPage(String userName) {
        driver.findElement(By.xpath(".//tr[.//a[text()='" + userName + "']]//img[@title='Edit']")).click();
    }

    public Boolean findPlayerInTheTable(String userName){
        if (isElementPresent(By.xpath(".//td[.//a[text()='"+userName+"']]/a"))==true){
            return true;
        }
        else{
            return  false;
        }
    }

    public boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    /**
     * Method waits for presence of element.
     * @param locator - locator of element which was found by id, xpath or etc.
     */
    public void waitForPresenceOfElementLocated(By locator) {
        Wait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public void enterEmailToSearch(String email){
        searchEmailInput.clear();
        searchEmailInput.sendKeys(email);
    }

    public void logOut(){
        logoutSpan.click();
    }

    public String getConfirmDeletingMessage(){
         return deleteConfirmationMessage.getText();
    }

    public String getTitle(){
        return driver.getTitle();
    }

    public void resetSearchFields(){
        resetButton.click();
    }
}
