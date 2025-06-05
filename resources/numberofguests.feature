Feature: Reservation Form Guest Specification

  Scenario: Specify the number of guests for a reservation
    Given I am logged into the application
    And I am on the customer booking page
    When I select the location "123 Main Street, New York, NY 10001"
    And I select the date "2025-06-09"
    And I select the time "12:15 p.m."
    And I specify "4" guests for the reservation
    Then the reservation form should accept "4" as the number of guests
    And I should see available tables for "4" guests