@JHipsterLogin
Feature: Login
  Background: A browser is launched with home page  url

  Scenario: Successful Login with Valid Credentials
  Given I am at home page
    When I navigate to login page
    And User enters Username as "admin" and Password as "admin"
    And Clicks on Sign In
    Then Redirected to the home page again with successful signed-in message
    And close the browser
