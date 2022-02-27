Feature: As a client
  I want to create a new Entity
  So that every time a new user is created

  Scenario: Client want to create a new Entity
    Given Client have new entity with details as below
      | name        | laptop          |
      | description | i am new laptop |
      | company     | HP              |
    When Client calls end point "/entity" with method as 'post'
    Then Client receive status code as 200
    And a new product is created in the DB