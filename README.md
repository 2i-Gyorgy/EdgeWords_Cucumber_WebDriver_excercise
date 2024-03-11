EdgeWords training final exercise.

Cucumber - Gherkin - WebDriver - Java
Run test against the edgewords training demo site - https://www.edgewordstraining.co.uk/demo-site/

Feedback from Steve from Edgewords:

Hi Gyorgy
Sorry this has taken some time to do… <br>
## BDD <br>
- [x] Your Scenarios are missing names. I’d suggest something like the following:
   * `Scenario: Coupon code gives appropriate discount`
   * `Scenario: Completed order added to order history`
- [x] The BDD steps themselves are written at the correct level (i.e. they are not test steps referring to specific UI) – although directly referencing the My Account page in the background step does smell a bit of an implementation detail.
- [x] Good to see parameterisation in use (e.g. to pass through username, password and coupon code), but you could have gone a bit further by also parameterising the expected discount percentage (I’ve also broken the last step in to two).<br>
   ```
   Then The applied discount is 15%<br>
   #@Then("The applied discount is {int}%")
   #public void the_applied_discount_is_x(int expectedPerCent) {
   And the Total is correct after discount and shipping.
   ```
- [ ] It would also be a good idea to pass Scenario 2’s customer billing details through as e.g. a data table.
  ```
  When I Purchase the items completing Billing Details in Checkout with
  | Name      | Add1   | Add2           | PCode   |
  | Bob Jones | 123 St | Somewhereville | XX1 1XX |     
  ```
  I always think about being a manual tester handed a feature file. Do I have all the necessary test data for to complete the tests or do I need to go off and generate/source this data? The feature file should ideally be a one-stop-shop and have everything needed. Although there may be times some data is considered sensitive e.g. login data and instead set by environment variables and not committed to a source repository.
- [ ] With more paramiterisation you could also make use of Scenario Outlines to test more test cases via varied examples e.g.
  ```
  Scenario Outline: Coupon code gives appropriate discount
  Given I add "<item>" to My Cart.
  When I add a coupon code "<coupon>".
  Then The applied discount is "<discount>"%, and the Total is correct after discount and shipping.
  Examples:
  | item       | coupon    | discount |
  | cap        | edgewords | 15       |
  | sunglasses | super50   | 50       |
  ```
## POM Pages

- [ ] NavbarPOM – sensible to handle areas common to all pages in their own POM. Al alternative would be to have a BasePOM with these fields/methods in and then inherit that in to other POM classes.<br>
  All straightforward and sensible. Also nice to see waiting ahs been thought about in goToCart()
- [x] myAccountPOM (Java classes usually start with a capital letter)
- [x] Nothing really to say – very straightforward (which is good!)
- [x] ShopPOM - Short and sweet. Hardcoded to find sun glasses at the moment, but using the area-label so you’re close to a generalised method. Entirely untested but you could try:
  ```
  private String item;
  @FindBy (css = "[aria-label*='”+item+”']") WebElement addToCartLinkArg;
  public void addProductToCart(String item) {
  this.item = item;
     addToCartLinkArg.click();
  }
  ```
- [x] CartPOM - More waiting (using helpers), good short CSS, no assertions (when it would have been easy for them to creep in) and just returning values. This is looking like it could turn out to be a quick review!
- [ ] ChekoutPOM - Caught you!<br>
   * fillOutShippingAddress() - This has hard coded test data in it. Really the method should have the address details passed to it from the test step – and it should get that data from the feature file.,br.
   * checkChequeRadioButton() - I will sheepishly admit to just throwing  a Thread.sleep() in here and moving on with my life too. I’ve considered waiting for the spinner element that blocks the UI over the (not) radio button, then waiting for that to go away (ExpectedConditions has helper methods for waiting for things to go away), but I always end up thinking there are edge case race conditions that might not be handled.
   * retrieveOrderNumber() - Strictly speaking that order number doesn’t show up on the checkout page – if you watch the URL you’ll see after clicking place order you are taken to a new page – and thus this should be in that new POM class.
   * navigateToMyAccount() - Wouldn’t your test step use NavbarPOM for this?
- [x] OrdersPOM - Other than the mystery commented out code, nothing to say. Does the job following the POM design pattern.
- [ ] utilitiesPOM/HelpersStaticPOM<br>
  Couple of useful and used) waits.<br>
   * priceStringToBigDecimal() – nice to see an appropriate return type used for handling currency.
   * Seeing the Double in here does make me wince for all the floating point precision inaccuracies I went in to on the course (though I have to admit I’m not sure anything could go wrong here)
   * return BigDecimal.valueOf(Double.valueOf(noCurrencyString));<br>
  To avoid you could:
  ```
  return new BigDecimal(noCurrencyStringBuilder.toString());
  ```
  I also think extracting the ponds & pence could be done a bit faster than with regex matching.
- [ ] Possible other helper methods to add – might be nice to have some screenshots for the report if something fails…
## Hooks
- [ ] @Before – you could add switching which web browser is in use. If the demo store banner gets in the way it could also be implicitly dismissed at this point (well once a page ais loaded).
- [x] @After – Nice to see cleanup after the test making the logged in application state more consistent for future runs.
- [x] Also nice to see plenty of chatty informative logging for reporting purposes.
- [ ] Our driver field is public and static so other (step definition) classes can access it. This won’t work for parallel execution so consider adding picocontainer and using dependency injection to share the driver around.

## Other
- [ ] Background - This is kind of picky (and feeds back to my comment in Hooks) but I’m not sure a step about logging in should be dismissing the demo store banner. It’s doing a bit more than the method name implies. Imaging another test that e.g. logs in, does something, logs out and then logs back in again to check something. It could well re-use this step – but it would fail on the second login because the banner was dismissed on the first run through. Sure you could guard against that (try/catch on the banner click) but its generally better to have methods that only do what they say they will. No surprises.
- [x] eCommerceTest (Java class name conventions… ECommerce…)
- [x] Really good logging of info throughout. Good use of comments too.
- [x] For steps that travel across a few pages I’d suggest not instantiating the POM classes all at once e.g.:<br>
  ```
  @Then("The captured Order number matches with the one on my Orders page.")
  public void the_captured_order_number_matches_with_the_one_on_my_orders_page() {

    CheckOutPOM checkout = new CheckOutPOM(driver);
    NavbarPOM navBar = new NavbarPOM(driver);
    OrdersPOM orders = new OrdersPOM(driver);
  ```
  Instead instantiate the POM class as and when you need it. The POM class could include a check that the browser has navigated to the correct page – doing it all at once is like saying all these pages are loaded right now at this moment, when in fact they wont be in browser until some point later in time.



Not much feedback for the steps I know, but that’s largely because what there was to be said has been said earlier. Code was very clean and clear. You’ve clearly got a very good grasp of what the POM is supposed to provide and how to provide it – just remember test data belongs in the test (which for a cucumber project means the Feature file). Cracking job!

If you have any questions ask away!

Kind Regards,

Steve