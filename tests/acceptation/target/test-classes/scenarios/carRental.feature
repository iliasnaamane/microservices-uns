Feature: Car Search

  Background:
    Given The CarRental service deployed on localhost:9020

  Scenario: Search car

    Given a place equal Londre
    	And a duration equal 10
    When the service is called
    Then the cout total is 30.5