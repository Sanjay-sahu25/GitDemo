package org.example;

import org.example.TestComponents.BaseTest;
import org.example.TestComponents.Retry;
import org.example.pageobjects.CartPage;
import org.example.pageobjects.ProductCatalogue;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class ErrorValidationsTest extends BaseTest{
    @Test(groups = {"ErrorHandling"},retryAnalyzer = Retry.class)
    public void LoginErrorValidation() throws IOException, InterruptedException {
        String productName = "ZARA COAT 3";
        landingPage.LoginApplication("sanj.goud@gmail.com","batman@25");
        //div[@aria-label='Incorrect email or password.']
        Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMessage());

    }

    @Test
    public void ProductErrorValidation() throws IOException, InterruptedException {
        String productName = "ZARA COAT 3";
        ProductCatalogue productCatalogue = landingPage.LoginApplication("sanjay.goud@gmail.com","Batman@25");
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);
        CartPage cartPage = productCatalogue.goToCartPage();

        Boolean match = cartPage.VerifyItem("ZARA COAT 33");
        Assert.assertFalse(match);

    }
}



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