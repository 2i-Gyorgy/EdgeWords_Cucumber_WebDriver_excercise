package com.twoitesting.finalProjectCucumberWebDriver.pompages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrdersPOM {
    // Field to work with webdriver in this class
    protected WebDriver driver;
    protected WebDriverWait wait;
    // Constructor to receive driver form test and set field
    public OrdersPOM(WebDriver driver){
        this.driver = driver;
        // initialise pagefactory with the driver and this class
        PageFactory.initElements(driver, this);
    }
    // locators
    @FindBy (css = "tbody > tr:nth-child(1) > td:nth-child(1) > a") WebElement orderNumber;
    // Methods
     public String findOrderNumber() {
         return orderNumber.getText();
     }
}
