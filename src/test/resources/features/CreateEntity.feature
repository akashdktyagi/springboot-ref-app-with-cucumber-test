@post
Feature: As a client
  I want to create a new Entity
  So that every time a new user is created

  #Style - 1  One Style of Writing the Scenario, which may be more in line with the Business understanding
  #For this to work, you will have to write your own steps and use the fw capability internally to call api end points and validate
  Scenario: Client want to create a new Entity - Style 1
    Given Client have new entity with details as below
      | name        | laptop          |
      | description | i am new laptop |
      | company     | HP              |
    When Client invokes the application to create new entity
    Then a new entity is created in the system

  #Style - 2  Another style of Writing the Scenario, which is more inline with Dev but makes sense to Business as well
  #For this to work, you will have to write your own steps and use the fw capability internally to call api end points and validate
  Scenario: Client want to create a new Entity - Style 2
    Given Client have new entity with details as below
      | name        | laptop1          |
      | description | i am new laptop |
      | company     | HP              |
    When Client calls end point "/entity" with method as 'post'
    Then Client receive status code as 200
    And a new product is created in the DB

  #Style - 3  Third Style of Writing the Scenario, which makes for sense to Dev but may not so much for Business
  #The advantage with this approach is that it these are generic steps and there is no need to impl these steps as they are already impmeneted and
  # you can use these steps to define how your API should behave as well as creating the living documentation of the behaviour.
  # More over, it also serves as the traditional Integration tests which keeps a check on the API behaviour and supports refactoring attempts.
  @t
  Scenario: post request for "/entity" end point - Style 3 - Generic
    Given with end point url as "/entity"
    And with acquired token and with authorization flow as "client_credentials"
    And with body json as
    """json
      {
        "name": "laptop2",
        "description":"I am new laptop",
        "company":"HP"
      }
    """
    When with method as "post"
    Then status is "200"
    * response contains string as "laptop"


