package practice4.tests;

import org.apache.commons.lang.RandomStringUtils;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import practice4.pages.CreateEditPokerPage;
import practice4.pages.PlayersPage;

/**
 * Created by Liza on 03.12.2016.
 */

public class CRUDUserTests extends BaseTest{
    private static final String USERNAME= RandomStringUtils.randomAlphabetic(8);
    private static final String EMAIL =RandomStringUtils.randomAlphabetic(5) + "@test.com";
    private static final String FIRSTNAME = RandomStringUtils.randomAlphabetic(5);
    private static final String LASTNAME= RandomStringUtils.randomAlphabetic(10);
    private static final String CITY = RandomStringUtils.randomAlphabetic(10);
    private static final String ADDRESS= RandomStringUtils.randomAlphabetic(10);
    private static final String PHONE= RandomStringUtils.randomNumeric(10);
    private static final String PASSWORD=RandomStringUtils.randomAlphanumeric(8);
    private static final int GENDER =(int)Math.random();

    protected PlayersPage playersPage;
    protected CreateEditPokerPage createEditPage;
    private SoftAssert softAssert;

    @BeforeTest
    protected void openFireFox(){
        super.openFireFox();
        softAssert=new SoftAssert();
    }


    /**
     * Step to reproduce
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
    @DataProvider(name = "insertPlayerData")
    public Object[][] insertPlayerData(){
        return new Object[][]{
                {USERNAME,EMAIL,PASSWORD,FIRSTNAME,LASTNAME,CITY,"CA",ADDRESS,PHONE,GENDER},

        };

    }
    @Test(dataProvider = "insertPlayerData")
    public void insertAndSavePlayer(String userName,String email,String password,String firstName,String lastName,String city, String country, String address,String phone, int gender){
        playersPage = new PlayersPage(super.getDriver());
        playersPage.openByPlusIcon();
        createEditPage = new CreateEditPokerPage(super.getDriver());
        createEditPage.setUsername(userName);
        createEditPage.setEmail(email);
        createEditPage.setPassword(password);
        createEditPage.confirmPassword(password);
        createEditPage.setFirstName(firstName);
        createEditPage.setLastName(lastName);
        createEditPage.setCity(city);
        createEditPage.setCountry(country);
        createEditPage.setAddress(address);
        createEditPage.setPhone(phone);
        createEditPage.setGender(gender);
        createEditPage.save();
        softAssert.assertEquals(playersPage.getTitle(),"Players","Wrong Title");

    }

    /**
     * Step to reproduce
     * 1.Set username to search to USERNAME
     * 2.Click to search button
     * 3.Click edit icon for searched user
     * 4.Verify that actual userName match to expected
     * 5.Verify that actual email match to expected
     * 6.Verify that actual firstName match to expected
     * 7.Verify that actual lastName match to expected
     * 8.Verify that actual city match to expected
     * 9.Verify that actual country match to expected
     * 10.Verify that actual address match to expected
     * 11.Verify that actual phone match to expected
     * 12.Verify that actual gender match to expected
     * 13.Click to save button
     * 14.Verify that title of the page equals to "Players"
     * @param userName
     * @param email
     * @param password
     * @param firstName
     * @param lastName
     * @param city
     * @param country
     * @param address
     * @param phone
     * @param gender
     */
    @Test(dataProvider = "insertPlayerData", dependsOnMethods = "insertAndSavePlayer")
    public void checkInsertedPlayer(String userName, String email, @Optional String password, String firstName, String lastName, String city, String country, String address, String phone, int gender){
        playersPage.enterUserNameToSearch(userName);
        playersPage.search();
        playersPage.openEditPage(userName);
        softAssert.assertEquals(createEditPage.getUsername(),userName,"Actual username doesn't match to expected\n");
        softAssert.assertEquals(createEditPage.getEmail(),email,"Actual email doesn't match to expected\n");
        softAssert.assertEquals(createEditPage.getFirstName(),firstName,"Actual firstName doesn't match to expected\n");
        softAssert.assertEquals(createEditPage.getLastName(),lastName,"Actual lastName doesn't match to expected\n");
        softAssert.assertEquals(createEditPage.getCity(),city,"Actual city doesn't match to expected\n");
        softAssert.assertEquals(createEditPage.getCountry(),country,"Actual country doesn't match to expected\n");
        softAssert.assertEquals(createEditPage.getAddress(),address,"Actual address doesn't match to expected\n");
        softAssert.assertEquals(createEditPage.getPhone(),phone,"Actual phone doesn't match to expected\n");
        softAssert.assertEquals(createEditPage.getGender(),gender,"Actual gender doesn't match to expected\n");
        createEditPage.save();
        softAssert.assertEquals(playersPage.getTitle(),"Players","Wrong Title");
        softAssert.assertAll();
    }


    /**
     * Step to reproduce
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
    @DataProvider(name = "editPlayerData")
    public Object[][] editPlayerData(){
        return new Object[][]{
                {EMAIL,FIRSTNAME,LASTNAME,CITY,"US",ADDRESS,PHONE},

        };

    }
    @Test (dataProvider ="editPlayerData", dependsOnMethods = "checkInsertedPlayer", alwaysRun = true)
    public void editPlayerAndSave(String email,String firstName,String lastName,String city, String country, String address,String phone){
        playersPage.enterUserNameToSearch(USERNAME);
        playersPage.search();
        playersPage.openEditPage(USERNAME);
        createEditPage.setEmail(email);
        createEditPage.setFirstName(firstName);
        createEditPage.setLastName(lastName);
        createEditPage.setCity(city);
        createEditPage.setCountry(country);
        createEditPage.setAddress(address);
        createEditPage.setPhone(phone);
        createEditPage.save();
        softAssert.assertEquals(playersPage.getTitle(),"Players","Wrong Title");

    }

    /**
     * Step to reproduce
     * 1. Click to "reset" button
     * 2.Set username to search to USERNAME
     * 3.Click to "search" button
     * 4.Click to delete icon for searched user
     * 5.Click "ok" button at the popup window
     * 6.Verify that deleting message equal to "Player has been deleted"
     */
    @Test(dependsOnMethods = "editPlayerAndSave", alwaysRun = true)
    public void deletePlayer(){
        playersPage.resetSearchFields();
        playersPage.enterUserNameToSearch(USERNAME);
        playersPage.delete(USERNAME);
        softAssert.assertEquals(playersPage.getConfirmDeletingMessage(),"Player has been deleted","Wrong Message");
    }

    /**
     * Step to reproduce
     * 1.Click to reset button
     * 2.Set username to search to USERNAME
     * 3.Click to "search" button
     * 4.Verify that player was really deleted
     *
     */
    @Test(dependsOnMethods = "deletePlayer", alwaysRun = true)
    public void checkDeletedPlayer(){
        playersPage.resetSearchFields();
        playersPage.enterUserNameToSearch(USERNAME);
        softAssert.assertEquals(playersPage.findPlayerInTheTable(USERNAME),Boolean.FALSE,"User still exists");
    }
}

