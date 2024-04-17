package com.saucedemo.stepdefinitions;

import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.CheckoutPage;
import com.saucedemo.pages.InventoryPage;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.utilities.ConfigurationReader;
import com.saucedemo.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

public class BuyAnyItemStepDefs {

    LoginPage loginPage = new LoginPage();
    InventoryPage inventoryPage = new InventoryPage();
    CheckoutPage checkoutPage = new CheckoutPage();

    @Given("the user navigates to the www.saucedemo.com")
    public void the_user_navigates_to_the_www_saucedemo_com() {

        // This step occurs in @BeforeMethod that belongs Hooks Class
    }

    @Given("the user enters username")
    public void the_user_enters_username() {

        loginPage.usernameBox.sendKeys(ConfigurationReader.get("username"));
    }

    @Given("the user enters password")
    public void the_user_enters_password() {

        loginPage.passwordBox.sendKeys(ConfigurationReader.get("password"));
    }

    @When("the user clicks login button")
    public void the_user_clicks_login_button() {

        loginPage.loginBtn.click();
    }

    @Then("the user logins and lands on {string} page successfully")
    public void the_user_logins_and_lands_on_page_successfully(String expectedUrl) {

        String actualUrl = Driver.get().getCurrentUrl();
        Assert.assertEquals("The url does NOT match", expectedUrl, actualUrl);
    }

    @Given("the user clicks any add to cart button")
    public void the_user_clicks_any_add_to_cart_button() {

        Random rn = new Random();
        int anyItem = rn.nextInt(inventoryPage.addToCartBtn.size());

        WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(inventoryPage.cartIcon));
        inventoryPage.addToCartBtn.get(anyItem).click();
    }

    @Given("the user clicks cart icon")
    public void the_user_clicks_cart_icon() {

        inventoryPage.cartIcon.click();
    }

    @When("the user clicks checkout button")
    public void the_user_clicks_checkout_button() {

        new CartPage().checkoutBtn.click();
    }

    @When("the user enters {string} to the first name box")
    public void the_user_enters_to_the_first_name_box(String firstName) {

        checkoutPage.firstNameBox.sendKeys(firstName);
    }

    @When("the user enters {string} to the last name box")
    public void the_user_enters_to_the_last_name_box(String lastName) {

        checkoutPage.lastNameBox.sendKeys(lastName);
    }

    @When("the user enters {string} to the postal code box")
    public void the_user_enters_to_the_postal_code_box(String zipCode) {

        checkoutPage.postalCodeBox.sendKeys(zipCode);
    }

    @When("the user clicks continue button")
    public void the_user_clicks_continue_button() {

        checkoutPage.continueBtn.click();
    }

    @When("the user clicks finish button")
    public void the_user_clicks_finish_button() {

        checkoutPage.finishBtn.click();
    }

    @Then("the user buys any item and see the following message")
    public void the_user_buys_any_item_and_see_the_following_message(String expectedMsg) {

        String actualMsg = checkoutPage.checkoutMessage.getText().trim();
        Assert.assertEquals("The user does NOT buy any item", expectedMsg, actualMsg);
    }
}
