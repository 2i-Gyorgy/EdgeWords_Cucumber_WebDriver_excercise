package com.twoitesting.finalProjectCucumberWebDriver.pompages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.twoitesting.finalProjectCucumberWebDriver.utilitiesPOM.HelpersStaticPOM.*;

public class CheckOutPOM {
    // Field to work with webdriver in this class
    private WebDriver driver;

    // Constructor to receive driver form test and set field
    public CheckOutPOM(WebDriver driver) {
        this.driver = driver;
        // initialise pagefactory with the driver and this class
        PageFactory.initElements(driver, this);
    }

    // locators
    @FindBy(id = "billing_first_name")
    WebElement FirstNameField;
    @FindBy(id = "billing_last_name")
    WebElement LatNameField;
    @FindBy(id = "billing_address_1")
    WebElement address1Field;
    @FindBy(id = "billing_city")
    WebElement cityField;
    @FindBy(id = "billing_postcode")
    WebElement postcodeField;
    @FindBy(id = "billing_phone")
    WebElement phoneNumberField;
    @FindBy(css = "label[for='payment_method_cheque']")
    WebElement paymentMethodChequeRadioButton;
    @FindBy(id = "place_order")
    WebElement placeOrderButton;

    // Methods
    public void fillOutShippingAddress() {
        FirstNameField.clear();
        FirstNameField.sendKeys("Devon");
        LatNameField.clear();
        LatNameField.sendKeys("Miles");
        address1Field.clear();
        address1Field.sendKeys("5 King Street");
        cityField.clear();
        cityField.sendKeys("Dundee");
        postcodeField.clear();
        postcodeField.sendKeys("DD1 2PE");
        postcodeField.clear();
        postcodeField.sendKeys("DD1 2PE");
        phoneNumberField.clear();
        phoneNumberField.sendKeys("07753826584");
    }

    public void checkChequeRadioButton() {
        try {
            Thread.sleep(1000); // need it, fix it, but no idea. listening to the conversations, it seems to be a hard one
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        paymentMethodChequeRadioButton.click();
    }

    public void clickPlaceOrder() {
        placeOrderButton.click();
    }

}
