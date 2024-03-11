package com.twoitesting.finalProjectCucumberWebDriver.pompages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ShopPOM {
    // Field to work with webdriver in this class
    protected WebDriver driver;
    protected WebDriverWait wait;
    // Constructor to receive driver form test and set field
    public ShopPOM(WebDriver driver){
        this.driver = driver;
        // initialise pagefactory with the driver and this class
        PageFactory.initElements(driver, this);
    }
    // Methods
    public void addProductToCart(String item) {
        WebElement addToCartLink = driver.findElement(By.cssSelector("[aria-label*='" + item + "']"));
        addToCartLink.click();
    }
}
