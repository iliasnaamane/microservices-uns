Feature: Hotel Search

  Background:
    Given The HotelFinders service deployed on localhost:9010

  Scenario: Search hotel

    Given a place is Paris
    And a duration is 10
    And Ascending is true   
    When the service called
    Then the list of hotels is
    | identifier |    | lieu |     | nom |     | prix |
    | 3 |     | Paris |     | Le Carton Confortable |     | 250 |  
    | 1 |     | Paris |     | MetroDODO |     | 300 |  
    | 2 |     | Paris |     | Dododoré |     | 10000 |