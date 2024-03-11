Feature: Edgewords eCommerce demo-site

  Background:
    Given I'm logged in with my username "gyorgy.papp@2itesting.com" and password "gyWSfp4Dv5AZFa".

  Scenario Outline: Coupon code gives appropriate discount
    Given I add item "<item>" to the cart.
    When I add a coupon code "edgewords".
    Then The applied discount is 15%.
    And The Total is correct after discount and shipping.
    Examples:
    | item       |
    | Sunglasses |
    | Cap        |

  Scenario Outline: Completed order added to order history
    Given I add item "<item>" to the cart.
    When Purchase item completing Billing Details in Checkout with
      | First Name | Last Name | Address1 | City           | Post Code | Phone Number |
      | Michael    | Knight    | MK St    | RandomplaceMK  | XX1 1XX   | 07123456789  |
    Then The captured Order number matches with the one on my Orders page.
    Examples:
      | item       |
      | Sunglasses |
      | Cap        |