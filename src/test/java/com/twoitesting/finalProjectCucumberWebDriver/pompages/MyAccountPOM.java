package com.twoitesting.finalProjectCucumberWebDriver.pompages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.twoitesting.finalProjectCucumberWebDriver.utilitiesPOM.HelpersStaticPOM.implicitlyWait;
import static com.twoitesting.finalProjectCucumberWebDriver.utilitiesPOM.HelpersStaticPOM.scrollElementIntoView;

public class MyAccountPOM {
    // Field to work with webdriver in this class
    private WebDriver driver;

    // Constructor to receive driver form test and set field
    public MyAccountPOM(WebDriver driver) {
        this.driver = driver;
        // initialise pagefactory with the driver and this class
        PageFactory.initElements(driver, this);
    }

    // locators
    @FindBy(id = "username")
    WebElement usernameField;
    @FindBy(id = "password")
    WebElement passwordField;
    @FindBy(className = "woocommerce-form-login__submit")
    WebElement logInButton;
    @FindBy(linkText = "Logout")
    WebElement logOutLink;
    @FindBy(linkText = "Orders")
    WebElement ordersLink;

    // Methods

    public void doLogIn(String username, String password) {
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        scrollElementIntoView(driver, logInButton);
        implicitlyWait(driver, 1);
        logInButton.click();
//        implicitlyWait(driver, 5);
    }

    public void doLogOut() {
        logOutLink.click();
    }

    public void clickOrders() {
        ordersLink.click();
    }
}
