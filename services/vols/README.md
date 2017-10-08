# Flights Service API ( Document based web service) :


### the service Deal with POST request on : localhost:9030/vols/webapi/vols 
 - produce and consumes 'application/json' data only 
 - answers `200` if everything went well, `400` elsewhere.

## Operations: 

The services follows a document approach, and handle the following events:


- ![#1589F0](https://placehold.it/15/1589F0/000000?text=+) **One_Way_Price** : GET One Way Flights Ordered By Price for a given date , origine and destination .
- ![#1589F0](https://placehold.it/15/1589F0/000000?text=+) **One_Way_Stops** : GET One Way One Way Flights Order By Number Of Stops for a given date , origine and destination.
- ![#1589F0](https://placehold.it/15/1589F0/000000?text=+) **One_Way_Duration** : One Way Flight Order By Duration for a given date , origine and destination.
- ![#1589F0](https://placehold.it/15/1589F0/000000?text=+) **One_Way_Rating** : One Way Flight Order By Rating for a given date , origine and destination.
- ![#1589F0](https://placehold.it/15/1589F0/000000?text=+) **Return_Price** : Round-trip Order By Price for a given date , origine and destination.
- ![#1589F0](https://placehold.it/15/1589F0/000000?text=+) **Return_Rating** : Round-trip Flight Order By Rating for a given date , origine and destination.
- ![#1589F0](https://placehold.it/15/1589F0/000000?text=+) **Return_Duration** : Round-trip Flight Order By Duration for a given date , origine and destination.
- ![#1589F0](https://placehold.it/15/1589F0/000000?text=+) **Return_Stops** : Round-trip Flight Order By Number Of Stops for a given date , origine and destination.

### One_Way_Price usage example

```json

  {
	"event": "One_Way_Price",
	"Outbound_date": "12-10-2017",
	"from" :"Nice",
	"to" : "Paris"
  }
 
```

### One_Way_Stops usage example

```json

  {
	"event": "One_Way_Stops",
	"Outbound_date": "12-10-2017",
	"from" :"Nice",
	"to" : "Paris"
  }
 
```
### One_Way_Duration usage example

```json

  {
	"event": "One_Way_Price",
	"Outbound_date": "12-10-2017",
	"from" :"Nice",
	"to" : "Paris"
  }
 
```
### One_Way_Rating usage example

```json

  {
	"event": "One_Way_Price",
	"Outbound_date": "12-10-2017",
	"from" :"Nice",
	"to" : "Paris"
  }
 
```
### Return_Price usage example

```json

  {
	"event": "Return_Price",
	"Outbound_date": "12-10-2017",
  "return_date":"13-10-2017",
	"from" :"Nice",
	"to" : "Paris"
  }
 
```
### Return_Duration usage example

```json
{
	"event": "Return_Duration",
	"Outbound_date": "12-10-2017",
	"return_date":"13-10-2017",
	"from" :"Nice",
	"to" : "Paris"
}
```
### Return_Rating usage example

```json

  {
	"event": "Return_Rating",
	"Outbound_date": "12-10-2017",
  "return_date":"13-10-2017",
	"from" :"Nice",
	"to" : "Paris"
  }
 
```
### Return_Stops usage example

```json
{
	"event": "Return_Stops",
	"Outbound_date": "12-10-2017",
	"return_date":"13-10-2017",
	"from" :"Nice",
	"to" : "Paris"
}
```
