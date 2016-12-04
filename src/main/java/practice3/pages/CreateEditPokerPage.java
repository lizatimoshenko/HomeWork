package practice3.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by Liza on 02.12.2016.
 */
public class CreateEditPokerPage {
    protected WebDriver driver;

    public CreateEditPokerPage(WebDriver driver) {
        this.driver =driver;
    }

    public void setUsername(String value) {
        WebElement usernameInput =driver.findElement(By.xpath(".//input[contains(@id,'us_login')] "));
        usernameInput.clear();
        usernameInput.sendKeys(value);
    }

    public void setEmail(String value) {
        WebElement emailInput = driver.findElement(By.xpath(".//input[contains(@id,'us_email')]"));
        emailInput.clear();
        emailInput.sendKeys(value);
    }

    public void setPassword(String value) {
        WebElement passInput = driver.findElement(By.xpath(".//input[contains(@id,'us_password')]"));
        passInput.clear();
        passInput.sendKeys(value);
    }

    public void confirmPassword(String value) {
        WebElement confirmPass = driver.findElement(By.xpath(".//input[contains(@id,'confirm_password')] "));
        confirmPass.clear();
        confirmPass.sendKeys(value);
    }

    public void setFirstName(String value) {
        WebElement firstNameInput = driver.findElement(By.xpath(".//input[contains(@id,'us_fname')]"));
        firstNameInput.clear();
        firstNameInput.sendKeys(value);
    }

    public void setLastName(String value) {
        WebElement lastNameInput = driver.findElement(By.xpath(".//input[contains(@id,'us_lname')]"));
        lastNameInput.clear();
        lastNameInput.sendKeys(value);
    }

    public void setCity(String value) {
        WebElement cityInput = driver.findElement(By.xpath(".//input[contains(@id,'us_city')]"));
        cityInput.clear();
        cityInput.sendKeys(value);
    }

    public void setCountry(String value) {
        Select country = new Select(driver.findElement(By.xpath(".//select[contains(@id,'us_country')]")));
        country .selectByValue(value);
    }

    public void setAddress(String value) {
        WebElement addressTextArea = driver.findElement(By.xpath(".//textarea[contains(@id,'us_address')]"));
        addressTextArea.clear();
        addressTextArea.sendKeys(value);
    }

    public void setPhone(String value) {
        WebElement phoneInput = driver.findElement(By.xpath(".//input[contains(@id,'us_phone')]"));
        phoneInput.clear();
        phoneInput.sendKeys(value);
    }

    public void setGender(int value) {
        Select gender = new Select(driver.findElement(By.xpath(".//select[contains(@id,'us_gender')]")));
        gender.selectByValue(String.valueOf(value));
    }

    public void save() {
        WebElement saveButton = driver.findElement(By.xpath(".//input[@name='button_save']"));
        saveButton.click();
    }

}
