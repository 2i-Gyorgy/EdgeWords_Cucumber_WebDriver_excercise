Feature: Edgewords eCommerce demo-site

  Background:
    Given I'm logged in with my username "gyorgy.papp@2itesting.com" and password "gyWSfp4Dv5AZFa".

  Scenario: Coupon code gives appropriate discount
    Given I add Sunglasses to the cart.
    When I add a coupon code "edgewords".
    Then The applied discount is 15%.
    And The Total is correct after discount and shipping.

  Scenario: Completed order added to order history
    Given I add Sunglasses to the cart.
    When Purchase item completing Billing Details in Checkout.
    Then The captured Order number matches with the one on my Orders page.