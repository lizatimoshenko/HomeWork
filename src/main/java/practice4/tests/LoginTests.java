package practice4.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import practice4.pages.LoginPage;

import java.util.concurrent.TimeUnit;

/**
 * Created by Liza on 02.12.2016.
 */
public class LoginTests {
    protected static WebDriver driver;
    protected LoginPage loginPage;
    private SoftAssert softAssert;



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
       softAssert=new SoftAssert();

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
       // Assert.assertEquals(loginPage.getTitle(),"Players","Wrong Title");
        softAssert.assertEquals(loginPage.getTitle(),"Players","Wrong Title");
        softAssert.assertAll();
    }


    /**
     * 1.Set username to "admin"
     * 2.Set password to "12345"
     * 3.Click to "login" button
     * 4.Verify that error message equals to "Invalid username or password"
     *
     */
    @DataProvider(name = "negativeLoginTestDP")
    public Object[][] negativeLoginTestDP(){
        return new Object[][]{
                {"admin","12345","Invalid username or password"},
                {"admin","123456789","Invalid username or password",}
        };

    }
    @Test(dataProvider = "negativeLoginTestDP", dependsOnMethods ="negativeLoginTestParameters")
    public void negativeLoginTestDP(String userName, String password, String expectedErrorMessage){
        loginPage.setUsernameUsingJS(userName);
        loginPage.setPassword(password);
        loginPage.logIn();
        Assert.assertEquals(loginPage.getUsernameErrorMessage(),expectedErrorMessage,"Wrong error message");
    }

    @Parameters({"myUserName","myPassword","myErrorMessage"})
    @Test()
    public void negativeLoginTestParameters(String userName, String password, String expectedErrorMessage){
        loginPage.setUsernameUsingJS(userName);
        loginPage.setPassword(password);
        loginPage.logIn();
        Assert.assertEquals(loginPage.getUsernameErrorMessage(),expectedErrorMessage,"Wrong error message");
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
     * Post condition
     * 1.Close browser
     */
    @AfterTest
    public  void afterTest(){
        driver.quit();
    }
}
