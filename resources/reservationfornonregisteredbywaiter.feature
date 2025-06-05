Feature: Create Anonymous Reservation for a Visitor

  Scenario: Create a new reservation for a visitor
    Given I am logged into the application as a waiter
    And I am on the waiter reservations page
    When I choose to create an anonymous reservation
    And I fill out the visitor details with:
      | Name     | Jane Doe                          |
      | Contact  | jane.doe@example.com              |
      | Phone    | +1-555-123-4567                   |
    And I fill out the reservation form with:
      | Location           | 123 Main Street, New York, NY 10001 |
      | Date               | 2025-06-09                         |
      | Time               | 12:15 p.m.                         |
      | Number of Guests   | 2                                  |
      | Table              | Table 3                            |
    And I submit the reservation details
    Then the reservation should be created successfully
    And the visitor "Jane Doe" should be notified of their new reservation via email or phone