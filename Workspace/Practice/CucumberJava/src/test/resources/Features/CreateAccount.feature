Feature: feature to create account

  Scenario Outline: create private account with mandatory fields
    Given user is on account creation page
    When user enters personal information with <firstname> and <lastname>
    And user enters Address information with <address1> <address2> <city> <state> <zipcode>
    And user enters vehicle information with
    And user enters device request information with
    And user selects plans with
    And user pays the open items
    Then private account is created with account number

    Examples: 
      | firstname | lastname | address1    | address2 | city     | state | zipcode |
      | Roger     | NJK      | DINO Street | BU Road  | NEW YORK | NY    |   10001 |
      | Green     | MIN      | MIN Street  | BU Road  | NEW YORK | NY    |   10001 |
