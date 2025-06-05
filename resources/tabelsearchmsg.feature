Feature: Prompt Customer to Log In or Sign Up when accessing reservation form

  Scenario: Customer tries to access reservation form without logging in
    Given the customer is not logged into the application
    When the customer tries to access the reservation form
    Then the application should not display the reservation form
    And the application should prompt the customer to log in or sign up