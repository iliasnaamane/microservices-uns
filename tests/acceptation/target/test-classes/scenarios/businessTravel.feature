Feature: Testing the business travel REST Web service
  Users should be able to submit GET and POST requests to the web service
 
  Scenario: Data post request to a web service
    When users submit a business travel
    Then the node server should handle it and return a success status
  Scenario: Data get request to a web service
    When users get all business travels
    Then the node server should return data