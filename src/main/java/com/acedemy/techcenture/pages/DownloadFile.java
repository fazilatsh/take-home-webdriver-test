package com.acedemy.techcenture.pages;

import com.acedemy.techcenture.config.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class DownloadFile extends BasePage{
    public DownloadFile(WebDriver driver, SoftAssert softAssert){
        super(driver, softAssert);
    }

    @FindBy (xpath = "//a[@href='download/some-file.txt']")
    private WebElement fileLink;

    public void navigateToDownload(){
        driver.get(ConfigReader.getProperty("url")+"/download");
    }
    public void verifyDownload(){
        softAssert.assertTrue(fileLink.isEnabled());
        fileLink.click();
        String path = "/Users/fazilat/Downloads";
        File folder = new File(path);
//List the files on that folder
        File[] listOfFiles = folder.listFiles();
        boolean found = false;
        //Look for the file in the files
        // You should write smart REGEX according to the filename
        for (File listOfFile : listOfFiles) {
            if (listOfFile.isFile()) {
                String fileName = listOfFile.getName();
                System.out.println("File " + listOfFile.getName());
                if (fileName.matches("some-file.txt")) {
                    new File(fileName);
                    found = true;
                }
            }
        }
        softAssert.assertTrue(found, "Downloaded document is not found");


}
}
