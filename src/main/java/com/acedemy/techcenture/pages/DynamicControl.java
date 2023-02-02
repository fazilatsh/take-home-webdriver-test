package com.acedemy.techcenture.pages;

import com.acedemy.techcenture.config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class DynamicControl extends BasePage {
    public DynamicControl(WebDriver driver, SoftAssert softAssert) {
        super(driver, softAssert);
    }

    @FindBy(xpath = "//button[@onclick='swapCheckbox()']")
    private WebElement removeBtn;

    @FindBy(xpath = "//button[@onclick='swapInput()']")
    private WebElement enableBtn;

    @FindBy(id = "message")
    private WebElement successMsg;

    public void navigateToDynamicControls() {
        driver.get(ConfigReader.getProperty("url") + "/dynamic_controls");
    }

    public void checkRemoveFunction() {
        softAssert.assertTrue(removeBtn.isEnabled());
        removeBtn.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));
        softAssert.assertTrue(successMsg.isDisplayed());
    }

    public void checkEnableFunction() {
        softAssert.assertTrue(enableBtn.isEnabled());
        enableBtn.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));
        softAssert.assertTrue(successMsg.isDisplayed());
    }
}
