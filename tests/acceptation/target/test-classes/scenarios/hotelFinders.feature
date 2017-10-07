Feature: Hotel Search

  Background:
    Given The HotelFinders service deployed on localhost:9010

  Scenario: Search hotel

    Given a place is Paris
    	And a duration is 10
    When the service called
    Then the name of hotel is Le Carton Confortable