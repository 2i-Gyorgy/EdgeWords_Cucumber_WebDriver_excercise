package com.twoitesting.finalProjectCucumberWebDriver.pompages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class myAccountPOM {
    // Field to work with webdriver in this class
    private WebDriver driver;

    // Constructor to receive driver form test and set field
    public myAccountPOM(WebDriver driver) {
        this.driver = driver;
        // initialise pagefactory with the driver and this class
        PageFactory.initElements(driver, this);
    }

    // locators
    @FindBy(id = "username")
    WebElement usernameField;
    @FindBy(id = "password")
    WebElement passwordField;
    @FindBy(css = "button[value='Log in']")
    WebElement loginLink;
    @FindBy(linkText = "Logout")
    WebElement logOutLink;

    // Methods

    public void doLogIn(String username, String password) {
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginLink.click();
    }

    public void doLogOut() {
        logOutLink.click();
    }
}
