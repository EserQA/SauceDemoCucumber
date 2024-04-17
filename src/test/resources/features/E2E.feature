@wip
Feature: E2E test to buy any item from https://www.saucedemo.com/

  Background: : Login Feature
    Given the user navigates to the www.saucedemo.com
    And the user enters username
    And the user enters password
    When the user clicks login button
    Then the user logins and lands on "https://www.saucedemo.com/inventory.html" page successfully

  Scenario: Buy any item
    Given the user clicks any add to cart button
    And the user clicks cart icon
    When the user clicks checkout button
    And the user enters "Ross" to the first name box
    And the user enters "Geller" to the last name box
    And the user enters "35000" to the postal code box
    And the user clicks continue button
    When the user clicks finish button
    Then the user buys any item and see the following message
      | Checkout: Complete! |

#  Scenario Outline: Buy any item
#    Given the user clicks any add to cart button
#    And the user clicks cart icon
#    When the user clicks checkout button
#    And the user clicks continue button
#    When the user clicks finish button
#    Then the user buys any item and see the following message
#      | CHECKOUT: COMPLETE! |
#    Examples:
#      | firstName | lastName | zipCode |
#      | "Ross"    | "Geller" | "35000" |



