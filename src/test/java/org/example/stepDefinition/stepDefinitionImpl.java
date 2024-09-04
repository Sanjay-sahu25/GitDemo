package org.example.stepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.TestComponents.BaseTest;
import org.example.pageobjects.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class stepDefinitionImpl extends BaseTest {
    public LandingPage landingPage;
    public ProductCatalogue productCatalogue;
    public ConfirmationPage confirmationPage;
    @Given("I landed on Ecommerce Page")
    public void I_landed_on_Ecommerce_Page() throws IOException {
       landingPage = launchApplication();
    }
    @Given("^Logged in with username (.+) and password (.+)$")
    public void Logged_in_username_and_password(String username, String password)
    {
        productCatalogue  = landingPage.LoginApplication(username, password);
    }
    @When("^I add product (.+) to Cart$")
    public void i_add_product_to_Cart(String productName)
    {
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);
    }
    @When("^Checkout (.+) and submit the order$")
    public void checkout_submit_order(String productName) throws InterruptedException {
        CartPage cartPage = productCatalogue.goToCartPage();

        Boolean match = cartPage.VerifyItem(productName);
        Assert.assertTrue(match);
        cartPage.scrollDown();
        Thread.sleep(2000);
        CheckOutPage checkOutPage = cartPage.checkOut();
        String countryName = "India";
        checkOutPage.selectCountry(countryName);
        confirmationPage = checkOutPage.submitOrder();
    }
    @Then("{string} message is displayed on ConfirmationPage")
    public void message_displayed_ConfirmationPage(String string)
    {
        String confirmMessage = confirmationPage.getConfirmationPage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
    }
    @Then("{string} message is displayed")
    public void message_is_displayed(String strAg1) {
        // Find the element displaying the error message and assert its text
        Assert.assertEquals(strAg1,landingPage.getErrorMessage());

        // Clean up
        driver.quit();
    }

}
