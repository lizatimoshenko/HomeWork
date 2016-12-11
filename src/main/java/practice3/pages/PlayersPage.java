package practice3.pages;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;


/**
 * Created by Liza on 02.12.2016.
 */

public class PlayersPage {




    protected WebDriver driver;

    public PlayersPage(WebDriver driver) {
        this.driver =driver;
    }

    public void openByLink() {
        waitForPresenceOfElementLocated(By.xpath(".//a[text()='Insert']"));
        driver.findElement(By.xpath(".//a[text()='Insert']")).click();
    }

    public void openByPlusIcon(){
        waitForPresenceOfElementLocated(By.xpath(".//img[@title='Insert']"));
        driver.findElement(By.xpath(".//img[@title='Insert']")).click();
    }



    /**
     * Method waits for presence of element.
     * @param locator - locator of element which was found by id, xpath or etc.
     */
    public void waitForPresenceOfElementLocated(By locator) {
        Wait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }


    public void enterUserNameToSearch(String value){
        WebElement searchUserName = driver.findElement(By.xpath(".//input[contains(@id,'login')]"));
        searchUserName.clear();
        searchUserName.sendKeys(value);
    }

    public void search(){
        WebElement searchButton = driver.findElement(By.xpath(".//input[@value='Search']"));
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



    public void enterEmailToSearch(String email) {
        WebElement searchEmailInput = driver.findElement(By.xpath(".//input[contains(@id,'email')]"));
        searchEmailInput.clear();
        searchEmailInput.sendKeys(email);
    }


    public void selectBalance(String balance) {
        Select playerBalance = new Select(driver.findElement(By.xpath(".//select[contains(@id,'real_amount')]")));
        playerBalance.selectByValue(balance);
    }

    public void logOut(){
        WebElement logoutSpan=driver.findElement(By.xpath(".//span[@class='logout']"));
        logoutSpan.click();
    }

    public String getConfirmDeletingMessage() {
        WebElement deleteMessage = driver.findElement(By.xpath(".//div[contains(@id,'messagespanel')]/ul/li"));
         return deleteMessage.getText();
    }

    public String  getTitle() {
        return driver.getTitle();
    }

    public void resetSearchFields() {
        WebElement resetButton = driver.findElement(By.xpath(".//input[@value='Search']"));
        resetButton.click();
    }
}
