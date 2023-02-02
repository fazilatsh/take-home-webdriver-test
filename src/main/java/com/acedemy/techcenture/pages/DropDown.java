package com.acedemy.techcenture.pages;

import com.acedemy.techcenture.config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

public class DropDown extends BasePage{

    public DropDown (WebDriver driver, SoftAssert softAssert){
        super(driver, softAssert);
    }
    @FindBy(id = "dropdown")
    private WebElement dropdown;

    public void selectDropDown(){
        softAssert.assertTrue(dropdown.isEnabled());
        Select select = new Select(dropdown);
        select.selectByIndex(1);
    }

    public void navigateToDropdown(){
        driver.get(ConfigReader.getProperty("url")+"/dropdown");
    }
}
