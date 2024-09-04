package org.example.pageobjects;

import org.example.AbstractComponets.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderPage extends AbstractComponent     {
    WebDriver driver;
    public OrderPage(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(css =".tr td:nth-child(3)")
    private List<WebElement> productNames;

    @FindBy(xpath = "//li[@class='totalRow']/button")
    WebElement checkOut;


    public Boolean VerifyOrderDisplay(String productName)
    {
        Boolean match = productNames.stream().anyMatch(listItem-> listItem.getText().equals(productName));
        return match;
    }




}
