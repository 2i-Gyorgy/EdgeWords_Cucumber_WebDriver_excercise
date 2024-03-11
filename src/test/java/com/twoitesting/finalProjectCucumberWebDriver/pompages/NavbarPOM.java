package com.twoitesting.finalProjectCucumberWebDriver.pompages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.twoitesting.finalProjectCucumberWebDriver.utilitiesPOM.HelpersStaticPOM.*;

public class NavbarPOM {
    // Field to work with webdriver in this class
    protected WebDriver driver;
    protected WebDriverWait wait;

    // Constructor to receive driver form test and set field
    public NavbarPOM(WebDriver driver) {
        this.driver = driver;
        // initialise pagefactory with the driver and this class
        PageFactory.initElements(driver, this);
    }

    // locators
    @FindBy(linkText = "Dismiss")
    WebElement dismissLink;  // button to dismiss blue information banner at the bottom
    @FindBy(linkText = "Shop")
    WebElement shopLink;
    @FindBy(css = "#site-header-cart > li > a")
    WebElement cartButton;
    @FindBy(linkText = "Orders")
    WebElement ordersLink;
    @FindBy(linkText = "My account") WebElement myAccountLink;

    // Methods
    public void dismissBanner() {
        dismissLink.click();
    }

    public void goToShop() {
        shopLink.click();
    }
    public void goToCart() {
        waitForElementToBeClickablePOM(driver, cartButton, 1);
        cartButton.click();
    }

    public void navigateToOrders() {
        ordersLink.click();
    }

    public void navigateToMyAccount()  {
        myAccountLink.click();
    }
}

