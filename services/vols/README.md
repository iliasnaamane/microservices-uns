# Flights Service API ( Document based web service) :


### the service Deal with POST request on : localhost:9030/vols/webapi/vols 
 - produce and consumes 'application/json' data only 
 - answers `200` if everything went well, `400` elsewhere.

## Operations: 

The services follows a document approach, and handle the following events:


- ![#1589F0](https://placehold.it/15/1589F0/000000?text=+) **Search** : GET a list of available flights given by a date of outbound and a date of return if available and can be sorted by many preferences , see examples bellow.


### Example of One_Way flight sorted by Price  

```json

  
   {
	"event": "Search",
	"sortby":"Price",
	"Outbound_date": "12-10-2017",
	"from" :"Nice",
	"to" : "Paris"
  }
 
```

### Example of One_Way flight sorted by number of Stops 

```json

   {
	"event": "Search",
	"sortby":"Stops",
	"Outbound_date": "12-10-2017",
	"from" :"Nice",
	"to" : "Paris"
  }
 
```
### Example of One_Way flight sorted by Duration 

```json

   {
	"event": "Search",
	"sortby":"Duration",
	"Outbound_date": "12-10-2017",
	"from" :"Nice",
	"to" : "Paris"
  }
 
```
### Example of One_Way flight sorted by Rating 

```json

  {
	"event": "Search",
	"sortby":"Rating",
	"Outbound_date": "12-10-2017",
	"from" :"Nice",
	"to" : "Paris"
  }
 
```
### Example of a return flight flight sorted by Price 


```json

 {
	"event": "Search",
	"sortby":"Price_r",
	"Outbound_date": "12-10-2017",
	"return_date":"13-10-2017",
	"from" :"Nice",
	"to" : "Paris"
}
```
### Example of a return flight flight sorted by Duration 

```json
{
	
	"event": "Search",
	"sortby":"Duration_r",
	"Outbound_date": "12-10-2017",
	"return_date":"13-10-2017",
	"from" :"Nice",
	"to" : "Paris"
}

```
### Example of a return flight flight sorted by Rating 

```json

  {
	"event": "Search",
	"sortby":"Rating_r",
	"Outbound_date": "12-10-2017",
	"return_date":"13-10-2017",
	"from" :"Nice",
	"to" : "Paris"
}
 
```
### Example of a return flight flight sorted by Number Of Stops 

```json
{
	"event": "Search",
	"sortby":"Stops_r",
	"Outbound_date": "12-10-2017",
	"return_date":"13-10-2017",
	"from" :"Nice",
	"to" : "Paris"
}

```
