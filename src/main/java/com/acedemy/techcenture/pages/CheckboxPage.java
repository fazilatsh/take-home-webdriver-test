package com.acedemy.techcenture.pages;

import com.acedemy.techcenture.config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

public class CheckboxPage extends BasePage {
    public CheckboxPage(WebDriver driver, SoftAssert softAssert){
        super(driver,softAssert);
    }
    @FindBy (xpath = "//input[@type='checkbox'][1]")
    private WebElement checkboxOne;

    @FindBy(xpath = "//input[@type='checkbox'][2]")
    private WebElement checkboxTwo;

    public void navigateToCheckboxes(){
        driver.get(ConfigReader.getProperty("url")+"/checkboxes");
    }

    public void checkCheckboxes(){
        softAssert.assertTrue(checkboxOne.isEnabled());
        softAssert.assertTrue(checkboxTwo.isEnabled());
        checkboxTwo.click();
        checkboxOne.click();
        checkboxTwo.click();
    }

    public void uncheckCheckboxes(){
        softAssert.assertTrue(checkboxOne.isSelected());
        softAssert.assertTrue(checkboxTwo.isSelected());
        checkboxOne.click();
        checkboxTwo.click();
    }

}
