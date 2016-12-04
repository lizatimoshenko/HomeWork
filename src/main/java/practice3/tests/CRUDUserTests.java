package practice3.tests;

import org.apache.commons.lang.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.*;
import practice3.pages.CreateEditPokerPage;
import practice3.pages.LoginPage;
import practice3.pages.PlayersPage;
/**
 * Created by Liza on 03.12.2016.
 */

public class CRUDUserTests {
    private static final String USERNAME= RandomStringUtils.randomAlphabetic(8);
    private static final String EMAIL =RandomStringUtils.randomAlphabetic(5) + "@test.com";
    private static final String FIRSTNAME = RandomStringUtils.randomAlphabetic(5);
    private static final String LASTNAME= RandomStringUtils.randomAlphabetic(10);
    private static final String CITY = RandomStringUtils.randomAlphabetic(10);
    private static final String ADDRESS= RandomStringUtils.randomAlphabetic(10);
    private static final String PHONE= RandomStringUtils.randomNumeric(10);
    private static final String PASSWORD=RandomStringUtils.randomAlphanumeric(8);
    private static final int GENDER =(int)Math.random();

    protected LoginTests loginTests=new LoginTests();
    protected LoginPage loginPage = new LoginPage(loginTests.getDriver());
    protected PlayersPage playersPage;
    protected CreateEditPokerPage createEditPage;
    protected SearchTests searchTests;


    /**Precondition
     * 1.Open FireFox
     * 2.Open "http://80.92.229.236:81/auth/login" url
     * 3.Set username to "admin"
     * 4.Set password to "123"
     * 5.Click to "login" button
     */
    @BeforeTest
    public void openBrowserAndLogIn(){
        loginTests.openFireFox();
        loginTests.openWebApplication();
        loginTests.positiveLoginTest();
    }

    /**
     * 1.Click plus icon to open inserting for
     * 2.Set username to "USERNAME"
     * 3.Set email to "EMAIL"
     * 4.Set password to "PASSWORD"
     * 5.Confirm password to "PASSWORD"
     * 6.Set first name to "FIRSTNAME"
     * 7.Set last name to "LASTNAME"
     * 8.Set city to "CITY"
     * 9.Select country to "CA"
     * 10.Set address to "ADDRESS"
     * 11.Set phone to "PHONE"
     * 12.Select gender by value "GENDER"
     * 13.Click to "save" button
     * 14.Verify that title of the page equals to "Players"
     */
    @Test
    public void insertAndSavePlayer(){
        playersPage = new PlayersPage(loginTests.getDriver());
        playersPage.openByPlusIcon();
        createEditPage = new CreateEditPokerPage(loginTests.getDriver());
        createEditPage.setUsername(USERNAME);
        createEditPage.setEmail(EMAIL);
        createEditPage.setPassword(PASSWORD);
        createEditPage.confirmPassword(PASSWORD);
        createEditPage.setFirstName(FIRSTNAME);
        createEditPage.setLastName(LASTNAME);
        createEditPage.setCity(CITY);
        createEditPage.setCountry("CA");
        createEditPage.setAddress(ADDRESS);
        createEditPage.setPhone(PHONE);
        createEditPage.setGender(GENDER);
        createEditPage.save();
        Assert.assertEquals(playersPage.getTitle(),"Players","Wrong Title");
    }


    /**
     * 1.Set username to search to USERNAME
     * 2.Click to "search" button
     * 3.Click edit icon for searched user
     * 4.Set new email to EMAIL
     * 5.Set new first name to FIRSTNAME
     * 6.Set new last name to LASTNAME
     * 7.Set new city to CITY
     * 8.Set new country to "US"
     * 9.Set new address to ADDRESS
     * 10.Set new phone to PHONE
     * 11.Click to "save" button
     * 12.Verify that title of the page equals to "Players"
     */
    @Test (dependsOnMethods = "insertAndSavePlayer")
    public void editPlayerAndSave(){
        searchTests = new SearchTests();
        searchTests.searchByUserName(USERNAME);
        playersPage.openEditPage(USERNAME);
        createEditPage.setEmail(EMAIL);
        createEditPage.setFirstName(FIRSTNAME);
        createEditPage.setLastName(LASTNAME);
        createEditPage.setCity(CITY);
        createEditPage.setCountry("US");
        createEditPage.setAddress(ADDRESS);
        createEditPage.setPhone(PHONE);
        createEditPage.save();
        Assert.assertEquals(playersPage.getTitle(),"Players","Wrong Title");
    }

    /**
     * 1. Click to "reset" button
     * 2.Set username to search to USERNAME
     * 3.Click to "search" button
     * 4.Click to delete icon for searched user
     * 5.Click "ok" button at the popup window
     * 6.Verify that deleting message equal to "Player has been deleted"
     */
    @Test(dependsOnMethods = "editPlayerAndSave")
    public void deletePlayer(){
        playersPage.resetSearchFields();
        playersPage.enterUserNameToSearch(USERNAME);
        playersPage.delete(USERNAME);
        Assert.assertEquals(playersPage.getConfirmDeletingMessage(),"Player has been deleted","Wrong Message");
    }

    /**
     * Postcondition
     * 1.Close browser
     */
    @AfterTest
    public void closeBrowser(){
        loginTests.getDriver().quit();
    }
}

