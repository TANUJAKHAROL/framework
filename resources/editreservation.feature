Feature: Manage Customer Reservations

  Scenario: Cancel a customer's reservation
    Given I am logged into the application as a waiter
    And I am on the waiter reservations page
    And I see the reservation for "John Doe" at "123 Main Street, New York, NY 10001" on "2025-06-09" at "12:15 p.m."
    When I cancel the reservation
    Then the reservation should be marked as canceled
    And the customer should be notified of the cancellation

  Scenario: Postpone a customer's reservation
    Given I am logged into the application as a waiter
    And I am on the waiter reservations page
    And I see the reservation for "John Doe" at "123 Main Street, New York, NY 10001" on "2025-06-09" at "12:15 p.m."
    When I postpone the reservation to "2025-06-10" at "1:00 p.m."
    Then the reservation should be updated with the new date and time
    And the customer should be notified of the change

  Scenario: Change the table for a customer's reservation
    Given I am logged into the application as a waiter
    And I am on the waiter reservations page
    And I see the reservation for "John Doe" at "Table 5" on "2025-06-09" at "12:15 p.m."
    When I change the table to "Table 10"
    Then the reservation should be updated with the new table
    And the customer should be notified of the table change