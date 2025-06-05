Feature: Create New Reservation for Existing Customer

  Scenario: Create a new reservation for an existing customer
    Given I am logged into the application as a waiter
    And I am on the waiter reservations page
    When I search for the existing customer by their email "johndoe@example.com"
    And I select the customer "John Doe"
    And I fill out the reservation form with:
      | Location           | 123 Main Street, New York, NY 10001 |
      | Date               | 2025-06-09                         |
      | Time               | 12:15 p.m.                         |
      | Number of Guests   | 4                                  |
      | Table              | Table 5                            |
    And I submit the reservation details
    Then the reservation should be created successfully
    And the customer "John Doe" should be notified of their new reservation