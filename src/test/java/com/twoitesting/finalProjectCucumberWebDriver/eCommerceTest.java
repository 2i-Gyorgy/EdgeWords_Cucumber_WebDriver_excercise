package com.twoitesting.finalProjectCucumberWebDriver;

import com.twoitesting.finalProjectCucumberWebDriver.pompages.*;
import io.cucumber.java.en.*;
import org.hamcrest.Matchers;
import org.openqa.selenium.WebDriver;

import java.math.BigDecimal;

import static com.twoitesting.finalProjectCucumberWebDriver.utilitiesPOM.HelpersStaticPOM.priceStringToBigDecimal;
import static org.hamcrest.CoreMatchers.containsStringIgnoringCase;
import static org.hamcrest.MatcherAssert.assertThat;

public class eCommerceTest {
    private static WebDriver driver;

    public eCommerceTest() {
        this.driver = Hooks.driver;
    }

    @Given("I add Sunglasses to My Cart.")
    public void addItemToCart() throws InterruptedException {

        NavbarPOM navBar = new NavbarPOM(driver);
        ShopPOM shop = new ShopPOM(driver);

        // 2. Enter the shop using top nav link ‘Shop’
        navBar.goToShop();
        System.out.println("Navigated to Shop");

        // 3. Add a clothing item to your Cart
        shop.addProductToCart();
        System.out.println("Beanie added to cart");

        // 4. View the Cart
        navBar.goToCart();
        System.out.println("Go to cart");
    }

    @When("I add a coupon code {string}.")
    public void i_add_a(String couponCode) {

        CartPOM cart = new CartPOM(driver);

        // 5. Apply a coupon ‘edgewords’
        cart.addCoupon(couponCode);
        assertThat("Not applied Coupon Code", cart.retrieveAddCouponResponse(), containsStringIgnoringCase("applied"));
        System.out.println("Coupon code applied");
    }

    @Then("The applied discount is 15%, and the Total is correct after discount and shipping.")
    public void the_applied_discount_is_x() {

        CartPOM cart = new CartPOM(driver);

        // 6. Check that the coupon takes off 15%
        // & 7. Check that the total calculated after coupon & shipping is correct
        String priceString = cart.subTotalString();
        System.out.println("Subtotal amount is: " + priceString);
        String discountString = cart.discountString();
        System.out.println("Discount amount is: " + discountString);
        String shippingString = cart.shippingFeeString();
        System.out.println("Shipping fee amount is: " + shippingString);
        String totalString = cart.totalAmountString();
        System.out.println("Total amount is: " + totalString);
//        make some String to BigDecimal conversions
        BigDecimal priceBigDecimal = priceStringToBigDecimal(priceString);
        BigDecimal discountBigDecimal = priceStringToBigDecimal(discountString);
        BigDecimal shippingBigDecimal = priceStringToBigDecimal(shippingString);
        BigDecimal totalBigDecimal = priceStringToBigDecimal(totalString);
//        assertThat("discount price value is incorrect", priceBigDecimal.multiply(BigDecimal.valueOf(0.15)), is(equalTo(discountBigDecimal))); // this came back with x.x00 != x.x
        assertThat("Expected discount does not match actual discount", priceBigDecimal.multiply(BigDecimal.valueOf(.15)), Matchers.comparesEqualTo(discountBigDecimal)); // page shows correct discount
        assertThat("Expected total does not match actual total", priceBigDecimal.multiply(BigDecimal.valueOf(.85)).add(shippingBigDecimal), Matchers.comparesEqualTo(totalBigDecimal)); // page calculates correct total with correct discount applied
        System.out.println("Good new everyone! The applied discount amount is correct!");

        System.out.println("TestCaseOneTest end");
    }

    @When("Purchase item completing Billing Details in Checkout.")
    public void purchase_item_completing_billing_details_in_checkout() {

        CartPOM cart = new CartPOM(driver);
        CheckOutPOM checkout = new CheckOutPOM(driver);

        // 5. Proceed to checkout
        cart.clickCheckoutButton();
        System.out.println("Navigate to cart");

        // 6. Complete Billing details (you will need to use a valid postcode)
        checkout.fillOutShippingAddress();
        System.out.println("fill out shipping details");

        // 7. Select ‘Check payments’ as payment method
        checkout.checkChequeRadioButton();
        System.out.println("Payment method 'cheque' selected");

        // 8. Place the order
        checkout.clickPlaceOrder();
        System.out.println("Order placed");
    }

    @Then("The captured Order number matches with the one on my Orders page.")
    public void the_captured_order_number_matches_with_the_one_on_my_orders_page() {

        CheckOutPOM checkout = new CheckOutPOM(driver);
        NavbarPOM navBar = new NavbarPOM(driver);
        OrdersPOM orders = new OrdersPOM(driver);

        // 9. Capture the Order Number and write it to the results
        String orderNumber = checkout.retrieveOrderNumber();
        System.out.print("the captured order number is: ");
        System.out.println(orderNumber);

        // 10. Navigate to My Account->Orders and check the same order shows in the account
        checkout.navigateToMyAccount();
        System.out.println("Go to My Account");

        navBar.navigateToOrders();
        System.out.println("Go to Orders page");

        assertThat("orders don't contain my order number", orders.findOrderNumber(), Matchers.containsString(orderNumber));
        System.out.println("The two captured order numbers match! happy days");
    }
}
