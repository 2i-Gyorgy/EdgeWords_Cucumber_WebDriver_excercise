package com.twoitesting.finalProjectCucumberWebDriver.pompages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.twoitesting.finalProjectCucumberWebDriver.utilitiesPOM.HelpersStaticPOM.*;

public class NavbarPOM {
    // Field to work with webdriver in this class
    protected WebDriver driver;

    // Constructor to receive driver form test and set field
    public NavbarPOM(WebDriver driver) {
        this.driver = driver;
        // initialise pagefactory with the driver and this class
        PageFactory.initElements(driver, this);
    }

    // locators
    @FindBy(linkText = "Dismiss")
    WebElement dismissLink;  // button to dismiss blue information banner at the bottom
    @FindBy(id = "site-navigation-menu-toggle")
    WebElement menuButton;
    @FindBy(linkText = "Shop")
    WebElement shopLink;
    @FindBy(linkText = "Cart")
    WebElement cartLink;
    @FindBy(linkText = "My account") WebElement myAccountLink;

    // Methods
    public void dismissBanner() {
        dismissLink.click();
    }

    public void goToShop() {
        try {
            shopLink.click();
        } catch (Exception e) {
            menuButton.click();
            shopLink.click();
        }
    }

    public void goToCart() {
        waitForElementToBeClickablePOM(driver, cartLink, 1);
        try {
            cartLink.click();
        } catch (Exception e) {
            menuButton.click();
            cartLink.click();
        }
    }

    public void navigateToMyAccount()  {
        try {
            myAccountLink.click();
        } catch (Exception e) {
            menuButton.click();
            myAccountLink.click();
        }
    }
}

