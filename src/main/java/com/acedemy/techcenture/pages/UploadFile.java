package com.acedemy.techcenture.pages;

import com.acedemy.techcenture.config.ConfigReader;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.time.Duration;

public class UploadFile extends BasePage {
    public UploadFile(WebDriver driver, SoftAssert softAssert){
        super(driver, softAssert);
    }
    @FindBy(id = "file-upload")
    private WebElement chooseFileBtn;

    @FindBy(id = "file-submit")
    private WebElement uploadBtn;

    @FindBy(id = "drag-drop-upload")
    private WebElement dragBox;

    String filepath = "/Users/fazilat/Downloads/some-file.txt";

    public void navigateToUpload(){
        driver.get(ConfigReader.getProperty("url")+"/upload");
    }

    public void uploadFile(){
        softAssert.assertTrue(chooseFileBtn.isEnabled());
        softAssert.assertTrue(uploadBtn.isEnabled());
        String filepath = "/Users/fazilat/Downloads/some-file.txt";
        chooseFileBtn.sendKeys(filepath);
        uploadBtn.click();
    }

    public void dragAndDropFile(){
        softAssert.assertTrue(dragBox.isDisplayed());
        DropFile(new File("/Users/fazilat/Downloads/some-file.txt"), dragBox, 0, 0);
    }

    public static void DropFile(File filePath, WebElement target, int offsetX, int offsetY) {
        if(!filePath.exists())
            throw new WebDriverException("File not found: " + filePath.toString());

        WebDriver driver = ((org.openqa.selenium.remote.RemoteWebElement)target).getWrappedDriver();
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        String JS_DROP_FILE =
                "var target = arguments[0]," +
                        "    offsetX = arguments[1]," +
                        "    offsetY = arguments[2]," +
                        "    document = target.ownerDocument || document," +
                        "    window = document.defaultView || window;" +
                        "" +
                        "var input = document.createElement('INPUT');" +
                        "input.type = 'file';" +
                        "input.style.display = 'none';" +
                        "input.onchange = function () {" +
                        "  var rect = target.getBoundingClientRect()," +
                        "      x = rect.left + (offsetX || (rect.width >> 1))," +
                        "      y = rect.top + (offsetY || (rect.height >> 1))," +
                        "      dataTransfer = { files: this.files };" +
                        "" +
                        "  ['dragenter', 'dragover', 'drop'].forEach(function (name) {" +
                        "    var evt = document.createEvent('MouseEvent');" +
                        "    evt.initMouseEvent(name, !0, !0, window, 0, 0, 0, x, y, !1, !1, !1, !1, 0, null);" +
                        "    evt.dataTransfer = dataTransfer;" +
                        "    target.dispatchEvent(evt);" +
                        "  });" +
                        "" +
                        "  setTimeout(function () { document.body.removeChild(input); }, 25);" +
                        "};" +
                        "document.body.appendChild(input);" +
                        "return input;";

        WebElement input =  (WebElement)jse.executeScript(JS_DROP_FILE, target, offsetX, offsetY);
        input.sendKeys(filePath.getAbsoluteFile().toString());
        wait.until(ExpectedConditions.stalenessOf(input));
    }
}
