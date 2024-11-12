@cart
Feature: Verify the add to cart functionality

  Background:
    Given user is successfully logged in

  Scenario: Verify add to cart
    When user clicks on Add to cart button
    And check added to cart message
    And navigate to cart
    Then verify the product details in cart
    And continue checkout
    When user enters payment details
    And fills shipping details
    And place order
    Then confirm the order creation
    And verify product details in order confirmation