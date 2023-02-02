package com.acedemy.techcenture.pages;

import com.acedemy.techcenture.config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;

public class LoginPage extends BasePage {

    @FindBy(id = "username")
    private WebElement userName;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(xpath = "//button[@class='radius']")
    private WebElement loginBtn;

    @FindBy(xpath = "//a[@href='/logout']")
    private WebElement logOutBtn;

    @FindBy(id = "flash")
    private WebElement incorrectLoginMsg;

    public LoginPage(WebDriver driver, SoftAssert softAssert) {
        super(driver, softAssert);
    }

    public void navigateToLoginPage() {
        driver.get(ConfigReader.getProperty("url") + "/login");
    }

    public void loginPositive() {
        userName.clear();
        userName.sendKeys(ConfigReader.getProperty("username"));
        password.clear();
        password.sendKeys(ConfigReader.getProperty("password"));
        softAssert.assertTrue(loginBtn.isEnabled(), "Login button isn't enabled");
        loginBtn.click();
        logOutBtn.click();
    }

    public void loginNegative() {
        userName.clear();
        userName.sendKeys("incorrectUsername");
        password.clear();
        password.sendKeys("incorrectPassword");
        softAssert.assertTrue(loginBtn.isEnabled(), "Login button isn't enabled");
        loginBtn.click();
        softAssert.assertTrue(incorrectLoginMsg.isDisplayed());
    }
}
