package org.example.pageobjects;

import org.example.AbstractComponets.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponent {
    WebDriver driver;

    public LandingPage(WebDriver driver)
    {
        super(driver);
        //initialization
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    //WebElement userEmail = driver.findElement(By.id("userEmail"));
    //PageFactory
    @FindBy(id ="userEmail")
    WebElement userEmail;

    @FindBy(id = "userPassword")
    WebElement passwordEle;

    @FindBy(id = "login")
    WebElement submit;

    @FindBy(css = "[class*='flyInOut']")
    WebElement errorMessage;

    public  ProductCatalogue LoginApplication(String email, String password)
    {
        userEmail.sendKeys(email);
        passwordEle.sendKeys(password);
        submit.click();

        return new ProductCatalogue(driver);

    }

    public String getErrorMessage()
    {
        waitForWebElementToAppear(errorMessage);
        return errorMessage.getText();

    }

    public  void goTO()
    {
        driver.get("https://rahulshettyacademy.com/client");
    }

}
