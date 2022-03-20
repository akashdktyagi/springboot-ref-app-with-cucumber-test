@get
Feature: As a client
  I want to get Entity Details
  So that I could see the all or individual entity details

  # Validate Get Request
  Scenario: Get end point for "/entity" end point
    Given with end point url as "/entity"
    And with acquired token and with authorization flow as "client_credentials"
    When with method as "get"
    Then status is "200"

