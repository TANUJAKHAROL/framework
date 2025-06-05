Feature: Reservation Confirmation

  Scenario: Receive confirmation after submitting reservation details
    Given I am logged into the application
    And I have filled out the reservation form with:
      | Location           | 123 Main Street, New York, NY 10001 |
      | Date               | 2025-06-09                         |
      | Time               | 12:15 p.m.                         |
      | Number of Guests   | 4                                  |
    When I submit the reservation details
    Then I should receive a confirmation message
    And the confirmation message should indicate the reservation was made successfully