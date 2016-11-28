
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.apache.commons.lang.RandomStringUtils;
import java.util.concurrent.TimeUnit;

/**
 * Created by Liza on 25.11.2016.
 */
public class LoginTests {
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


        /*WebElement insertLink = driver.findElement(By.xpath(insertLinkID));
        insertLink.click();



        PockerPlayer player = new PockerPlayer(USERNAME,PASSWORD,EMAIL,FIRSTNAME,LASTNAME,CITY,ADDRESS,PHONE);
        createUser(player,driver);
        /*
        String expectedTitle="Players";
        String actualTitle=driver.getTitle();
        assertText(actualTitle, expectedTitle);
        */
        WebElement searchUserName = driver.findElement(By.xpath(searchByUserNameID));
        //searchUserName.sendKeys(player.getUserName());

        /* for existed user in the table */
        String userName ="user151";
        searchUserName.sendKeys(userName);


        WebElement searchButton = driver.findElement(By.xpath(searchButtonID));
        searchButton.click();

        /*How to write this xpath like a constant ?*/
        driver.findElement(By.xpath(".//tr[.//a[text()='" + userName + "']]//img[@title='Edit']")).click();
        edit(driver);

        //checkData(player,driver);

        //editUser(player,driver);



        /*checkData(player,driver);


        driver.quit();*/
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

        /*
        * WebElement country = driver.findElement(By.xpath("//select/option[@value='?']"));
        * country.click();
        */

    }
    public static void checkData(PockerPlayer obj,WebDriver driver){

        WebElement email = driver.findElement(By.xpath("ADD LATER "));
        String actualEmail=email.getText();
        String expectedEmail=obj.getEmail();
        assertText(actualEmail,expectedEmail);

        WebElement firstName = driver.findElement(By.xpath("ADD LATER "));
        String actualFirstName=firstName.getText();
        String expectedFirstName=obj.getFirstName();
        assertText(actualFirstName,expectedFirstName);

        WebElement lastName = driver.findElement(By.xpath("ADD LATER "));
        String actualLastName=lastName.getText();
        String expectedLastName=obj.getLastName();
        assertText(actualLastName,expectedLastName);

        WebElement city = driver.findElement(By.xpath("ADD LATER "));
        String actualCity=city.getText();
        String expectedCity=obj.getCity();
        assertText(actualCity,expectedCity);


        /*
        *check country ???
        */

        WebElement address = driver.findElement(By.xpath("ADD LATER "));
        String actualAddress=address.getText();
        String expectedAddress=obj.getAddress();
        assertText(actualAddress,expectedAddress);

        WebElement phone = driver.findElement(By.xpath("ADD LATER "));
        String actualPhone=phone.getText();
        String expectedPhone=obj.getPhone();
        assertText(actualPhone,expectedPhone);
    }

    public static void editUser(PockerPlayer obj,WebDriver driver){

        WebElement email = driver.findElement(By.xpath(emailInputID));
        String newEmail = RandomStringUtils.randomAlphabetic(5) + "@test.com";
        obj.setEmail(newEmail);
        email.sendKeys(obj.getEmail());

        WebElement firstName = driver.findElement(By.xpath(fnameInputID));
        String newFirstName =  RandomStringUtils.randomAlphabetic(5);
        obj.setFirstName(newFirstName);
        firstName.sendKeys(obj.getFirstName());

        WebElement lastName = driver.findElement(By.xpath(lnameInputID));
        String newLastName =  RandomStringUtils.randomAlphabetic(5);
        obj.setLastName(newLastName);
        lastName.sendKeys(obj.getLastName());

        WebElement city = driver.findElement(By.xpath(cityInputID));
        String newCity = RandomStringUtils.randomAlphabetic(10);
        obj.setCity(newCity);
        city.sendKeys(obj.getCity());


        WebElement address = driver.findElement(By.xpath(addressTextAreaID));
        String newAddress = RandomStringUtils.randomAlphabetic(20);
        obj.setAddress(newAddress);
        address.sendKeys(obj.getAddress());

        WebElement phone = driver.findElement(By.xpath(phoneInputID));
        String newPhone = RandomStringUtils.randomNumeric(10);
        obj.setPhone(newPhone);
        phone.sendKeys(obj.getPhone());

        WebElement saveButton = driver.findElement(By.id(saveButtonID));
        saveButton.click();

    }
     /*edit user version for user that exists in the table */
    public static void edit(WebDriver driver){

        WebElement email = driver.findElement(By.xpath(emailInputID));
        String newEmail = RandomStringUtils.randomAlphabetic(5) + "@test.com";
        email.clear();
        email.sendKeys(newEmail);

        WebElement firstName = driver.findElement(By.xpath(fnameInputID));
        String newFirstName =  RandomStringUtils.randomAlphabetic(5);
        firstName.clear();
        firstName.sendKeys(newFirstName);

        WebElement lastName = driver.findElement(By.xpath(lnameInputID));
        String newLastName =  RandomStringUtils.randomAlphabetic(5);
        lastName.clear();
        lastName.sendKeys(newLastName);

        WebElement city = driver.findElement(By.xpath(cityInputID));
        String newCity = RandomStringUtils.randomAlphabetic(10);
        city.clear();
        city.sendKeys(newCity);


        WebElement address = driver.findElement(By.xpath(addressTextAreaID));
        String newAddress = RandomStringUtils.randomAlphabetic(20);
        address.clear();
        address.sendKeys(newAddress);

        WebElement phone = driver.findElement(By.xpath(phoneInputID));
        String newPhone = RandomStringUtils.randomNumeric(10);
        phone.clear();
        phone.sendKeys(newPhone);

        WebElement saveButton = driver.findElement(By.xpath(saveButtonID));
        saveButton.click();

    }
}
