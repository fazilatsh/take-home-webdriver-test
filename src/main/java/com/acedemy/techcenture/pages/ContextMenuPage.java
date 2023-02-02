package com.acedemy.techcenture.pages;

import com.acedemy.techcenture.config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

public class ContextMenuPage extends BasePage {

    public ContextMenuPage(WebDriver driver, SoftAssert softAssert){
        super(driver, softAssert);
    }

    @FindBy (id = "hot-spot")
    private WebElement contextBox;

    public void navigateToContextMenu(){
        driver.get(ConfigReader.getProperty("url")+"/context_menu");
    }

    public void clickContextBox(){
        Actions actions = new Actions(driver);
        actions.contextClick(contextBox).perform();
        driver.switchTo().alert().accept();
    }
}
