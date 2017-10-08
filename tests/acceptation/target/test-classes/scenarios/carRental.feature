Feature: Car Search

  Background:
    Given The CarRental service deployed on localhost:9020

  Scenario: Search car

    Given a place equal Paris
    And a date start equal 2017-09-23
    And a date end equal 2017-09-25
    When the service is called
    Then the list of cars is 
        | idCar |    | marque |     | modele |     | numSerie |     | place |     | prix | 
    | 2 |     | Renaut |     | Megane |     | 0 |     | Paris |     | 61.0 | 

    