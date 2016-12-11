package practice4.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by Liza on 02.12.2016.
 */
public class CreateEditPokerPage {
    @FindBy(xpath =".//input[contains(@id,'us_login')]")
    private WebElement usernameInput;

    @FindBy(xpath =".//input[contains(@id,'us_email')]")
    private WebElement emailInput;

    @FindBy(xpath =".//input[contains(@id,'us_password')]")
    private WebElement passInput;

    @FindBy(xpath = ".//input[contains(@id,'confirm_password')] ")
    private WebElement confirmPass;

    @FindBy(xpath = ".//input[contains(@id,'us_fname')]")
    private WebElement firstNameInput;

    @FindBy(xpath = ".//input[contains(@id,'us_lname')]")
    private WebElement lastNameInput;

    @FindBy(xpath = ".//input[contains(@id,'us_city')]")
    private WebElement cityInput;

    @FindBy(xpath =".//select[contains(@id,'us_country')]")
    private WebElement countrySelect;

    @FindBy(xpath =".//textarea[contains(@id,'us_address')]")
    private WebElement addressTextArea;

    @FindBy(xpath =".//input[contains(@id,'us_phone')]" )
    private WebElement phoneInput;

    @FindBy(xpath =".//select[contains(@id,'us_gender')]")
    private WebElement genderSelect;

    @FindBy(xpath = ".//input[@name='button_save']")
    private WebElement saveButton;

    protected WebDriver driver;

    public CreateEditPokerPage(WebDriver driver){
        this.driver =driver;
        PageFactory.initElements(driver,this);
    }

    public void setUsername(String value){
        usernameInput.clear();
        usernameInput.sendKeys(value);
    }

    public String getUsername(){
        return usernameInput.getAttribute("value");
    }

    public void setEmail(String value){
        emailInput.clear();
        emailInput.sendKeys(value);
    }

    public String getEmail(){
       return emailInput.getAttribute("value");
    }


    public void setPassword(String value){
        passInput.clear();
        passInput.sendKeys(value);
    }

    public void confirmPassword(String value){
        confirmPass.clear();
        confirmPass.sendKeys(value);
    }

    public void setFirstName(String value){
        firstNameInput.clear();
        firstNameInput.sendKeys(value);
    }
    public String getFirstName(){
        return firstNameInput.getAttribute("value");
    }
    public void setLastName(String value){
        lastNameInput.clear();
        lastNameInput.sendKeys(value);
    }
    public String getLastName(){
        return lastNameInput.getAttribute("value");
    }
    public void setCity(String value){
        cityInput.clear();
        cityInput.sendKeys(value);
    }
    public String getCity() {
        return cityInput.getAttribute("value");
    }
    public void setCountry(String value){
        Select country = new Select(countrySelect);
        country.selectByValue(value);
    }

    public String  getCountry() {
        return countrySelect.getAttribute("value");
    }

    public void setAddress(String value){
        addressTextArea.clear();
        addressTextArea.sendKeys(value);
    }

    public String getAddress() {
        return addressTextArea.getAttribute("value");
    }
    public void setPhone(String value){
        phoneInput.clear();
        phoneInput.sendKeys(value);
    }

    public String getPhone() {
        return phoneInput.getAttribute("value");
    }

    public void setGender(int value){
        Select gender = new Select(genderSelect);
        gender.selectByValue(String.valueOf(value));
    }

    public void save(){
        saveButton.click();
    }


    public String getGender() {
        return genderSelect.getAttribute("value");
    }
}
