package actionsTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

/**
 * Created by Liza on 11.12.2016.
 */
public class Test {

    private WebDriver driver;
    private Page page;
    private SoftAssert softAssert;

    private static final int number =4;

    @BeforeTest
    public void beforeTest() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        page = new Page(driver);
        softAssert = new SoftAssert();
        page.open();

    }

    @org.testng.annotations.Test
    public void dragAndDropTest() {
        page.dragAndDrop(number);
        softAssert.assertEquals(page.getAlertMessage(),"Are you sure that you want to delete?","Actual message doesn't match to expected");
        page.cancelDeleting();
        softAssert.assertEquals(page.checkElement(number),Boolean.TRUE,"Element does't find");
        page.dragAndDrop(number);
        softAssert.assertEquals(page.getAlertMessage(),"Are you sure that you want to delete?","Actual message doesn't match to expected");
        page.acceptDeleting();
        softAssert.assertEquals(page.checkElement(number),Boolean.FALSE,"Element doesn't delete ");
        softAssert.assertAll();
    }

   @org.testng.annotations.Test
    public void ascSorting(){
       page.ascSorting();
       for (int i=1;i<7;i++){
           softAssert.assertEquals(page.checkSortedElementASC(i),Boolean.TRUE,"Element "+i+" isn't sorted right");

       }
       softAssert.assertAll();

    }
   @org.testng.annotations.Test
    public void descSorting(){
       page.descSorting();
       int j=1;
       for (int i=7;i>1;i--){
           softAssert.assertEquals(page.checkSortedElementDESC(i,j),Boolean.TRUE,"Element "+i+" isn't sorted right");
           j++;
       }
       softAssert.assertAll();
    }


    @AfterTest
    public void closeFireFox(){
       driver.quit();
    }

}
