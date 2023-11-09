package com.twoitesting.finalProjectCucumberWebDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.twoitesting.finalProjectCucumberWebDriver.pompages.*;

public class Hooks {
    public static WebDriver driver;

    @Before
    public void setup() {
        driver = new ChromeDriver();
    }

    @After
    public void teardown() {

        String baseURL = "https://www.edgewordstraining.co.uk/demo-site/";

        CartPOM cart = new CartPOM(driver);

        System.out.println("@AfterEach start");
        driver.get(baseURL + "cart/");
        if (!driver.findElements(By.className("woocommerce-remove-coupon")).isEmpty()) {
            cart.removeCoupon();
            System.out.println("Coupon removed");
        }
        if (!driver.findElements(By.className("remove")).isEmpty()) {
            cart.removeProduct();
            System.out.println("Product removed");
        }

        // 8. / 11. Finally - Log Out
        driver.get(baseURL + "my-account/");
        myAccountPOM myAccount = new myAccountPOM(driver);
        myAccount.doLogOut();
        System.out.println("Logged out");

        driver.quit();

        System.out.println("@AfterEach end");
    }
}
