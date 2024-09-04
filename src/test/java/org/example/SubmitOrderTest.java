package org.example;

import org.example.TestComponents.BaseTest;
import org.example.pageobjects.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class    SubmitOrderTest extends BaseTest{
    String productName = "ZARA COAT 3";
    @Test(dataProvider = "getData",groups = {"Purchase"})
    public void SubmitOrder(HashMap<String,String> input) throws IOException, InterruptedException {

        ProductCatalogue productCatalogue = landingPage.LoginApplication(input.get("email"),input.get("password"));
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(input.get("productName"));
        CartPage cartPage = productCatalogue.goToCartPage();

        Boolean match = cartPage.VerifyItem(input.get("productName"));
        Assert.assertTrue(match);
        cartPage.scrollDown();
        Thread.sleep(2000);
        CheckOutPage checkOutPage = cartPage.checkOut();
        String countryName = "India";
        checkOutPage.selectCountry(countryName);
        ConfirmationPage confirmationPage = checkOutPage.submitOrder();

        String confirmMessage = confirmationPage.getConfirmationPage();
      

        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

    }
    //To verify ZARA COAT 3 is displaying in order page
    @Test(dependsOnMethods = {"SubmitOrder"})
    public void OrderHistoryTest()
    {
        ProductCatalogue productCatalogue = landingPage.LoginApplication("sanjay.goud@gmail.com","Batman@25");
        OrderPage ordersPage = productCatalogue.goToOrdersPage();
        Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));

    }

    @DataProvider
    public Object[][] getData() throws IOException {
//        HashMap<String,String> map = new HashMap<String,String>();
//        map.put("email","sanjay.goud@gmail.com");
//        map.put("password","Batman@25");
//        map.put("productName","ZARA COAT 3");
//
//        HashMap<String,String> map1 = new HashMap<String,String>();
//        map1.put("email","sanjay@gmail.com");
//        map1.put("password","Ironman@25");
//        map1.put("productName","ADIDAS ORIGINAL");
        List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//org//example//TestData//PurchaseOrder.json");
        return new Object[][] {{data.get(0)},{data.get(1)}};

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