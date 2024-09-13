package com.twoitesting.finalProjectCucumberWebDriver;

import com.twoitesting.finalProjectCucumberWebDriver.pompages.NavbarPOM;
import com.twoitesting.finalProjectCucumberWebDriver.pompages.MyAccountPOM;
import io.cucumber.java.en.Given;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.twoitesting.finalProjectCucumberWebDriver.utilitiesPOM.HelpersStaticPOM.implicitlyWait;
import static com.twoitesting.finalProjectCucumberWebDriver.utilitiesPOM.HelpersStaticPOM.waitForElementToBePresentPOM;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class Background {
    private static WebDriver driver;
    protected String baseURL = "https://www.edgewordstraining.co.uk/demo-site/";

    public Background() {
        this.driver = Hooks.driver;
    }

    @Given("I'm logged in with my username {string} and password {string}.")
    public void login(String userName, String passwd) throws InterruptedException {

        NavbarPOM navBar = new NavbarPOM(driver);
        MyAccountPOM myAccount = new MyAccountPOM(driver);

        driver.get(baseURL + "my-account/");

        // "demo site" message potentially intercept clicks, make sure it is dismissed
        navBar.dismissBanner();
        System.out.println("Warning banner dismissed");

        // 1. Login to your account at https://www.edgewordstraining.co.uk/demo-site/my-account/ using the account you manually registered
        myAccount.doLogIn(userName, passwd);

       // Verify that we are logged in
//        implicitlyWait (driver, 1);
//        String loginPageText = driver.findElement(By.tagName("body")).getText();
//        assertThat(loginPageText, containsString("Logout"));

        System.out.println("Logged in with username " + userName);
    }
}
