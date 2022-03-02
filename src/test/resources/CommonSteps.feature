Feature: Common Steps

  @t
  # Below is the List of Reusable Steps which can directly be used to write Integration tests
  Scenario: validate post
    Given with end point url as ""
    * with body as ""
    * with headers in JSON format as ""
    * with swagger schema document location as "{string}"
    * with query parameter as in below table
    * with authorization mechanism as "{string}"
    * with mock as "mockDB"
    When with method as "post"
    Then status is "200"
    * response contains string as ""
    * entity of type "Product" is created
    * response contains string as "{string}"
    * response does not contains string as "{string}"
    * response contains string mentioned in below list
    * response does not contains string mentioned in below list
    * response json path as "{string}" has value which contains "{string}"
    * response json path as "{string}" has value which does not contains "{string}"
    * response json path as "{string}" has value equal to "{string}"
    * response json path and values mentioned in below table
      |jsonPath|expectedValue|
    * response json path "{string}" is not null
    * response json path is not null in below list
      |jsonPath|
    * response field represented as json path in below list is present in response
      |jsonPath|
    * response field represented as xml path in below list is present in response
      |xpath|
    * response field represented as json path in below list is not present in response
      |jsonPath|
    * response field represented as xml path in below list is not present in response
      |xpath|

