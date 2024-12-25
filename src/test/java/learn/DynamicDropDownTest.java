package learn;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

/*
this program is to handle dynamic dropdown
website used is - MakeMytrip

 */
public class DynamicDropDownTest {

    @Test(invocationCount = 1)
    public void verifyDropDown() {


        String url = "https://www.makemytrip.com";
        String closeStickerIcon= "//span[@class=\"commonModal__close\"]";

        String fromCityId ="fromCity";

        String fromLocation ="Coimbatore";

        String suggestedFromDropDown = String.format("//span[text()= '%s']/parent::span/parent::p/parent::div",fromLocation);

        WebDriver webDriver = null;
        try {

            webDriver = new ChromeDriver();
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            webDriver.manage().window().maximize();

            webDriver.get(url);

            WebDriverWait webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
            WebElement closeSticker = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(closeStickerIcon)));
            closeSticker.click();


            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(fromCityId)));

            JavascriptExecutor js = (JavascriptExecutor) webDriver;
            js.executeScript("arguments[0].removeAttribute('readonly');", webDriver.findElement(By.id("fromCity")));


            webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id(fromCityId))).click();
            webDriver.findElement(By.id(fromCityId)).sendKeys(fromLocation);

//            WebElement suggestedFrom = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(suggestedFromDropDown)));
//            suggestedFrom.click();
//
//            String value = webDriver.findElement(By.id(fromCityId)).getDomProperty("value");
//            System.out.println(value);

            Thread.sleep(5000);


        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            webDriver.close();
            webDriver.quit();
        }




    }
}
