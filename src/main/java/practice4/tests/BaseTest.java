package practice4.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import practice4.pages.LoginPage;

import java.util.concurrent.TimeUnit;


/**
 * Created by Liza on 09.12.2016.
 */
public abstract class BaseTest {
    protected WebDriver driver;
    protected LoginPage loginPage;


    /**
     * Precondition
     * 1.Open browser FireFox
     * 2.Open url "http://80.92.229.236:81/auth/login"
     * 3.Set username to "admin"
     * 4.Set password to "123"
     * 5.Click to "login" button
     */
    @BeforeTest
    protected void openFireFox(){
        driver=new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        loginPage= new LoginPage(driver);
        loginPage.open();
        loginPage.setUsername("admin");
        loginPage.setPassword("123");
        loginPage.logIn();
    }

    public WebDriver getDriver() {
        return driver;
    }

    /**
     * Post-condition
     * 1.Close browser
     */
    @AfterTest
    public void closeBrowser(){
        driver.quit();
    }

}
