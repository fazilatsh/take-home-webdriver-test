package com.acedemy.techcenture.pages;

import com.acedemy.techcenture.config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;

public class DynamicContent extends BasePage{
    public DynamicContent(WebDriver driver, SoftAssert softAssert){
        super(driver, softAssert);
    }

    @FindBy(id="content")
    private WebElement dynamicContent;

    public void navigateToDynamic(){
        driver.get(ConfigReader.getProperty("url")+"/dynamic_content");
    }

    public void checkContentChanges() throws InterruptedException {
        ConfigReader.setProperty("originalContent", dynamicContent.getText());
        Thread.sleep(2000);
        driver.navigate().refresh();
        Thread.sleep(2000);
        softAssert.assertTrue(!ConfigReader.getProperty("originalContent").equalsIgnoreCase(dynamicContent.getText()), "Text didn't change" );
    }
}
