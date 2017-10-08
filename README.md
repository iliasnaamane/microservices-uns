# Virtual Travel Agency

## Authors: 
* NAAMANE Ilias
* FAGUET Guillaume
* AFAILAL Abdelmoughit
* DEJEUX Maxime

## Getting started
  Build the project from the services directory with running `./build.sh`
  Start the ecosystem from the root folder with running `./start.sh`
  Test the ecosystem by running `./tests.sh` from the tests folder
  
## Services & Features
* [x] Car Rents
  *  User can find alternative and available car rents of a given place between a dateStart & dateEnd.
  * The service use RPC paradigm using SOAP protocol.
  * Why RPC? We have specific parameters as an input and one single feature on this service. Tight Coupling of RPC will not be  embarassing in this case with the advantage of the ability to encapsule remote invokation to the procedure.
  The use of REST in this case will be a bad idea, since we will have different parameters to deal with  a single GET request. Query name parameters are not recommended for REST architecture. ( e.g &dest=Paris&....)
 

* [x] Hotel Rents
  * User can find alternative hotels for a given place and a given duration sorted by price ( ascending or descending ) depending on client choice ( boolean parameter )
  * The service use RPC paradigm using SOAP protocol.
  * Why RPC? ( Same arguments as Car Rents Service )


* [x] [Flight Rents]( https://github.com/iliasnaamane/microservices-uns/blob/master/services/vols/README.md)                  
  * User can find alternative flights at a given place and a given dates and many other preferences
  * The service use Document paradigm using JSON data format.
  * Why Document? This service has many procedures which handle differeence user preferences such as ( one way flight ) ..The use of RPC in that case would be an overkill since we will have a tight coupling of many procedures. So the simplest why is REST? No We will get some ugly URIS /flights?destination="paris"&typeflight="oneway"&date="2016-02-23"&..........................) and this kind of URIS can be considered as an anti-pattern. Otherwise the use of Document paradigm is like RPC, except the coupling constraint which helps us to have more flexibility between the client/server.

  

* [x] [Business Travels](https://github.com/iliasnaamane/microservices-uns/blob/master/services/resourceBusinessTravel/Readme.md)
  * Employee can submit a business travel, Manager review a list of business travels, decline and also can approve.
  * The service use REST Architecture (resource paradigm)
  * Why REST? This service has 4 basic operations ( GET, POST, PUT, DELETE ). 
  GET -> to list , POST -> to submit a business travel , PUT -> to approve , DELETE-> to decline. So the simplest way is to use REST architecture to handle this 4 operations that REST inherited from the HTTP protocol. The resource would be /businesstravels.

* [x] [Mailing Service](https://developers.google.com/gmail/api/guides/sending) [ Must be implemented in the integration phase ]
  * This service is useful when a Manager approve a business travel, a summary must be sent to the employee.
  * This service is considered as an extern service using an API
  * This feature doesn't belong to the business logic, so the use of an API such as GMAIL API would help us to have a good performance on this mailing service.
  
    
## Technological Stack
### Service Development
* REST: ExpressJS
* SOAP : JAX-WS
### Storage
* MongoDB ( Mongoose )
* Lists

### Deployment
* Docker compose

### Testing
* Acceptance: Cucumber
* Stress: Gatling


  
  
  




      
