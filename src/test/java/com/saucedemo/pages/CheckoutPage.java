package com.saucedemo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends BasePage{

    @FindBy(id = "first-name")
    public WebElement firstNameBox;

    @FindBy(id = "last-name")
    public WebElement lastNameBox;

    @FindBy(id = "postal-code")
    public WebElement  postalCodeBox;

    @FindBy(id = "continue")
    public WebElement continueBtn;

    @FindBy(id = "finish")
    public WebElement finishBtn;

    @FindBy(className = "title")
    public WebElement checkoutMessage;

}
