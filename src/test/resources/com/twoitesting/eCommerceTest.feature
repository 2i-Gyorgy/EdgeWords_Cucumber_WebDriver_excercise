Feature: Edgewords eCommerce demo-site

  Background:
    Given I'm logged in with my username "gyorgy.papp@2itesting.com" and password "gyWSfp4Dv5AZFa" on the My Account page.

  Scenario:
    Given I add Sunglasses to My Cart.
    When I add a coupon code "edgewords".
    Then The applied discount is 15%, and the Total is correct after discount and shipping.

  Scenario:
    Given I add Sunglasses to My Cart.
    When Purchase item completing Billing Details in Checkout.
    Then The captured Order number matches with the one on my Orders page.