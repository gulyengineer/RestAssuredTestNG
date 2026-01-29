Feature: Login API

  Scenario: Valid login
    Given valid login credentials
    When I call the login API
    Then the response status is 200
    And the login token is present
    And the response email matches the test email
