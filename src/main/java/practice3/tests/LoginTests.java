package practice3.tests;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import practice3.pages.LoginPage;
import java.util.concurrent.TimeUnit;
/**
 * Created by Liza on 02.12.2016.
 */
public class LoginTests {
    protected static WebDriver driver;
    protected LoginPage loginPage;


    public WebDriver getDriver(){
        return driver;
    }

    /**
     * Precondition
     * 1.Open browser FireFox
     */
   @BeforeTest
   public void openFireFox(){
       driver = new FirefoxDriver();
       driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
   }


    /**
     * Precondition
     * 1.Open url "http://80.92.229.236:81/auth/login"
     */
   @BeforeMethod
   public void openWebApplication(){
       loginPage= new LoginPage(driver);
       loginPage.open();

   }

    /**
     * 1.Set username to "admin"
     * 2.Set password to "123"
     * 3.Click to "log in" button
     * 4.Verify that title of the page equals to "Players"
     *
     */
    @Test
    public void positiveLoginTest(){
        loginPage.setUsername("admin");
        loginPage.setPassword("123");
        loginPage.logIn();
        Assert.assertEquals(loginPage.getTitle(),"Players","Wrong Title");
    }


    /**
     * 1.Set username to "admin"
     * 2.Set password to "12345"
     * 3.Click to "login" button
     * 4.Verify that error message equals to "Invalid username or password"
     *
     */
    @Test
    public void negativeLoginTest(){
        loginPage.setUsernameUsingJS("admin");
        loginPage.setPassword("12345");
        loginPage.logIn();
        Assert.assertEquals(loginPage.getUsernameErrorMessage(),"Invalid username or password","Wrong error message");
    }

    /**
     * 1.Set username as empty field
     * 2.Set password to "123"
     * 3.Click to "login" button
     * 4.Verify that error message for username equals to "Value is required and can't be empty"
     *
     */
    @Test
    public void emptyUsernameFieldLoginTest(){
        loginPage.setUsername("");
        loginPage.setPassword("123");
        loginPage.logIn();
        Assert.assertEquals(loginPage.getUsernameErrorMessage(),"Value is required and can't be empty","Wrong error message");

    }

    /**
     * 1.Set username to "admin"
     * 2.Set password as empty field
     * 3.Click to "login" button
     * 4.Verify that error message for password equals to "Value is required and can't be empty"
     *
     */
    @Test
    public void emptyPasswordFieldTest(){
        loginPage.setUsername("admin");
        loginPage.setPassword("");
        loginPage.logIn();
        Assert.assertEquals(loginPage.getPasswordErrorMessage(),"Value is required and can't be empty","Wrong error message");
    }

    /**
     * 1.Set username as empty field
     * 2.Set password as empty field
     * 3.Click to "login" button
     * 4.Verify that error message for username equals to "Value is required and can't be empty"
     * 5.Verify that error message for password equals to "Value is required and can't be empty"
     */
    @Test
    public void emptyFieldsLoginTest(){
        loginPage.setUsername("");
        loginPage.setPassword("");
        loginPage.logIn();
        Assert.assertEquals(loginPage.getUsernameErrorMessage(),"Value is required and can't be empty","Wrong error message");
        Assert.assertEquals(loginPage.getPasswordErrorMessage(),"Value is required and can't be empty","Wrong error message");
    }

    /**
     * Postcondition
     * 1.Close browser
     */
    @AfterTest
    public  void afterTest(){
        driver.quit();
    }
}
