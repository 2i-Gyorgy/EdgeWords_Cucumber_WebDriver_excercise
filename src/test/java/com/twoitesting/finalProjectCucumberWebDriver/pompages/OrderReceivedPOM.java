package com.twoitesting.finalProjectCucumberWebDriver.pompages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.twoitesting.finalProjectCucumberWebDriver.utilitiesPOM.HelpersStaticPOM.waitForElementToBePresentPOM;

public class OrderReceivedPOM {
    // Field to work with webdriver in this class
    private WebDriver driver;

    // Constructor to receive driver form test and set field
    public OrderReceivedPOM(WebDriver driver) {
        this.driver = driver;
        // initialise pagefactory with the driver and this class
        PageFactory.initElements(driver, this);
    }

    // locators
    @FindBy(css = "li.woocommerce-order-overview__order.order")
    WebElement orderNumberWithText;

    // Methods
    public String retrieveOrderNumber() {
        waitForElementToBePresentPOM(driver, orderNumberWithText, 2);
        return orderNumberWithText.getText().substring(14, 18);
    }
}
