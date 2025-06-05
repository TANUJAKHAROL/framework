Feature: Table Search

  Scenario: View list of available tables
    Given I am on the customer booking page
    When I select the location "123 Main Street, New York, NY 10001"
    And I select the date "2025-06-09"
    And I select the time "12:15 p.m."
    And I search for available tables
    Then I should see a list of available tables in the selected location, date, and timeslot

  Scenario: Filter tables by guests quantity
    Given I am on the customer booking page
    When I select the location "123 Main Street, New York, NY 10001"
    And I select the date "2025-06-09"
    And I select the time "12:15 p.m."
    And I filter tables by guests quantity 4
    Then I should see tables available for 4 guests

