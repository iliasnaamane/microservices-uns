# Hotel Service
Authors: 
* Rami Ajroud [(email)](rami.ajroud@etu.unice.fr)
* Antoine Aubé [(email)](antoine.aube@etu.unice.fr)
* Danial Aswad Bin Ahmad Fazlan [(email)](danial-aswad.bin-ahmad-fazlan@etu.unice.fr)
* Günther Jungbluth [(email)](gunther.jungbluth@etu.unice.fr)

## Dependencies
This project requires:
- maven
- java 8
- docker

## Routes
There is one route: ```GET /hotels```.

One can give several query argument:
* ```destination``` the name of the hotel's city.
* ```date``` the date of the booking in the form ```DD-MM-YYYY```.
* ```price_ordering``` the order of the prices: ```asc``` or ```desc```.

The answer is a JSON array which items have fields:
* ```name``` the name of the hotel.
* ```city``` the city where the hotel is located.
* ```roomCost``` the price of one room one night in the hotel.
