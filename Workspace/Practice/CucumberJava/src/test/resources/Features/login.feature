#Author

Feature: feature to test login functionality

Scenario: Check login functionality with valid credentials
    Given user is on login page
    When user enters username and password
    And clicks on Login button
    Then user is navigated to home page

Scenario: Check login functionality with valid credentials1
    Given user is on login page1
    When user enters username and password1
    And clicks on Login button1
    Then user is navigated to home page1