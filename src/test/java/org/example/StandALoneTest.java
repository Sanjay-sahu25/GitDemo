package org.example;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.bytebuddy.pool.TypePool;
import org.example.pageobjects.LandingPage;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import javax.swing.*;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;

public class StandALoneTest {
    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://rahulshettyacademy.com/client");
        LandingPage LandingPage = new LandingPage(driver);

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
        String productName = "ZARA COAT 3";
        List<WebElement> products =  driver.findElements(By.cssSelector(".mb-3"));
        /*
        for(int i=0;i<products.size();i++)
        {
            if(products.get(i).findElement(By.cssSelector("b")).getText().equalsIgnoreCase("ZARA COAT 3"))
            {
                driver.findElements(By.cssSelector("button[class$='btn w-10 rounded']")).get(i).click();
                break;
            }
        }
        */
       WebElement prod = products.stream().filter(product-> product.findElement(By.cssSelector("b"))
                .getText().equals(productName)).findFirst().orElse(null);
       prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
        List<WebElement> listItems =  driver.findElements(By.cssSelector(".items"));
       Boolean match = listItems.stream().anyMatch(listItem-> listItem.findElement(By.cssSelector("h3")).getText().equals(productName));
        Assert.assertTrue(match);

        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,500)");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//li[@class='totalRow']/button")).click();
        String countryName = "India";
        Actions a = new Actions(driver);
        a.sendKeys(driver.findElement(By.cssSelector("[placeholder*='Select Country']")),countryName).build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
        driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
      
        String confirmedmsg = driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertTrue(confirmedmsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
        driver.close();







    }
}
