
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.apache.commons.lang.RandomStringUtils;
import java.util.concurrent.TimeUnit;

/**
 * Created by Liza on 25.11.2016.
 */
public class Tests {
    public static final String USERNAME= RandomStringUtils.randomAlphabetic(8);
    public static final String EMAIL =RandomStringUtils.randomAlphabetic(5) + "@test.com";
    public static final String FIRSTNAME = RandomStringUtils.randomAlphabetic(5);
    public static final String LASTNAME= RandomStringUtils.randomAlphabetic(10);
    public static final String CITY = RandomStringUtils.randomAlphabetic(10);
    public static final String ADDRESS= RandomStringUtils.randomAlphabetic(10);
    public static final String PHONE= RandomStringUtils.randomNumeric(10);
    public static final String PASSWORD=RandomStringUtils.randomAlphanumeric(8);


    private static final String admin = "admin";
    private static final String password = "123";

    /*buttons*/
    private static final String loginButtonID="logIn";
    private static final String inserLinkID=".//a[text()='Insert']";
    private static final String saveButtonID=".//input[@name='button_save']";

    /*input fields*/
    private static final String searchByUserNameID=".//input[contains(@id,'login')]";
    private static final String searchButtonID= ".//input[@value='Search']";
    private static final String userNameInputID =".//input[contains(@id,'us_login')] ";
    private static final String emailInputID=".//input[contains(@id,'us_email')]";
    private static final String passwordInputID=".//input[contains(@id,'us_password')] ";
    private static final String confirmPasswordID=".//input[contains(@id,'confirm_password')] ";
    private static final String fnameInputID=".//input[contains(@id,'us_fname')]";
    private static final String lnameInputID=".//input[contains(@id,'us_lname')]";
    private static final String cityInputID=".//input[contains(@id,'us_city')]";
    private static final String addressTextAreaID=".//textarea[contains(@id,'us_address')]";
    private static final String phoneInputID=".//input[contains(@id,'us_phone')]";




    public static void main(String[] args) {

        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://80.92.229.236:81/auth/login");

        WebElement usernameInput = driver.findElement(By.id("username"));
        usernameInput.sendKeys(admin);

        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys(password);

        WebElement loginButton = driver.findElement(By.id(loginButtonID));
        loginButton.click();


        WebElement insertLink = driver.findElement(By.xpath(inserLinkID));
        insertLink.click();

        PockerPlayer player = new PockerPlayer(USERNAME,PASSWORD,EMAIL,FIRSTNAME,LASTNAME,CITY,ADDRESS,PHONE);


        createUser(player,driver);

        String expectedTitle="Players";
        String actualTitle=driver.getTitle();
        assertText(actualTitle, expectedTitle);


        WebElement searchUserName = driver.findElement(By.xpath(searchByUserNameID));
        searchUserName.sendKeys(player.getUserName());

        WebElement searchButton = driver.findElement(By.xpath(searchButtonID));
        searchButton.click();

        /*How to write this xpath like a constant ?*/
        driver.findElement(By.xpath(".//tr[.//a[text()='" + player.getUserName() + "']]//img[@title='Edit']")).click();

        checkData(player,driver);

        driver.findElement(By.xpath(".//tr[.//a[text()='" + player.getUserName() + "']]//img[@title='Edit']")).click();
        editUser(player,driver);

        driver.findElement(By.xpath(".//tr[.//a[text()='" + player.getUserName() + "']]//img[@title='Edit']")).click();
        checkData(player,driver);
        System.out.println("Test finished");
        
        /*driver.quit();*/
    }

    public static void assertText(String actualValue, String expectedValue) {
        if(actualValue.equals(expectedValue)) {
            System.out.println("Passed.");
        } else {
            System.err.println("Failed. Expected text is "
                    + expectedValue + ", but Actual title is " + actualValue);
        }
    }

    public static void createUser(PockerPlayer obj,WebDriver driver){
        WebElement userName = driver.findElement(By.xpath(userNameInputID));
        userName.sendKeys(obj.getUserName());

        WebElement email = driver.findElement(By.xpath(emailInputID));
        email.sendKeys(obj.getEmail());

        WebElement password = driver.findElement(By.xpath(passwordInputID));
        password.sendKeys(obj.getPassword());

        WebElement confirmPassword=driver.findElement(By.xpath(confirmPasswordID));
        confirmPassword.sendKeys(obj.getPassword());


        WebElement firstName = driver.findElement(By.xpath(fnameInputID));
        firstName.sendKeys(obj.getFirstName());

        WebElement lastName = driver.findElement(By.xpath(lnameInputID));
        lastName.sendKeys(obj.getLastName());

        WebElement city = driver.findElement(By.xpath(cityInputID));
        city.sendKeys(obj.getCity());


        WebElement address = driver.findElement(By.xpath(addressTextAreaID));
        address.sendKeys(obj.getAddress());

        WebElement phone = driver.findElement(By.xpath(phoneInputID));
        phone.sendKeys(obj.getPhone());

        WebElement saveButton = driver.findElement(By.xpath(saveButtonID));
        saveButton.click();

        /* ??????
        * WebElement country = driver.findElement(By.xpath("//select/option[@value='?????']"));
        * country.click();
        */

    }
    public static void checkData(PockerPlayer player,WebDriver driver){

        WebElement email = driver.findElement(By.xpath(emailInputID));
        String actualEmail = email.getAttribute("value");
        String expectedEmail=player.getEmail();
        assertText(actualEmail,expectedEmail);


        WebElement firstName = driver.findElement(By.xpath(fnameInputID));
        String actualFirstName=firstName.getAttribute("value");
        String expectedFirstName=player.getFirstName();
        assertText(actualFirstName,expectedFirstName);


        WebElement lastName = driver.findElement(By.xpath(lnameInputID));
        String actualLastName=lastName.getAttribute("value");
        String expectedLastName=player.getLastName();
        assertText(actualLastName,expectedLastName);


        WebElement city = driver.findElement(By.xpath(cityInputID));
        String actualCity=city.getAttribute("value");
        String expectedCity=player.getCity();
        assertText(actualCity,expectedCity);

        WebElement address = driver.findElement(By.xpath(addressTextAreaID));
        String actualAddress=address.getAttribute("value");
        String expectedAddress=player.getAddress();
        assertText(actualAddress,expectedAddress);

        System.out.println("phone:");
        WebElement phone = driver.findElement(By.xpath(phoneInputID));
        String actualPhone=phone.getAttribute("value");
        String expectedPhone=player.getPhone();
        assertText(actualPhone,expectedPhone);

        WebElement saveButton = driver.findElement(By.xpath(saveButtonID));
        saveButton.click();


    }

    public static void editUser(PockerPlayer obj,WebDriver driver){

        WebElement email = driver.findElement(By.xpath(emailInputID));
        String newEmail = RandomStringUtils.randomAlphabetic(5) + "@test.com";
        email.clear();
        obj.setEmail(newEmail);
        email.sendKeys(obj.getEmail());

        WebElement firstName = driver.findElement(By.xpath(fnameInputID));
        firstName.clear();
        String newFirstName =  RandomStringUtils.randomAlphabetic(5);
        obj.setFirstName(newFirstName);
        firstName.sendKeys(obj.getFirstName());

        WebElement lastName = driver.findElement(By.xpath(lnameInputID));
        lastName.clear();
        String newLastName =  RandomStringUtils.randomAlphabetic(5);
        obj.setLastName(newLastName);
        lastName.sendKeys(obj.getLastName());

        WebElement city = driver.findElement(By.xpath(cityInputID));
        city.clear();
        String newCity = RandomStringUtils.randomAlphabetic(10);
        obj.setCity(newCity);
        city.sendKeys(obj.getCity());


        WebElement address = driver.findElement(By.xpath(addressTextAreaID));
        address.clear();
        String newAddress = RandomStringUtils.randomAlphabetic(20);
        obj.setAddress(newAddress);
        address.sendKeys(obj.getAddress());

        WebElement phone = driver.findElement(By.xpath(phoneInputID));
        phone.clear();
        String newPhone = RandomStringUtils.randomNumeric(10);
        obj.setPhone(newPhone);
        phone.sendKeys(obj.getPhone());

        WebElement saveButton = driver.findElement(By.xpath(saveButtonID));
        saveButton.click();

    }

}
