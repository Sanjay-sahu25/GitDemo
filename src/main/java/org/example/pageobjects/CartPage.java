package org.example.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage {
    WebDriver driver;
    public CartPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(css =".items")
    private List<WebElement> items;

    @FindBy(xpath = "//li[@class='totalRow']/button")
    WebElement checkOut;


    public Boolean VerifyItem(String productName)
    {
        Boolean match = items.stream().anyMatch(listItem-> listItem.findElement(By.cssSelector("h3")).getText().equals(productName));
        return match;
    }
    public void scrollDown()
    {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,500)");
    }
    public CheckOutPage checkOut()
    {
        checkOut.click();

        return new CheckOutPage(driver);
    }



}
