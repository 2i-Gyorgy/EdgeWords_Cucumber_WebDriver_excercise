package com.twoitesting.finalProjectCucumberWebDriver.utilitiesPOM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HelpersStaticPOM {
    // varaibles
    public String baseURL = "https://www.edgewordstraining.co.uk/demo-site/";

    // Helper methods
    public static void waitForElementToBeClickablePOM(WebDriver driver, WebElement locator, int timeOutSeconds) {
        WebDriverWait myClickWait = new WebDriverWait(driver, Duration.ofSeconds(timeOutSeconds));
        myClickWait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void waitForElementToBePresentPOM(WebDriver driver, WebElement locator, int timeOutSeconds) {
        WebDriverWait myPresenceWait = new WebDriverWait(driver, Duration.ofSeconds(timeOutSeconds));
        myPresenceWait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static BigDecimal priceStringToBigDecimal(String priceValueString) { // takes in a string, looks for a pattern of \d+.\d+ - the number part of a price -, strips the currency and returns the value converted to BigDecimal
        Pattern pattern = Pattern.compile("\\d+.\\d+");
        Matcher matcher = pattern.matcher(priceValueString);
        StringBuilder noCurrencyStringBuilder = new StringBuilder();
        while (matcher.find()) {
            noCurrencyStringBuilder.append(matcher.group(0));
        }
        String noCurrencyString = noCurrencyStringBuilder.toString();
        return BigDecimal.valueOf(Double.valueOf(noCurrencyString));
    }
}
