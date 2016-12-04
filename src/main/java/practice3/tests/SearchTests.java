package practice3.tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import practice3.pages.PlayersPage;

/**
 * Created by Liza on 03.12.2016.
 */
public class SearchTests {
    protected LoginTests loginTests=new LoginTests();
    protected PlayersPage playersPage = new PlayersPage(loginTests.getDriver());

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
        playersPage = new PlayersPage(loginTests.getDriver());
    }

    /**
     *This test is for CRUDUserTests class
     * 1.Set username to search to USERNAME
     * 2.Click to "search" button
     */
    @Test
    public void searchByUserName(String value){
        playersPage.enterUserNameToSearch(value);
        playersPage.search();
    }

    /**
     * 1.Set email to search to "player142@test.com"
     * 2.Click to "search" button
     */
    @Test
    public void searchByEmail(){
        String email = "player142@test.com";
        playersPage.enterEmailToSearch(email);
        playersPage.search();
    }

    /**
     * 1.Set username to search to "user100"
     * 2.Click to "search" button
     */
    @Test
    public void searchByUserName(){
        String username = "user100";
        playersPage.enterUserNameToSearch(username);
        playersPage.search();
    }
    /**
     * 1.Select player balance value to "251-500"
     * 2.Click to "search" button
     */
    @Test
    public void searchByBalance(){
        String balance = "251-500";
        playersPage.selectBalance(balance);
        playersPage.search();
    }


}
