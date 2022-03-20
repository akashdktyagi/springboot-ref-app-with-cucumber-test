@put
Feature: As a client
  I want to update Entity Details
  So that I Entity data can be modified

  Scenario: Put method for "/entity" end point
    Given with end point url as "/entity"
    * with acquired token and with authorization flow as "client_credentials"
    * with body json as
    """json
      {
        "name": "laptop2",
        "description":"I am new laptop",
        "company":"HP"
      }
    """
    When with method as "put"
    Then status is "200"



