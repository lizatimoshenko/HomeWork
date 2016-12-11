package actionsTest;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



/**
 * Created by Liza on 11.12.2016.
 */
public class Page {

    @FindBy(id = "drop")
    private WebElement trash;

    @FindBy(id = "sortable")
    private WebElement sortableElement;


    private WebDriver driver;
    private Alert alert;
    private final static String URL = "file:///C:/Users/Liza/Desktop/drag_and_drop2/index.html";

    public Page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get(URL);
    }

    public void dragAndDrop(int number) {
        Actions action = new Actions(driver);
        action.dragAndDrop(sortableElement.findElement(By.xpath("./li[text()='" + number + "']")), trash).perform();
    }


    public String getAlertMessage() {
        alert = driver.switchTo().alert();
        return alert.getText();
    }

    public void cancelDeleting() {
        alert = driver.switchTo().alert();
        alert.dismiss();
    }


    public Boolean checkElement(int number) {
        if (isElementPresent(By.xpath("//*[text()='" + number + "']")) == true) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void acceptDeleting() {
        alert = driver.switchTo().alert();
        alert.accept();
    }

    public void ascSorting() {
        Actions action = new Actions(driver);
        for (int i=7;i>1;i--){
            for (int j = 1; j < i; j++) {
                WebElement firstElement=driver.findElement(By.xpath(".//*[@id='sortable']/li["+(j)+"]"));
                WebElement secondElement=driver.findElement(By.xpath(".//*[@id='sortable']/li["+(j+1)+"]"));
                if ( Integer.parseInt(firstElement.getText())> Integer.parseInt(secondElement.getText())) {
                    action.dragAndDrop(secondElement,firstElement).perform();

                }
            }
        }
    }

    public void descSorting() {
        Actions action = new Actions(driver);
        for (int i=0;i<6;i++){
            for (int j =1; j <7-i; j++) {
                WebElement firstElement=driver.findElement(By.xpath(".//*[@id='sortable']/li["+(j)+"]"));
                WebElement secondElement=driver.findElement(By.xpath(".//*[@id='sortable']/li["+(j+1)+"]"));
                if ( Integer.parseInt(firstElement.getText())< Integer.parseInt(secondElement.getText())) {
                    action.dragAndDrop(secondElement,firstElement).perform();

                }
            }
        }
    }

    public Boolean checkSortedElementASC(int number) {
        String xpath = ".//*[@id='sortable']/li["+number+"]";
        if ((isElementPresent(By.xpath(xpath)) == true) && (Integer.parseInt(driver.findElement(By.xpath(xpath)).getText())==number))  {
            return true;
        } else {
            return false;
        }
    }

    public Boolean checkSortedElementDESC(int number,int value) {
        String xpath = ".//*[@id='sortable']/li["+number+"]";
        if ((isElementPresent(By.xpath(xpath)) == true) && (Integer.parseInt(driver.findElement(By.xpath(xpath)).getText())==value))  {
            return true;
        } else {
            return false;
        }
    }
}
