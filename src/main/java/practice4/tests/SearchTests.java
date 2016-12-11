package practice4.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import practice4.pages.PlayersPage;

/**
 * Created by Liza on 03.12.2016.
 */
public class SearchTests extends BaseTest{
    protected PlayersPage playersPage;

    /**Precondition
     * 1.Open FireFox
     * 2.Open "http://80.92.229.236:81/auth/login" url
     * 3.Set username to "admin"
     * 4.Set password to "123"
     * 5.Click to "login" button
     */
   @BeforeTest
    public void openFireFox(){
      super.openFireFox();
       playersPage = new PlayersPage(super.getDriver());
    }



    /**
     * 1.Set email to search to "player142@test.com"
     * 2.Click to "search" button
     */
    @DataProvider(name = "emails")
    public Object[][] emailsForSearch(){
        return new Object[][]{
                {"player194@test.com",true,"User didn't find"},
                {"hihihihih194@test.com",true,"User didn't find"}
        };

    }
    @Test (dataProvider = "emails")
    public void searchByEmail(String email,Boolean isFind,String errorMessage){
        playersPage.resetSearchFields();
        playersPage.enterEmailToSearch(email);
        playersPage.search();
        Assert.assertEquals(playersPage.findPlayerInTheTable(email),isFind,errorMessage);
    }

    /**
     * 1.Set username to search to "user100"
     * 2.Click to "search" button
     */
    @Parameters({"username"})
    @Test
    public void searchByUserName(String username){
        playersPage.resetSearchFields();
        playersPage.enterUserNameToSearch(username);
        playersPage.search();
        Assert.assertEquals(playersPage.findPlayerInTheTable(username),Boolean.TRUE,"User didn't find");
    }

}
