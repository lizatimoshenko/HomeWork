
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
    public static final String ADDRESS= RandomStringUtils.randomAlphabetic(20);
    public static final String PHONE= RandomStringUtils.randomNumeric(10);

    public static final String admin = "admin";
    public static final String password = "123";


    public static void main(String[] args) {

        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://80.92.229.236:81/auth/login");

        WebElement usernameInput = driver.findElement(By.id("username"));
        usernameInput.sendKeys(admin);

        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys(password);

        WebElement loginButton = driver.findElement(By.id("login"));
        loginButton.click();


        WebElement insert = driver.findElement(By.id("ADD LATER"));
        insert.click();



        PockerPlayer player = new PockerPlayer(USERNAME,EMAIL,FIRSTNAME,LASTNAME,CITY,ADDRESS,PHONE);
        create(player,driver);

        String expectedTitle="Players";
        String actualTitle=driver.getTitle();
        assertText(actualTitle, expectedTitle);

        WebElement searchUserName = driver.findElement(By.id("ADD LATER"));
        searchUserName.sendKeys(player.getUserName());

        WebElement searchButton = driver.findElement(By.id("ADD LATER"));
        searchButton.click();

        /*find edit link for searched user and open it */

        check(player,driver);

        edit(player,driver);

        /*open edit form*/

        check(player,driver);


        driver.quit();
    }

    public static void assertText(String actualValue, String expectedValue) {
        if(actualValue.equals(expectedValue)) {
            System.out.println("Passed.");
        } else {
            System.err.println("Failed. Expected text is "
                    + expectedValue + ", but Actual title is " + actualValue);
        }
    }

    public static void create(PockerPlayer obj,WebDriver driver){
        WebElement userName = driver.findElement(By.xpath("ADD LATER "));
        userName.sendKeys(obj.getUserName());

        WebElement email = driver.findElement(By.xpath("ADD LATER "));
        email.sendKeys(obj.getEmail());

        WebElement firstName = driver.findElement(By.xpath("ADD LATER "));
        firstName.sendKeys(obj.getFirstName());

        WebElement lastName = driver.findElement(By.xpath("ADD LATER "));
        lastName.sendKeys(obj.getLastName());

        WebElement city = driver.findElement(By.xpath("ADD LATER "));
        city.sendKeys(obj.getCity());
        /*
        * WebElement country = driver.findElement(By.xpath("//select/option[@value='1']"));
        * country.click();
        */

        WebElement address = driver.findElement(By.xpath("ADD LATER "));
        address.sendKeys(obj.getAddress());

        WebElement phone = driver.findElement(By.xpath("ADD LATER "));
        phone.sendKeys(obj.getPhone());

        WebElement saveButton = driver.findElement(By.id("ADD LATER"));
        saveButton.click();


    }
    public static void check(PockerPlayer obj,WebDriver driver){

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

    public static void edit(PockerPlayer obj,WebDriver driver){

        WebElement email = driver.findElement(By.xpath("ADD LATER "));
        String newEmail = RandomStringUtils.randomAlphabetic(5) + "@test.com";
        obj.setEmail(newEmail);
        email.sendKeys(obj.getEmail());

        WebElement firstName = driver.findElement(By.xpath("ADD LATER "));
        String newFirstName =  RandomStringUtils.randomAlphabetic(5);
        obj.setFirstName(newFirstName);
        firstName.sendKeys(obj.getFirstName());

        WebElement lastName = driver.findElement(By.xpath("ADD LATER "));
        String newLastName =  RandomStringUtils.randomAlphabetic(5);
        obj.setLastName(newLastName);
        lastName.sendKeys(obj.getLastName());

        WebElement city = driver.findElement(By.xpath("ADD LATER "));
        String newCity = RandomStringUtils.randomAlphabetic(10);
        obj.setCity(newCity);
        city.sendKeys(obj.getCity());


        WebElement address = driver.findElement(By.xpath("ADD LATER "));
        String newAddress = RandomStringUtils.randomAlphabetic(20);
        obj.setAddress(newAddress);
        address.sendKeys(obj.getAddress());

        WebElement phone = driver.findElement(By.xpath("ADD LATER "));
        String newPhone = RandomStringUtils.randomNumeric(10);
        obj.setPhone(newPhone);
        phone.sendKeys(obj.getPhone());

        WebElement saveButton = driver.findElement(By.id("ADD LATER"));
        saveButton.click();

    }
}
