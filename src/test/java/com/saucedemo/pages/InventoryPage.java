package com.saucedemo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class InventoryPage extends BasePage{

    @FindBy(className = "inventory_item_name")
    public WebElement products;

    @FindBy(className = "btn_inventory")
    public List<WebElement> addToCartBtn;

    @FindBy(className = "shopping_cart_link")
    public WebElement cartIcon;


}
