@put
Feature: As a client
  I want to update Entity Details
  So that I Entity data can be modified

  Scenario: Client is able to modify an existing entity with put method on "/entity" end point
    Given an entity already present with data as
      | name        | computer          |
      | description | i am new computer |
      | company     | HP              |
    * with end point url as "/entity"
    * with acquired token and with authorization flow as "client_credentials"
    * with body json as
    """json
      {
        "name": "computer",
        "description":"Description After Edit",
        "company":"Company After Edit"
      }
    """
    When with method as "put"
    Then status is "200"
    * response contains string as "Company After Edit"
    * response contains string as "Description After Edit"

  Scenario: Client is able not able to modify if entity is not present before hand
    Given with acquired token and with authorization flow as "client_credentials"
    * with end point url as "/entity"
    * with body json as
    """json
      {
        "name": "NoSuchEntity",
        "description":"Description After Edit",
        "company":"Company After Edit"
      }
    """
    When with method as "put"
    Then status is "200"
    * response contains string as "Company After Edit"
    * response contains string as "Description After Edit"



