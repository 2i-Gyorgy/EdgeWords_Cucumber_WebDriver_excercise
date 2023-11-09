package com.twoitesting.finalProjectCucumberWebDriver.pompages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.twoitesting.finalProjectCucumberWebDriver.utilitiesPOM.HelpersStaticPOM.*;

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
    // locators
    @FindBy (css = "[aria-label*='Sunglasses']") WebElement addToCartLink;
    // Methods
     public void addProductToCart() {
         addToCartLink.click();
     }
}
