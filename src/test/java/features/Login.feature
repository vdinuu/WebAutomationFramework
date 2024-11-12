@login
Feature: Login functionality

  Background:
  Given User is on login page

  Scenario Outline: Verify successful login

    When user enters login credentials "<username>" and "<password>"
    And clicks on login button
    Then login should be successful

    Examples:
      | username         | password     |
      | vdinuu@gmail.com | Automate@123 |
