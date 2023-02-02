package com.acedemy.techcenture.pages;

import com.acedemy.techcenture.config.ConfigReader;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class DragAndDropPage extends BasePage{
    public DragAndDropPage(WebDriver driver, SoftAssert softAssert){
        super(driver, softAssert);
    }
    @FindBy (id = "column-a")
    private WebElement boxA;

    @FindBy (id = "column-b")
    private WebElement boxB;

    public void dragAndDrop () throws InterruptedException {
        softAssert.assertTrue(boxA.isDisplayed());
        softAssert.assertTrue(boxB.isDisplayed());

        int x = boxB.getLocation().x;
        int y = boxB.getLocation().y;

//        Actions actions = new Actions(driver);
//        actions.moveToElement(boxA)
//                .pause(Duration.ofSeconds(3))
//                .clickAndHold(boxA)
//                .pause(Duration.ofSeconds(3))
//                .moveByOffset(x, y)
//                .moveToElement(boxB)
//                .moveByOffset(x,y)
//                .pause(Duration.ofSeconds(1))
//                .release().build().perform();
        Actions actions = new Actions(driver);
        Thread.sleep(3000);
//        actions.clickAndHold(boxA).moveToElement(boxB).release(boxB).build().perform();
//        Thread.sleep(3000);

        actions.dragAndDrop(boxA, boxB).perform();
        Thread.sleep(3000);
        softAssert.assertTrue(boxA.getText().equals("B"));
        softAssert.assertTrue(boxB.getText().equals("A"));
    }

    public void navigateToDragPage(){
        driver.get(ConfigReader.getProperty("url")+"/drag_and_drop");
    }
}
