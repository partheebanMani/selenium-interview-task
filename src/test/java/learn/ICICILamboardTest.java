package learn;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;


/*
*
* Interview question to select tabs in icicilombok website
* Finding correct xpath is important here.
* This xpath uses both descendant and parent
* descendant::span  - finds all the descendant span for class tabsectionBox
* parent::div - span which has parent as div
* [1] - finds first span . Div may contain multiple span. we need first span alone.
 */

public class ICICILamboardTest {

    static String url = "https://www.icicilombard.com/";

    @Test
    public  void testcase()  {

        String xpathForTabs = "//*[@class='tabSectionBox']/li/descendant::span[parent::div][1]";

        WebDriver webDriver =null;
        try {
            webDriver = new ChromeDriver();

            webDriver.get(url);

            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

            List<WebElement> tabSectionBox = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpathForTabs)));

            System.out.println(tabSectionBox.size());

            for (WebElement webElement : tabSectionBox) {
                System.out.println(webElement.getText());
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            webDriver.close();
            webDriver.quit();

        }


    }
}
